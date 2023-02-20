package io.demian.rxjava.operators;

import io.demian.rxjava.common.CommonUtils;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.annotations.SchedulerSupport;
import io.reactivex.functions.BiFunction;

import java.util.concurrent.TimeUnit;


/**
 * 값을 발행하기 전 지연시간을 지정한다.
 */
public class delay {
    public void emit() {

        String[] data = {"1", "3", "5"};

        Observable<String> source = Observable
                .fromArray(data)
                .delay(100L, TimeUnit.MILLISECONDS);

        source.subscribe( System.out::println );

//        delay - SchedulerSupport.COMPUTATION
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        new delay().emit();
    }
}
