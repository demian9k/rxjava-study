package io.demian.rxjava.operators.conditional;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.operators.creation.interval;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.internal.operators.observable.ObservableAll;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 *  기준이 되는 observable2 에서 값이 발행되는 순간 데이터 발행중인 observable1 을 onComplete 한다.
 *  그러나 구독자는 observable2를 이어서 받지는 않는다.
 */
public class takeUntil {
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
         *  subscribe를 하면 zipInterval100ms observable에서 값의 발행을 시작한다.
         *  그리고 takeUntil에서 받은 timer500ms observable이 값을 발행하기 시작하면
         *  zipInterval100ms observable이 즉시 완료합니다.(onComplete 이벤트 발생)
         *
         *  takeUntil 기준이 되는 timer500ms 는 500ms 이후 발행이 시작되므로
         *  이 시점에서
         *  zipInterval100ms은 100ms 주기로 흐르다가 4개의 값만 발행하고 종료된다.
         *
         *  1, 2, 3, 4 까지만 발행된다.
         *
         */
        zipInterval100ms.takeUntil(timer500ms)
                .subscribe(Log::i);


        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        new takeUntil().emit();
    }
}
