package io.demian.rxjava.observables;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * subscribe 함수는 Disposal 인스턴스를 반환한다.
 * Observable contract에 의해서 observable은 onComplete 알림시 dispose를 실행하여 구독을 해지한다.
 * 그러므로 일반적으로 dispose를 명시적으로 호출하지 않는다.
 *
 * 그러나 onComplete 알림이 발생하지 않는 경우 memory leak의 위험이 있으므로
 * dispose는 observable에게 더 이상 데이터를 발행하지 않도록 구독을 해지하는 함수다.
 */
public class disposable {
    public void emit() {

        Observable<String> source = Observable.just("RED", "GREEN", "YELLOW");

        Disposable d = source.subscribe(
                v -> System.out.println("onNext() : value : "+ v),
                err -> System.err.println("onError() : err" + err.getMessage() ),
                () -> System.out.println("onComplete")
        );


        System.out.println("isDisposed() :" + d.isDisposed() );

    }

    public static void main(String[] args) {
        new disposable().emit();
    }

}




