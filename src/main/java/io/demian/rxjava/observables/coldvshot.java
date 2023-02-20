package io.demian.rxjava.observables;

import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

public class coldvshot {
    final Count count = new Count();

    public static void main(String[] args) {
        coldvshot demo = new coldvshot();
//        demo.testColdObservable();
        demo.testHotObservable();
    }


    public void testColdObservable() {
        final Count count = new Count();

        Observable<String> observable = Observable
                .range(0, 10)
                .timestamp()
                .map(timestamped -> String.format("[%d] %d", timestamped.value(), timestamped.time(TimeUnit.MILLISECONDS)))
                .doOnNext(value -> count.increase());

        observable.subscribe(value -> {
            System.out.println("subscriber1 : " + value);
        });

        observable.subscribe(value -> {
            System.out.println("subscriber2 : " + value);
        });

        System.out.println("연산횟수 : " + count.count());
    }

    /**
     * 흐름을 공유하여 연산횟수가 줄었음.
     */
    public void testHotObservable() {
        final Count count = new Count();

        ConnectableObservable<String> observable = Observable
                .range(0, 10)
                .timestamp()
                .map(timestamped -> String.format("[%d] %d", timestamped.value(), timestamped.time(TimeUnit.MILLISECONDS)))
                .doOnNext(value -> count.increase())
                .publish();

        observable.subscribe(value -> {
            System.out.println("subscriber1 : " + value);
        });

        observable.subscribe(value -> {
            System.out.println("subscriber2 : " + value);
        });

        observable.connect();
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
