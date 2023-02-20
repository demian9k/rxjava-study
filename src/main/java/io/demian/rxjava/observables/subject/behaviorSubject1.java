package io.demian.rxjava.observables.subject;

import io.reactivex.subjects.BehaviorSubject;


/**
 *
 * behaviorSubject는 최근 값을 넘겨준다.
 * 값이 없는 경우 기본 값을 넘겨준다.
 *
 */
public class behaviorSubject1 {

    public void emit() {

        BehaviorSubject<String> subject = BehaviorSubject.createDefault("6");


        //처음부터 공유한 구독자는 기본값부터 발행된 모든 값을 수신한다.
        //6, 1, 3, 4
        subject.subscribe( data -> System.out.println("Subscriber #1 =>" + data ));

        subject.onNext("1");
        subject.onNext("3");

        //이 구독자는  구독시점 이전 마지막 값부터 수신한다.
        //3, 4
        subject.subscribe( data -> System.out.println("Subscriber #2 =>" + data ));

        subject.onNext("4");

        subject.onComplete();

    }

    public static void main(String[] args) {
        new behaviorSubject1().emit();
    }
}
