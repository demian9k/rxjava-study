package io.demian.rxjava.operators.transform.flatMap;

import io.reactivex.Observable;

import java.util.Scanner;

public class reactiveGugudan {
    public void emit() {

        Scanner in = new Scanner(System.in);

        System.out.println("input:");

        int dan = Integer.parseInt(in.nextLine());

        Observable<Integer> source = Observable.range(1,9);

        //구독시 외부변수 dan을 참조
        source.subscribe( row -> System.out.println(dan + " * " + row + " = " + dan * row ));

        in.close();
    }

    public static void main(String[] args) {
        new reactiveGugudan().emit();
    }
}
