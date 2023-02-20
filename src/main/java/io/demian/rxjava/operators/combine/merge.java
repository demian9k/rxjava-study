package io.demian.rxjava.operators.combine;


import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiFunction;
import io.reactivex.observables.ConnectableObservable;
import org.checkerframework.checker.units.qual.A;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 *  가장 일반적인 결합 함수
 *  입력된 observable 순서와 데이터 발행 여부 등을 신경쓰지 않고
 *  먼저 입력되는 데이터를 그대로 발행한다.
 */
public class merge {

    String[] data1 = {"1", "3"};
    String[] data2 = {"2" ,"4", "6"};

    public void emit() {

        /**
         * observable A
         *
         * initial delay 있음 0이어도 없는 것보다는 느리다.
         */
        Observable<String> A = Observable.interval( 100L, TimeUnit.MILLISECONDS)
                .map( Long::intValue )
                .map(idx -> data1[idx] )
                .take( data1.length );

        /**
         * observable B
         */
        Observable<String> B = Observable.interval( 100L, TimeUnit.MILLISECONDS)
                .map( Long::intValue )
                .map(idx -> data2[idx] )
                .take( data2.length );

        /**
         * MERGE & SUBSCRIBE
         */
        Observable.merge( A, B )
                .subscribe(Log::i);


        /**
         * merge는 SchedulerSupport.NONE 이지만
         * interval이 computation 이므로 필요함
         */
        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new merge().emit();
        new merge().emit();
        new merge().emit();
        new merge().emit();
        new merge().emit();
        new merge().emit();
    }
}

