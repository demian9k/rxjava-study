package io.demian.rxjava.operators.transform.flatMap;

import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

/**
 *
 * map은 일대일로 대응되지만 flatMAp은 결과가 observable이므로 일대다로 대응된다.
 * flatMap은 interleaving 한다.
 * 순서가 지켜지지 않는다.
 * 순서를 지키려면 concatMap을 사용한다.
 */
public class flatMap1 {
    public void emit() {

        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.fromArray(balls)
                .flatMap( this::getDoubleDiamonds );

        source.subscribe(Log::i);

    }

    public Observable<String> getDoubleDiamonds(String ball) {
        return Observable.just( ball + "<>", ball + "<>");
    }

    public static void main(String[] args) {
        new flatMap1().emit();
    }
}
