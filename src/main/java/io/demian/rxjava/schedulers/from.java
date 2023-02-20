package io.demian.rxjava.schedulers;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * from 함수 java.util.concurrent 패키지의 Executor를 스케줄러로 변환할 수 있다.
 */
public class from {
    public void emit() {

        final int THREAD_NUM = Runtime.getRuntime().availableProcessors();

        String[] data = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(data);
        Executor executor = Executors.newFixedThreadPool(THREAD_NUM);

        Executor singleExecutor = (Executor) Executors.newSingleThreadExecutor();

        //Observable A
        Observable<String> A = source.subscribeOn(Schedulers.from(executor));

        //Observable B
        Observable<String> B = source.subscribeOn(Schedulers.from(executor));

        /**
         * single Executor
         */
        Observable<String> single = source.subscribeOn( Schedulers.from(singleExecutor) );

        A.subscribe(Log::i);
        B.subscribe(Log::i);

        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new from().emit();
        new from().emit();
        new from().emit();
        new from().emit();
        new from().emit();
        new from().emit();
        new from().emit();
    }
}
