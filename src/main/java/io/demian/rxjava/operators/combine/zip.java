package io.demian.rxjava.operators.combine;


import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import java.sql.Time;
import java.util.concurrent.TimeUnit;



/**
 * 각각의 observable을 활용해 2개 이상의 observable을 결합한다.
 */
public class zip {

    public void emit() {

        /**
         * example1
         */
        String[] shapes ={"BALL", "PENTAGON", "STAR"};
        String[] coloredTriangles = {"2-T", "6-T", "4-T"};

        Observable<String> source = Observable.zip(
                Observable.fromArray(shapes).map(Shape::getSuffix),
                Observable.fromArray(coloredTriangles).map(Shape::getColor),
                (suffix, color) -> color + suffix
        );

//        source.subscribe(Log::i);

        /**
         * example2
         */
        Observable<Integer> source2 = Observable.zip(
                Observable.just(100, 200, 300),
                Observable.just(10, 20, 30),
                Observable.just(1, 2, 3),
                (a,b,c) -> a + b + c
        );

//        source2.subscribe(Log::i);


        /**
         * example3 zipInterval 패턴
         */
        Observable<String> source3 = Observable.zip(
                Observable.just("RED", "GREEN", "BLUE"),
                Observable.interval(200L, TimeUnit.MILLISECONDS ),
                (value, i) -> value
        );


        CommonUtils.exampleStart();
        source3.subscribe(Log::it);
        CommonUtils.sleep(1000);

    }

    public static void main(String[] args) {
        new zip().emit();
    }
}
