package io.demian.rxjava.operators.transform.flatMap;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class reactiveGugudan3 {
    public void emit() {

        Scanner in = new Scanner(System.in);

        System.out.println("input:");

        int dan = Integer.parseInt(in.nextLine());

        Function<Integer, Observable<Integer>> mapper = num -> Observable.range(1,9);

        //flatMap의 다른 시그니쳐
        BiFunction<Integer, Integer, String> resultSelector = (gugu, i) -> gugu + " * " + i + " = " + gugu * i;

        Observable<String> source = Observable.just(dan).flatMap( mapper, resultSelector );

//        Observable<String> source = Observable.just(dan)
//                .flatMap(
//                gugu -> Observable.range(1,9),
//                (gugu, i) -> gugu + " * " + i + " = " + gugu * i  );
//
        source.subscribe( System.out::println );

        in.close();
    }

    public static void main(String[] args) {
        new reactiveGugudan3().emit();
    }
}
