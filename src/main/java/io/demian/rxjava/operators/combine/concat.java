package io.demian.rxjava.operators.combine;


import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import java.util.concurrent.TimeUnit;


/**
 *  2개 이상의 observable을 순서대로 실행하는 함수
 *  각 observable이 끝날 때 onComplete 이벤트가 발생해야 이후 observable이 subscribe가 시작된다.
 */
public class concat {

    String[] data1 = {"1", "3", "5"};
    String[] data2 = {"2" ,"4", "6"};

    public void emit() {

        /**
         * io.reactivex.functions.Action
         * A functional interface similar to Runnable but allows throwing a checked exception.
         */
        Action onCompleteAction = () -> Log.d("onComplete()");

        /**
         * observable A
         *
         * initial delay 있음 0이어도 없는 것보다는 느리다.
         */
        Observable<String> A = Observable.fromArray(data1)
                .doOnComplete(onCompleteAction);

        /**
         * observable B
         */
        Observable<String> B = Observable.interval( 100L, TimeUnit.MILLISECONDS)
                .map( Long::intValue )
                .map(idx -> data2[idx] )
                .take( data2.length )
                .doOnComplete(onCompleteAction);

        /**
         * CONCAT & SUBSCRIBE
         * A가 모두 실행되고 나서 B가 실행된다.
         * 최대 4개 observable을 결합가능하다.
         */
        Observable.concat( A, B )
                .doOnComplete(onCompleteAction)
                .subscribe(Log::i);



        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new concat().emit();
        new concat().emit();
        new concat().emit();
        new concat().emit();
        new concat().emit();
        new concat().emit();
    }
}

