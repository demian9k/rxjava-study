package io.demian.rxjava.operators.transform;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.observables.GroupedObservable;

import java.util.List;

/**
 *
 */
public class scan {

    public void emit() {

        String[] balls = {"1", "3", "5"};

        BiFunction mergeBalls = (ball1, ball2) -> ball2 + "(" + ball1 + ")";

//        Maybe<String> reduceSource = Observable.fromArray(balls)
//                .reduce( mergeBalls );


        /**
         * reduce와 유사하지만 반환 타입이 Maybe가 아닌 Observable이다.
         * reduce의 경우는 마지막 값이 입력되지 않거나 onComplete 이벤트가 발생하지 않으면
         * 구독자에게 값을 발행하지 않지만 그래서 reduce의 경우 Maybe를 반환한다.
         *
         * 그러나 scan은 값이 입력될 때마다 구독자에게 값을 발행하므로 observable이다.
         */
        Observable<String> ScanSource = Observable.fromArray(balls).scan( mergeBalls  );
        ScanSource.subscribe( Log::i );
    }


    public static void main(String[] args) {
        new scan().emit();
    }


}
