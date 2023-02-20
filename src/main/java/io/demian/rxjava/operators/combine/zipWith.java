package io.demian.rxjava.operators.combine;


import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

public class zipWith {

    public void emit() {

        Observable<Integer> source = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10,20,30),
                (a, b) -> a + b
        ).zipWith( Observable.just(1,2,3), (ab,c) -> ab + c);

        source.subscribe(Log::i);
    }

    public static void main(String[] args) {
        new zipWith().emit();
    }
}