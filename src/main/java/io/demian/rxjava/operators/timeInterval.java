package io.demian.rxjava.operators;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Timed;

import java.util.concurrent.TimeUnit;


/**
 * 값을 발행하면 그 값을 발행했을 때 이전 값 발행 이후 시간이 얼마인지 알려준다.
 */
public class timeInterval {
    public void emit() {

        String[] data = {"1", "3", "7"};

        //현재 시간을 static에 저장한다.
        CommonUtils.exampleStart();

        Observable<Timed <String>> source = Observable
                .fromArray(data)
                .delay(item -> {
                    //무작위시간동안 스레드를 sleep 한다.
                    //아래 작업시간동안 작업을 지연한다는 뜻이다.
                    CommonUtils.doSomething();
                    return Observable.just(item);
                })
                .timeInterval();

        //Timed 객체안에 data와 지난 시간값, 단위가 포함되어 있다.
        source.subscribe( Log::it );

//        delay - SchedulerSupport.COMPUTATION
        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        new timeInterval().emit();
    }
}
