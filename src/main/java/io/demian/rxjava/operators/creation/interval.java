package io.demian.rxjava.operators.creation;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class interval {
    public void emit() {


        CommonUtils.exampleStart();

//        반복
        Observable<Long> source = Observable.interval(100L, TimeUnit.MILLISECONDS)
//            .doOnNext( System.out::println )
            .map(data -> (data + 1) * 100 );
//            .take(5);

        source.subscribe(Log::i);
        //주석 처리하면 스레드에서 할일이 없어 종료된다.
        //다른 스레드에서 실행되므로 sleep이 필요하다.
        CommonUtils.sleep(10000000);

    }

    public static void main(String[] args) {
        new interval().emit();
    }
}
