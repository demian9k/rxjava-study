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
public class asyncSubject1 {
    public void emit() {

        AsyncSubject<String> subject = AsyncSubject.create();

        subject.subscribe( data -> System.out.println("Subscriber #1 => " + data ) );

        subject.onNext("1");
        subject.onNext("2");
        subject.subscribe( data -> System.out.println("subscriber #2 => " + data) );
        subject.onNext("3");

        // 구독자들은  onComplete 시점에서 마지막으로 발행된 값 1개만 수신한다.
        subject.onComplete();
    }

    public static void main(String[] args) {
        new asyncSubject1().emit();
    }
}
