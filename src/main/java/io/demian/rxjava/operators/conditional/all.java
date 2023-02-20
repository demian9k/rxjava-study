package io.demian.rxjava.operators.conditional;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 *  주어진 조건에 맞는 데이터가 발행되면 true를 발행하고, 아니면 false를 발행한다.
 */
public class all {
    public void emit() {

        String[] data1 = {"1", "2", "3", "4"};

        /**
         * suffix 없이 getShape를 하면 ball이 반환되므로 all은 true를 반환한다.
         */
        Single<Boolean> source = Observable.fromArray(data1)
                .map(Shape::getShape)
                .all(Shape.BALL::equals);

        source.subscribe( (d) -> Log.i(d));


        CommonUtils.sleep(1000);
    }

    public static void main(String[] args) {
        new all().emit();
    }
}
