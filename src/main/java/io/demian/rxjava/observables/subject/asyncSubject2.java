package io.demian.rxjava.observables.subject;

import io.reactivex.subjects.AsyncSubject;


/**
 *
 * subject는 cold observable를 hot observable로 변환한다.
 * subject는 observable와 subscriber의 속성을 모두 가진다.
 * 데이터를 발행할수도 있고 subscriber처럼 데이터를 처리할 수 있다.
 *
 * asyncSubject는 observable 발행한 마지막 데이터를 얻어올 수 있는 subject이다.
 * 완료 전 마지막 데이터에만 관심을 두고, 이전 데이터를 무시한다.
 */
public class asyncSubject2 {
    public void emit() {

        AsyncSubject<Integer> subject = AsyncSubject.create();

        subject.onNext(10);
        subject.onNext(20);
        subject.subscribe( data -> System.out.println("Subscriber #1 => " + data ) );
        subject.onNext(30);

        subject.onComplete();

        //complete  호출 후에도 발행이 가능하지만 무시된다.
        subject.onNext(40);

        //구독자는 onComplete 이전의 값을 수신하게 된다.
        subject.subscribe( data -> System.out.println("subscriber #2 => " + data) );
        subject.subscribe( data -> System.out.println("subscriber #3 => " + data) );
    }

    public static void main(String[] args) {
        new asyncSubject2().emit();
    }
}
