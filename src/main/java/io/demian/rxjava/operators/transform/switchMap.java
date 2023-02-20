package io.demian.rxjava.operators.transform;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

/**

 * concatMap에 비해 switchMap은 순서 보장을 위해 새 값이 발행되면 기존 진행 중이던 작업
 * 을 중단하고 마지막으로 들어온 값을 처리한다.
 */
public class switchMap {

    public void emit() {

        CommonUtils.exampleStart();

        String[] balls = {"1", "3", "5"};

        Observable<String> source = Observable.interval(100L, TimeUnit.MILLISECONDS )
                .map(Long::intValue)
//                .doOnNext( d ->  Log.dt(d) )
                .map(idx -> balls[idx] )
                .take(balls.length)
//                .doOnNext( d ->  Log.dt("doOnNext2 : " + d) )
                /**
                 * 위 작업들은 값을 발행하는데 사용하는 스레드를 사용하고
                 * 아래부터는 값을 구독자에게 값을 전달하는 스레드를 사용한다.
                 *
                 * --> 값을 발행하는 스레드와 값을 전달하는 스레드는 분리되어 동작한다.
                 */
                .flatMap(ball -> Observable.interval(200L, TimeUnit.MILLISECONDS)
                        .map(notUsed -> ball + "<>")
                        .take(2)
                );


        /**
         * ball 은 100m 간격으로 발행되고 다이아몬드는 200ms 간격으로 발행된다.
         * (1) 다이아가 발행되기 전 (5) ball이 발행된다.
         * 따라서 (3) 다이아 발행이 취소되고 마지막 (5) ball 을 이용해 5다이아몬드가 map 처리 된다.
         */

        /**
         * ball의 interval을 60ms, switchMap의 interval을 20ms로 맞춰주면
         * ball이 천천히 발행되어 60ms 내에 switchMap에서 처리할 시간이 충분하므로 concatMap을 사용한 것처럼 발행된다.
         */

        /**
         * flatMap으로 발행하면 interleaving 되어 순서 보장 없이 빠르게 전체가 발행되며
         * concatMap 으로 실행하면 concatMap interval의 지연에도 불구하고 전체가 순서대로 발행된다.
         */

        /**
         *   switchMap은  센서 값등을 얻어와서 처리하는 경우에 유용하다.
         *   센서 값은 중간 값보다 최종적인 값이 중요하여 처리하는 경우가 많기 때문에
         *   flatMap을 사용해서 매번 결과가 새로 나오는지 검사하지 않고 switchMap의 마지막 값 처리 특성을 사용할 수 있다.
         */
        //시간 확인은 여기서 한다.
        source.subscribe(Log::it);
        CommonUtils.sleep(2000);
    }

    public static void main(String[] args) {
        new switchMap().emit();
    }


}
