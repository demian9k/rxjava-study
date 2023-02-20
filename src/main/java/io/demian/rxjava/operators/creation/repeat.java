package io.demian.rxjava.operators.creation;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;


/**
 *
 */
public class repeat {
    public void emit() {

        String[] balls = {"1", "3", "5"};

        /**
         * 인자 N만큼 balls 를 반복한다.
         * 인자가 없으면 계속 반복한다.
         */
        Observable<String> source = Observable.fromArray( balls ).repeat(3);

        source.doOnComplete( () -> Log.d("onComplete"))
                .subscribe(Log::i);
    }

    public static void main(String[] args) {
        new repeat().emit();
    }
}
