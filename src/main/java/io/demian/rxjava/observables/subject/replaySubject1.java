package io.demian.rxjava.observables.subject;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;


/**
 *
 * ReplaySubject 처음부터 발행된 데이터를 모아서 새 구독자가 모두 수신할 수 있도록 한다.
 */
public class replaySubject1 {

    public void emit() {

        ReplaySubject<String> subject = ReplaySubject.create();



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
        new replaySubject1().emit();
    }
}
