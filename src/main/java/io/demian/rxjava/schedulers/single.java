package io.demian.rxjava.schedulers;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.checkerframework.checker.units.qual.A;

/**
 *
 *
 *
 * 단일 스레드를 생성해 subscription 작업 처리
 *
 * 싱글톤이므로 Schedulers.single() 여러번 생성해도 인스턴스는 동일하다.
 */
public class single {
    public void emit() {

        Observable<Integer> numbers = Observable.range(100, 5);
        Observable<String> chars = Observable.range(0,5).map(CommonUtils::numberToAlphabet);

        //Observable A
        Observable<Integer> A = numbers.subscribeOn(Schedulers.single());

        //Observable B
        Observable<String> B = chars.subscribeOn(Schedulers.single());

        /**
         * 큐에 작업을 넣은 후 시작하므로 A, B 순서가 바뀌는 일은 없다.
         */
        A.subscribe(Log::i);
        B.subscribe(Log::i);

        CommonUtils.sleep(500);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new single().emit();
        new single().emit();
        new single().emit();
        new single().emit();
        new single().emit();
        new single().emit();
        new single().emit();
    }
}
