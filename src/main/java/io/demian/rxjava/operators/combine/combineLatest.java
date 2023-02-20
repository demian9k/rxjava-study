package io.demian.rxjava.operators.combine;


import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.functions.BiFunction;
import io.reactivex.observables.ConnectableObservable;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;


/**
 *  2개 이상의 observable을 기반으로 각각의 흐름에서 값이 변경되었을 때 갱신해주는 함수
 *  마지막 인자로 입력되는 combiner 함수가 각 observable을 결합하여 결과를 만들어주는 역할을 한다.
 */
public class combineLatest {

    /**
     * ReactiveSum
     */
    public void emit() {

        /**
         * source Observable -- user keyboard input
         */
        ConnectableObservable<String> source = userInput();

        /**
         * observable A
         */
        Observable<Integer> A = source
                .filter( str -> str.startsWith("a:") )
                .map( str -> str.replace("a:", "") )
                .map( Integer::parseInt );

        /**
         * observable B
         */
        Observable<Integer> B = source
                .filter( str -> str.startsWith("b:") )
                .map( str -> str.replace("b:", "") )
                .map( Integer::parseInt );

        /**
         * combiner
         * A , B observable 값이 변경될 때 동작하여 결과를 만든다.
         */
        BiFunction<? super Integer, ? super Integer, Integer> combiner = (x, y) -> x + y;

        Observable
                .combineLatest( A.startWith(0), B.startWith(0), combiner )
                .subscribe(
                    res -> System.out.println("Result: "+ res)
                );

        //connect to Hot Observable
        source.connect();

    }

    public ConnectableObservable<String> userInput() {
        return Observable.create( (ObservableEmitter<String> emitter) -> {

            Scanner in = new Scanner(System.in);

            while(true) {
                System.out.println("Input : ");
                //blocking
                String line = in.nextLine();

                emitter.onNext(line);

                if( line.indexOf("exit") >= 0 ) {
                    in.close();
                    break;
                }

            }
        }).publish(); //to ConnectableObservable
    }


    public static void main(String[] args) {
        new combineLatest().emit();
    }
}

