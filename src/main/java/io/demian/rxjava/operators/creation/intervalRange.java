package io.demian.rxjava.operators.creation;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

public class intervalRange {
    public void emit() {

        CommonUtils.exampleStart();

        //일정 시간 간격으로 n 개만큼의 값만 생성한다.
        //start, count, initialDelay, period, unit
        Observable<Long> source = Observable
                .intervalRange(1, 5, 0, 100, TimeUnit.MILLISECONDS);

//        Observable<Long> source2 = Observable
//                .interval(100, TimeUnit.MILLISECONDS)
//                .map(val -> val + 1)
//                .take(5);

        source.subscribe(Log::i);
        //주석 처리하면 스레드에서 할일이 없어 종료된다?
        //메인 스레드가 먼저 종료되서 시작되지 않는듯..
        CommonUtils.sleep(10000);

    }

    public static void main(String[] args) {
        new intervalRange().emit();
    }
}
