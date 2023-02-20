package io.demian.rxjava.observables;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

/**
 * Subject 클래스들과 동일하게 Cold Observable을 Hot Observable로 변환한다.
 * Observable을 여러 구독자에게 공유할 수 있으므로 원 데이터 하나를 여러 구독자에게 동시에 전달할 때 사용한다.
 *
 * 일반적인 observable과 다른 점은 subscribe 함수 호출 이후에 바로 데이터를 발행하지 않고
 * connect() 함수 호출 후에 발행된다.
 *
 * 또한 ConnectableObservable은 Observable의 publish 메서드를 통해 생성한다.
 */
public class connectableObservable {
    public void emit() {

        String[] dt = {"1", "2", "3"};

        Observable<String> balls = Observable
                .interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> dt[i])
                .take(dt.length);

        ConnectableObservable<String> source = balls.publish();

        //구독해도 데이터 발생하지 않음
        source.subscribe( data -> System.out.println("Subscriber #1 => " + data ));
        source.subscribe( data -> System.out.println("Subscriber #2 => " + data ));

        //connect() 이후 데이터 발생 시작
        //구독자 1,2는 이후 모든 데이터 수신
        source.connect();

        CommonUtils.sleep(250);

        //구독자3 은 데이터 3만 수신
        source.subscribe( data -> System.out.println("Subscriber #3 => " + data ));


        CommonUtils.sleep(150);

    }

    public static void main(String[] args) {
        new connectableObservable().emit();
    }
}
