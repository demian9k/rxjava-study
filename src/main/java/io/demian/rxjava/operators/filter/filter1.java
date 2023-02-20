package io.demian.rxjava.operators.filter;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class filter1 {
    public void emit() {

        String[] objs = {"1 CIRCLE", "2 DIAMOND", "3 TRIANGLE", "4 DIAMOND", "5 CIRCLE", "6 HEXAGON"};


        Observable<String> source = Observable.fromArray(objs)
                .filter( obj -> obj.endsWith("CIRCLE") );

        source.subscribe(System.out::println);




        Integer[] numbers = {100, 34, 27, 99, 50};

        Observable<Integer> source2 = Observable.fromArray(numbers)
                .filter( num -> num % 2 == 0 );

        source2.subscribe(System.out::println);
    }

    public static void main(String[] args) {
        new filter1().emit();
    }
}
