package io.demian.rxjava.operators;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;


public class reduce1 {
    public void emit() {

        String[] balls = {"1", "3", "5"};


        BiFunction mergeBalls = (ball1, ball2) -> ball2 + "(" + ball1 + ")";

        Maybe<String> source = Observable.fromArray(balls)
                .reduce( mergeBalls );

        source.subscribe( System.out::println );
    }

    public static void main(String[] args) {
        new reduce1().emit();
    }
}
