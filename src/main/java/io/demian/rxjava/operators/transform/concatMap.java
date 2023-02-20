package io.demian.rxjava.operators.transform;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**
 * timer 와 유사하지만 데이터 흐름의 생성을 구독할때까지 지연할 수 있다.
 *
 *
 *
 */
public class concatMap {

    public void emit() {

        CommonUtils.exampleStart();

        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS )
//                .doOnNext( System.out::println )
                .map(Long::intValue)
                .map(idx -> balls[idx] )
                .take(balls.length)

                //interleaving 허용 더 빠르다
//                .flatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
//                        .map(notUsed -> ball + "<>")
//                        .take(2)
//                );

//                순서 보장 flatMap 보다 느리다.
                .concatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2)
                );


        //시간 확인은 여기서 한다.
        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    public static void main(String[] args) {
        new concatMap().emit();
    }


}
