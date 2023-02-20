package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;

public class fromIterable1 {
    public void emit() {

        List<String> names = new ArrayList<>();
        names.add("Jerry");
        names.add("William");
        names.add("Bob");


        Observable<String> source = Observable.fromIterable(names);

        source.subscribe(data -> System.out.println("Result : " + data));
    }

    public static void main(String[] args) {
        new fromIterable1().emit();
    }

}

