package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;

import java.util.stream.IntStream;

public class fromArray2 {
    public void emit() {

        int[] arr = {100, 200, 300};

        Observable<Integer> source = Observable.fromArray(toIntegerArray(arr));

        source.subscribe(data -> System.out.println("Result : " + data));
    }

    public static void main(String[] args) {
        new fromArray2().emit();
    }

    private static Integer[] toIntegerArray(int[] intArray) {
        return IntStream.of(intArray)
                .boxed()
                .toArray(Integer[]::new);
    }
}

