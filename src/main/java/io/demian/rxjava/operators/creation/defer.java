package io.demian.rxjava.operators.creation;

import hu.akarnokd.rxjava2.string.StringObservable;
import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.annotations.SchedulerSupport;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * timer 와 유사하지만 데이터 흐름의 생성을 구독할때까지 지연할 수 있다.
 *
 */
public class defer {

    //숫자 목록
    Iterator<String > colors = Arrays.asList("1", "3", "5", "6").iterator();

    public void emit() {

        Callable<Observable<String>> supplier = () -> getObservable();

//      @SchedulerSupport(SchedulerSupport.NONE)
        Observable<String> source = Observable.defer( supplier );


        //1번째 구독
        source.subscribe( val -> Log.i("Subscriber #1: " + val ));

        //2번째 구독
        source.subscribe( val -> Log.i("Subscriber #2: " + val ));

        //3번째 구독
        source.subscribe( val -> Log.i("Subscriber #3: " + val ));

        //4번째 구독
        source.subscribe( val -> Log.i("Subscriber #4: " + val ));

        //5번째 구독 -- oberservable.empty를 받게 되어 실행되지 않음.
        source.subscribe( val -> Log.i("Subscriber #5: " + val ));


        //------ 텍스트 생성
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new defer().emit();
    }

    //color 한개당 3개의 shape를 연결
    //번호 적인 도형을 발행하는 observable을 생성한다.
    public Observable<String> getObservable() {
        if(colors.hasNext()) {
            String color = colors.next();
            return Observable.just(
                    Shape.getString(color, Shape.BALL),
                    Shape.getString(color, Shape.RECTANGLE),
                    Shape.getString(color, Shape.PENTAGON)
            );
        }

        return Observable.empty();
    }
}
