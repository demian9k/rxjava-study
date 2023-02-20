package io.demian.rxjava;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.BackpressureOverflowStrategy;
import io.reactivex.Flowable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/*
    일반적인 동역학에서의 backpressure의 정의는
    파이프를 통해 원하는 방향으로의 유체 흐름과 반대로 작용하는 힘이나 저항을 의미한다.

    소프트웨어에서는 의미가 조금 다르게 사용된다. 흐르는 것은 업스트림 컴포넌트에서 다운스트림 컴포넌트로 향하는 데이터이고
    다운스트림 컴포넌트의 데이터 처리 속도가 업스트림 컴포넌트에서 보내는 것보다 느릴 때 BackPressure 가 있다고 하고

    결국 데이터를 처리하지 못해 흘러 넘치는 것을 overflow
    그것을 어떻게 대응할 것인지가 BackPressure overflow strategy 이다.

    rxjava 에서는 backpressure는 아래와 같은 전략을 사용할 수 있다.
 */
public class BackpressureExample {
    public void makeBackpressure() {
        CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함

        PublishSubject<Integer> subject = PublishSubject.create();
        subject
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    //100ms후 데이터를 처리함
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.e(err.toString()));

        //뜨거운 Observable로 50,000,000개의 데이터를 연속적으로 발행함
        for (int i=0; i< 50_000_000; ++i) {
            subject.onNext(i);
        }
        subject.onComplete();
        CommonUtils.exampleComplete();
    }

    /*
        buffer를 이용한 backpressure 제어
     */
    public void usingBuffer() {
        CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함

        Action actionOnOverflow = () -> System.out.println("actionOnOverflow " );

        /*
            버퍼 오버플로우시 전략

            ERROR	오류를 출력한다.
            DROP_OLDEST	버퍼에서 가장 오래된 것을 삭제한다.
            DROP_LATEST	버퍼에서 가장 최근 데이터를 삭제한다
         */
        Flowable.range(1, 50_000_000)
                .onBackpressureBuffer(128, actionOnOverflow, BackpressureOverflowStrategy.DROP_OLDEST)
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    //100ms후 데이터를 처리함
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.e(err.toString()));
        CommonUtils.exampleComplete();
    }

    /*
        백프레셔 발생시 데이터 버리기(drop)
    */
    public void usingDrop() {
        CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함

        Flowable.range(1, 50_000_000)
                .onBackpressureDrop(i -> System.out.println("Dropped " + i)) //버린다.
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    //100ms후 데이터를 처리함
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.e(err.toString()));

        CommonUtils.sleep(20_000);
        CommonUtils.exampleComplete();
    }

    /*
        백프레셔 발생시 최신값만 가져오기
        버퍼의 변형으로 방출된 이전 값은 모두 제거한다.
    */
    public void usingLatest() {
        CommonUtils.exampleStart(); //시간을 측정하기 위해 호출함

        Flowable.range(1, 50_000_000)
                .onBackpressureLatest()
                .observeOn(Schedulers.computation())
                .subscribe(data -> {
                    //100ms후 데이터를 처리함
                    CommonUtils.sleep(100);
                    Log.it(data);
                }, err -> Log.e(err.toString()));

        CommonUtils.sleep(20_000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        BackpressureExample demo = new BackpressureExample();
        demo.makeBackpressure();
//		demo.usingBuffer();
//		demo.usingDrop();
//		demo.usingLatest();
    }
}
