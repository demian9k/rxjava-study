package io.demian.rxjava.operators.transform;

import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class map3 {
    public void emit() {

        Function<String, Integer> getDiamond = ball -> {
            switch(ball) {
                case "RED":     return 1;
                case "YELLOW":  return 2;
                case "GREEN":   return 3;
                case "BLUE":    return 5;
                default :       return -1;
            }
        };

        String[] balls = {"RED", "YELLOW", "GREEN", "BLUE"};

        Observable<Integer> source = Observable.fromArray(balls)
                .map( getDiamond );

        source.subscribe(Log::i);

    }

    public static void main(String[] args) {
        new map3().emit();
    }
}
