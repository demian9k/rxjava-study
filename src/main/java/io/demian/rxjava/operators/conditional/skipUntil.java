package io.demian.rxjava.operators.conditional;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 *  기준이 되는 observable2 에서 값이 발행되는 순간 observable1의 데이터 발행을 시작한다.
 *  구독자는 observable1의 데이터를 받는다.
 */
public class skipUntil {
    public void emit() {

        String[] data1 = {"1", "2", "3", "4", "5", "6"};

        Observable<Long> interval100ms = Observable.interval(100L, TimeUnit.MILLISECONDS);
        Observable<Long> timer500ms = Observable.timer(500L, TimeUnit.MILLISECONDS);


        /**
         * 100ms 주기로 data1을 발행
         */
        Observable<String> zipInterval100ms = Observable.fromArray(data1)
                .zipWith( interval100ms, (val, notUsed) -> val);  //zipInterval

        /**
         *
         *  subscribe를 하면 zipInterval100ms observable은 값을 발행하지만
         *  skipUntil에 지정한 timer500ms observable이 값을 발행하기 시작할때까지 구독자에게 값을 전달하지 않는다.
         *
         *  timer500ms observable이 값을 발행시작하는 시점인 500ms 이후 시점부터
         *  zipInterval100ms observable은 값이 구독자에게 전달된다.
         *
         *  zipInterval100ms은 100ms 주기로 흐르다가 500ms 이후 시점의 2개의 값 (5, 6)만 발행하고 종료된다.
         */
        zipInterval100ms.skipUntil(timer500ms)
                .subscribe(Log::i);

        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        new skipUntil().emit();
    }
}
