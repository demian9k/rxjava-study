package io.demian.rxjava.operators.creation;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class range {
    public void emit() {


        CommonUtils.exampleStart();

        //현재 스레드에서 실행되므로 sleep이 필요없다.
        Observable<Integer> source = Observable.range(1, 9999)
//            .doOnNext( System.out::println )
            .filter( number -> number % 2 == 0);

        source.subscribe(Log::i);

        //현재 스레드에서 실행되므로 sleep이 필요없다.
//        CommonUtils.sleep(1000);

    }

    public static void main(String[] args) {
        new range().emit();
    }
}
