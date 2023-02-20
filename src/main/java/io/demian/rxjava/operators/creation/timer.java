package io.demian.rxjava.operators.creation;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class timer {
    public void emit() {


        CommonUtils.exampleStart();

        Observable<String> source = Observable.timer(100L, TimeUnit.MILLISECONDS)
//            .doOnNext( System.out::println )
            .map( notused -> {
                return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
            });

        source.subscribe(Log::i);
        //주석 처리하면 스레드에서 할일이 없어 종료된다.
        //다른 스레드에서 실행되므로 sleep이 필요하다.
        CommonUtils.sleep(1000);

    }

    public static void main(String[] args) {
        new timer().emit();
    }
}
