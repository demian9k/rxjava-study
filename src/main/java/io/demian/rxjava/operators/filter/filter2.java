package io.demian.rxjava.operators.filter;

import io.reactivex.Observable;
import io.reactivex.Single;


public class filter2 {
    public void emit() {

        Integer[] numbers = {100, 200, 300, 400, 500};

        //첫번째 값 가져옴 -1 은 기본값이다.
        Single<Integer> single1 = Observable.fromArray(numbers).first(-1);
        single1.subscribe(data -> System.out.println("first() value = " + data));

        //마지막 값 가져옴 -1 은 기본값이다.
        Single<Integer> single2 = Observable.fromArray(numbers).last(-1);
        single2.subscribe(data -> System.out.println("last() value = " + data));

        //take(n) n 개 값만 가져옴
        Observable<Integer> source1 = Observable.fromArray(numbers).take(2);
        source1.subscribe(data -> System.out.println("take() value = " + data));

        //takeLast(n) 뒤에서 n 개 값만 가져옴
        Observable<Integer> source2 = Observable.fromArray(numbers).takeLast(2);
        source2.subscribe(data -> System.out.println("takeLast() value = " + data));

        //skip(n) 전체 값에서 n개 값을 건너뜀
        Observable<Integer> source3 = Observable.fromArray(numbers).skip(2);
        source3.subscribe(data -> System.out.println("skip() value = " + data));

        //skip(n) 전체 값에서 n개 값을 건너뜀
        Observable<Integer> source4 = Observable.fromArray(numbers).skipLast(2);
        source4.subscribe(data -> System.out.println("skipLast() value = " + data));
    }

    public static void main(String[] args) {
        new filter2().emit();
    }
}
