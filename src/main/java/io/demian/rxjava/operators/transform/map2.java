package io.demian.rxjava.operators.transform;

import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class map2 {
    public void emit() {

        Function<String, String> getDiamond = ball -> ball + "<>";
        String[] balls = {"1", "2", "3", "5"};

        Observable<String> source = Observable.fromArray(balls)
                .map( getDiamond );

        source.subscribe(Log::i);

    }

    public static void main(String[] args) {
        new map2().emit();
    }
}
