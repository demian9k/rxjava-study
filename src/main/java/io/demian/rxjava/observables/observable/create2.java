package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

public class create2 {
    public void emit() {

        Observable<Integer> source = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(100);
                emitter.onNext(200);
                emitter.onNext(300);
                emitter.onComplete();
            }
        });


        source.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer data) throws Exception {
                System.out.println("result : " + data);
            }
        });

    }

    public static void main(String[] args) {
        new create2().emit();
    }
}
