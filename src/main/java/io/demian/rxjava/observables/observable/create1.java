package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

public class create1 {
    public void emit() {

        /**
         * 다른 observable creation 함수들은 자동알림 이벤트가 발생하지만 create 함수는
         * onNext, onComplete, onError 같은 알림을 개발자가 직접 호출해줘야 한다.
         */
        Observable<Integer> source = Observable.create( (ObservableEmitter<Integer> emitter) -> {
            emitter.onNext(100); //데이터의 발행
            emitter.onNext(200);
            emitter.onNext(300);
            emitter.onComplete(); //완료 이벤트 발행
        });

        source
                .subscribe(data -> System.out.println("Result : " + data));

    }

    public static void main(String[] args) {
        new create1().emit();
    }
}
