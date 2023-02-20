package io.demian.rxjava.schedulers;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * computation은 cpu에 대응하는 계산용 스케줄러이다.
 * 계산 작업시에는 대기시간을 최소화하여 빠르게 결과를 도출해야한다.
 *
 * 계산 작업은 I/O를 사용하지 않는 작업에 최적화된 스케줄러이다.
 * 내부적으로 스레드풀을 생성하며 스레드개수는 프로세서 갯수와 같다.
 *
 * Observable.interval 함수는 내부적으로 computation 스케줄러를 사용한다.
 * ( reactive 함수들은 대부분 스케줄러를 지정할 수 있다)
 */
public class computation {
    public void emit() {

        String[] data = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(data)
                .zipWith(Observable.interval(100L, TimeUnit.MILLISECONDS), (a,b) -> a);


        //스케줄러를 io로 변경한 interval operation
//        Observable<Long> ioInterval = Observable.interval(100, TimeUnit.MILLISECONDS, Schedulers.io() );

        //Observable A
        Observable<String> A = source
                .doOnNext( d -> Log.v("Original data#1: " + d) )
                .map( d -> "<<" + d + ">>")
                .subscribeOn(Schedulers.computation());

        //Observable B
        Observable<String> B = source
                .doOnNext( d -> Log.v("Original data#2: " + d) )
                .map( d -> "##" + d + "##")
                .subscribeOn(Schedulers.computation());


        A.subscribe(Log::i);
        B.subscribe(Log::i);

        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new computation().emit();
        new computation().emit();
        new computation().emit();
        new computation().emit();
        new computation().emit();
        new computation().emit();
        new computation().emit();
    }
}
