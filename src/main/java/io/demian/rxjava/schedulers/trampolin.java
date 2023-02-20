package io.demian.rxjava.schedulers;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.schedulers.Schedulers;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 *
 * 새로운 스레드를 생성하지 않고 1개의 스레드에 무한한 크기의 Waiting Queue를 사용하는 스케줄러이다.
 *
 * 입출력 작업은 비동기로 실행되지만 결과를 얻기까지 대기 시간이 길다.
 *
 * I/O 작업 : network request, file I/O DB Query
 */
public class trampolin {
    public void emit() {

        String[] strings = {"1", "2", "3"};

        Observable<String> source = Observable.fromArray(strings);

        //Observable A
        Observable<String> A = source
                .subscribeOn(Schedulers.trampoline())
                .doOnNext( d -> Log.v("Original data#1: " + d) )
                .map( d -> "<<" + d + ">>");


        //Observable B
        Observable<String> B = source
                .subscribeOn(Schedulers.trampoline())
                .doOnNext( d -> Log.v("Original data#2: " + d) )
                .map( d -> "##" + d + "##");


        /**
         * 큐에 작업을 넣은 후 시작하므로 A, B 순서가 바뀌는 일은 없다.
         */
        A.subscribe(Log::i);
        B.subscribe(Log::i);

        CommonUtils.sleep(1000);

        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new trampolin().emit();
        new trampolin().emit();
        new trampolin().emit();
        new trampolin().emit();
        new trampolin().emit();
        new trampolin().emit();
        new trampolin().emit();
    }
}
