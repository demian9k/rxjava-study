package io.demian.rxjava.schedulers;


import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * marble diagram을 코드로 구현
 */
public class newThread {
    public void emit1() {


        //STAR - TRIANGLE - HEXAGON
        String[] objs = {"1-S", "2-T", "3-P"};

        //스레드 생성이나 runnable 등을 직접 사용하지 않고
        //스케줄러를 지정함으로서 데이터 흐름이 발생하는 스레드와 처리된 결과를 전달하는 스레드를 분리할 수 있다.
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext( data -> Log.v("Original data = " + data )) //onNext 이벤트 발생시 실행됨
                .subscribeOn(Schedulers.newThread()) //subscribe 이벤트 발생시 스레드의 스케줄러 전략을 지정한다.
                .observeOn(Schedulers.newThread()) //observable 이 처리되고 구독자에게 발행하는 스레드의 스케줄러 전략을 지정한다.
                .map(Shape::flip);


        source.subscribe(Log::i);

        /**
         * result
         *
         * RxNewThreadScheduler-1 | Original data = 1-S
         * RxNewThreadScheduler-1 | Original data = 2-T
         * RxNewThreadScheduler-1 | Original data = 3-P
         * RxNewThreadScheduler-2 | value = (flipped)1-S
         * RxNewThreadScheduler-2 | value = (flipped)2-T
         * RxNewThreadScheduler-2 | value = (flipped)3-P
         *
         */

        CommonUtils.sleep(500);
    }

    public void emit2() {




        //STAR - TRIANGLE - HEXAGON
        String[] objs = {"1-S", "2-T", "3-P"};

        //스레드 생성이나 runnable 등을 직접 사용하지 않고
        //스케줄러를 지정함으로서 데이터 흐름이 발생하는 스레드와 처리된 결과를 전달하는 스레드를 분리할 수 있다.
        //newThread는 새로운 스레드를 생성하여 작업을 처리한다
        Observable<String> source = Observable.fromArray(objs)
                .doOnNext( data -> Log.v("Original data = " + data )) //onNext 이벤트 발생시 실행됨
                .subscribeOn(Schedulers.newThread()) //subscribe 이벤트 발생시 실행됨
//                .observeOn(Schedulers.newThread()) //observable 이 처리되는 스케줄러 지정
                .map(Shape::flip);


        source.subscribe(Log::i);

        /**
         * result
         * observeOn handler를 지정하지 않아 subscribeOn시 지정한 스레드로 모든 작업 처리
         *
         * RxNewThreadScheduler-1 | Original data = 1-S
         * RxNewThreadScheduler-1 | value = (flipped)1-S
         * RxNewThreadScheduler-1 | Original data = 2-T
         * RxNewThreadScheduler-1 | value = (flipped)2-T
         * RxNewThreadScheduler-1 | Original data = 3-P
         * RxNewThreadScheduler-1 | value = (flipped)3-P
         *
         */

        CommonUtils.sleep(500);
    }

    public void emit3() {

        String[] strings = {"1", "2", "3"};

        Observable.fromArray(strings)
                .doOnNext( d -> Log.v("Original data#1 : " + d) )
                .map( d -> "<<" + d + ">>")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::i);

        //이 sleep 을 통해 두번째 observable을 충분히 지연시키지 않으면
        //스레드의 실행이 interleaving 한다.
        CommonUtils.sleep(500);

        Observable.fromArray(strings)
                .doOnNext( d -> Log.v("Original data#2 : " + d) )
                .map( d -> "##" + d + "##")
                .subscribeOn(Schedulers.newThread())
                .subscribe(Log::i);

        //2개의 sleep이 없으면 subscribe가 실행되지 않는다..
        CommonUtils.sleep(0);

        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new newThread().emit1();
        new newThread().emit2();
        new newThread().emit3();
    }
}
