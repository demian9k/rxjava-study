package io.demian.rxjava.observables.maybe;


import io.reactivex.Maybe;
import io.reactivex.Observable;

/**
 * Single 클래스와 마찬가지로 최대 데이터 하나를 가질 수 있지만
 * 0개일 가능성을 가진다.
 * 데이터 발행 없이 바로 데이터 발생을 완료할 수 있다.
 *
 * Maybe는 Single 클래스에 onComplete 이벤트가 추가된 형태이다.
 */
public class maybe {
    public void emit() {

        Observable<String> source= Observable.just("A","B", "C");


        //index로 참조
        Maybe<String> maybe1 = source.elementAt(1);

        //처음
        Maybe<String> maybe2 = source.firstElement();

//        Maybe<String> maybe3 = source.flatMapMaybe( d -> d );

        //마지막
        Maybe<String> maybe4 = source.lastElement(); //C

        Maybe<String> maybe5 = source.reduce((a, b) -> a+b); //AB

        Maybe<String> maybe6 = source.singleElement();
    }

    public static void main(String[] args) {
        new maybe().emit();
    }
}
