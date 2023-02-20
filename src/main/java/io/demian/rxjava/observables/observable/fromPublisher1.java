package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class fromPublisher1 {
    public void emit() {

        Publisher<String> publisher = new Publisher<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("published in Publisher subscribe method");
                s.onComplete();
            }
        };

        Observable<String> source = Observable.fromPublisher(publisher);

        source.subscribe(data -> System.out.println(data));
    }

    public static void main(String[] args) {
        new fromPublisher1().emit();
    }

}


