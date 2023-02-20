package io.demian.rxjava.operators.transform;

import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

public class map1 {
    public void emit() {

        String[] balls = {"1", "2", "3", "5"};
//        String[] ball2 = new String[] {"1", "2", "3", "5"};

        Observable<String> source = Observable.fromArray(balls)
                .map( ball -> ball + "<>");

        source.subscribe(Log::i);

    }

    public static void main(String[] args) {
        new map1().emit();
    }
}
