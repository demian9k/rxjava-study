package io.demian.rxjava.operators.conditional;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * ambiguous( 모호한 ) 의 줄임말
 * 여러개의 observable 중 1개의 observable을 선택한다.
 */
public class amb {
    public void emit() {

        String[] data1 = {"1", "3", "5"};
        String[] data2 = {"2-R", "4-R"};

        List<Observable<String>> sources = Arrays.asList(
                Observable.fromArray(data1)
                        .doOnComplete( () -> Log.d("Observable #1 : onComplete)")),

                /**
                 * delay 메서드를 통해 100ms 늦게 값을 발행하는 observable을 생성한다.
                 */
                Observable.fromArray(data2)
                        .delay(100L, TimeUnit.MILLISECONDS)
                        .doOnComplete( () -> Log.d("Observable #2 : onComplete)"))
        );


        /**
         * amb 함수는 observable 목록을 입력받아 그 중 하나를 선택한다.
         * data1 observable이 먼저 발행되므로 data2 observable에서 발행되는 값은 무시된다.
         */
        Observable.amb(sources)
                .doOnComplete( () -> Log.d("Result : onComplete()"))
                .subscribe(Log::i);

        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        new amb().emit();
    }
}
