package io.demian.rxjava.observables.single;

import io.demian.rxjava.common.Order;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 *  Observable의 특수한 형태로 1개의 데이터만 발행하도록 한정한다.
 *  일반적으로 결과가 유일한 SERVER API를 호출할 때 유용하다.
 */
public class single1 {
    public void emit() {



        /**
         * static 메서드로 Observable에서 single로 변환
         */
        Observable<String> source = Observable.just("Hello Single");

        Single
            .fromObservable(source)
            .subscribe(System.out::println);

        /**
         * 값이 유일한 observable에서 single 메서드를 호출해서 생성
         */
        Observable.just("Hello Single")
            .single("default item")
            .subscribe(System.out::println);


        //first() observable에서 첫번째 값을 발행함
        String[] colors = {"red", "blue", "Gold"};
        Observable
                .fromArray(colors)
                .first("default value")
                .subscribe(System.out::println);


        //observable이 빈 값이면 single 메서드에 입력된 기본값이 발행됨
        Observable
                .empty()
                .single("empty default value")
                .subscribe(System.out::println);


        //take()로 값을 1개만 가져옴
        Observable
                .just(
                    new Order("ORD-1"),
                    new Order("ORD-2")
                )
                .take(1)
                .single(new Order("default order"))
                .subscribe(System.out::println);


    }

    public static void main(String[] args) {
        new single1().emit();
    }
}
