package io.demian.rxjava.schedulers.appliedExample;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

import java.util.concurrent.TimeUnit;

//https://gist.github.com/QuadFlask/145e80b4ac54d1541e2d38d9ce762a57

public class refCount {
    final Count count = new Count();

    public static void main(String[] args) {
        refCount demo = new refCount();
        demo.refCount1(); //first

        demo.usingPublishSubject(); //first
        demo.usingReplaySubject(); //first
    }

    /**
     * 결과는 전혀 multicast 가 되지 않았습니다.
     * 사실 publish().refCount() 를 빼고 쓴것과 동일한 결과입니다.
     * 이유는 refCount 로 만들어진 Observable 에 처음 구독자가 subscribe 를 하게 됨으로써
     * connect 가 된것인데, 모든 item 이 emit 되고 난후 두번째 구독자가 구독이 되기 때문입니다.
     * ** 이 찍힌 위치를 보면 알수 있습니다. 따라서 첫번째 구독자가 구독을 다하고,
     * 두번째 구독자가 다시 새로운 스트림에 구독을 하게 됨으로써 얻고자했던 multicast 의 이점을 얻을수가 없습니다.
     *
     */
    public void refCount1() {
        Observable<String> observable = Observable
                .range(0, 4)
                .timestamp()
                .map(timestamped -> {
                    System.out.println("\n________________map 연산________________\n");
                    return String.format("[%d] %d", timestamped.value(), timestamped.time(TimeUnit.MILLISECONDS));
                })
                .doOnNext(value -> count.increase())
                .publish().refCount();

        observable.subscribe(value -> {
            System.out.println("subscriber1 : " + value);
        });
        System.out.println("*******************************************************");
        observable.subscribe(value -> {
            System.out.println("subscriber2 : " + value);
        });

        System.out.println("연산횟수 : " + count.count());
    }

    /**
     *  Subject 를 이용하여 item 을 나중에 emit 시키면 원하는 multicast 를 할수 있었습니다.
     */
    public void usingPublishSubject() {
        PublishSubject<Integer> publishSubject = PublishSubject.create();

        Observable<String> observable = publishSubject
                .timestamp()
                .map( timestamped -> {
                    System.out.println("\n________________map 연산________________\n");
                    count.increase();
                    return String.format("[%d] %d", timestamped.value(), timestamped.time(TimeUnit.MILLISECONDS));
                } )
                .publish().refCount();

        observable.subscribe( value -> {
            System.out.println("subscriber1 : " + value);
        });

        observable.subscribe(value -> {
            System.out.println("subscriber2 : " + value);
        });

        publishSubject.onNext( 1 );
        publishSubject.onNext( 2 );
        publishSubject.onNext( 3 );
        publishSubject.onNext( 4 );

        System.out.println("연산횟수 : " + count.count());
    }

    /**
     * 그리고 ReplaySubject 를 이용하면 subscribe 하기 전에 emit 한 item 도 받을수 있습니다.
     */
    public void usingReplaySubject() {
        ReplaySubject<Integer> replaySubject = ReplaySubject.create();

        replaySubject.onNext( 1 );
        replaySubject.onNext( 2 );
        replaySubject.onNext( 3 );
        replaySubject.onNext( 4 );

        Observable<String> observable = replaySubject
                .timestamp()
                .map( timestamped -> {

                    System.out.println("\n________________map 연산________________\n");
                    count.increase();
                    return String.format("[%d] %d", timestamped.value(), timestamped.time(TimeUnit.MILLISECONDS));
                } )
                .replay().refCount();

        observable.subscribe( value -> {
            System.out.println("subscriber1 : " + value);
        });

        observable.subscribe(value -> {
            System.out.println("subscriber2 : " + value);
        });

        System.out.println("연산횟수 : " + count.count());
    }



    static class Count {
        private int count = 0;

        void increase() {
            count++;
        }

        int count() {
            return count;
        }
    }

}
