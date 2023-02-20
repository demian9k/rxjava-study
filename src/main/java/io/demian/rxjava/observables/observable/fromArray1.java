package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;

public class fromArray1 {
    public void emit() {

        Integer[] arr = {100, 200, 300};

        Observable<Integer> source = Observable.fromArray(arr);

        source.subscribe(data -> System.out.println("Result : " + data));
    }

    public static void main(String[] args) {
        new fromArray1().emit();
    }
}
