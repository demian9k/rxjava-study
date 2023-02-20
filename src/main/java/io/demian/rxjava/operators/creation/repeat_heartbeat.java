package io.demian.rxjava.operators.creation;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.OkHttpHelper;
import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


/**
 *
 */
public class repeat_heartbeat {
    public void emit() {

        CommonUtils.exampleStart();


        //호출하면 다른 문구들을 응답하는 API
        String url = "https://api.github.com/zen";

        /**
         * 2초 timer는 원래 1번 호출되어야 하지만 repeat에 의해 계속 반복되어 실행된다.
         * repeat 함수는 동작이 한번 끝난 다음 다시 구독하는 방식으로 동작한다.
         * 구독할때마다 동작하는 스레드 번호가 달라짐으로 확인할 수 있다.
         */
        Observable<String> source = Observable.timer(2, TimeUnit.SECONDS)
                .map(val -> url)
                .map(OkHttpHelper::get)
                .repeat();

        source.subscribe(res -> Log.it("Ping repeat Result :" + res));

        /**
         * 같은 스레드에서 동작시키려면 interval을 사용하면 된다.
         */
//        Observable.interval(2, TimeUnit.SECONDS)
//                .map(val -> url)
//                .map(OkHttpHelper::get)
//                .subscribe(res -> Log.it("Ping interval Result :" + res));;



        CommonUtils.sleep(100000);
    }

    public static void main(String[] args) {
        new repeat_heartbeat().emit();
    }
}
