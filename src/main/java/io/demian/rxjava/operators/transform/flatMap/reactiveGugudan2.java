package io.demian.rxjava.operators.transform.flatMap;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class reactiveGugudan2 {
    public void emit() {

        Scanner in = new Scanner(System.in);

        System.out.println("input:");

        int dan = Integer.parseInt(in.nextLine());

        Function<Integer, Observable<String>> gugudan = num ->
                Observable
                        .range(1,9)
                        .map( row -> num + " * " + row + " = " + num * row  );

        Observable<String> source = Observable.just(dan).flatMap(gugudan);

        //구독시 외부변수 dan을 참조하지 않고, gugudan 함수는 pure function이므로 재사용 가능해짐
        source.subscribe( System.out::println );

        in.close();
    }

    public static void main(String[] args) {
        new reactiveGugudan2().emit();
    }
}
