package io.demian.rxjava.observables.subject;

import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;


/**
 *
 * publisher 기본값이 없고 구독 시점부터 이후에 발행된 값만 수신한다.
 */
public class publishSubject1 {

    public void emit() {

        PublishSubject<String> subject = PublishSubject.create();



        //구독 이후에 발행된 값을 수신한다. 1, 3, 4
        subject.subscribe( data -> System.out.println("Subscriber #1 =>" + data ));

        subject.onNext("1");
        subject.onNext("3");


        //구독 이후에 발행된 값을 수신한다. 4
        subject.subscribe( data -> System.out.println("Subscriber #2 =>" + data ));

        subject.onNext("4");

        subject.onComplete();

    }

    public static void main(String[] args) {
        new publishSubject1().emit();
    }
}
