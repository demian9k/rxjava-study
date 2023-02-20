package io.demian.rxjava.operators.transform;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.Shape;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.observables.GroupedObservable;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *  key로 정해진 기준에 따라 단일 observable을 여러 개로 이루어진 GroupedObservable 로 만든다.
 */
public class groupBy {

    public void emit() {

//        String[] objs = {"6", "4", "2-T", "2", "6", "4-T"};
//
//        Observable<GroupedObservable<String,String>> source =
//                Observable.fromArray( objs ).groupBy( Shape::getShape );
//
//        source.subscribe( obj -> obj.subscribe(val -> System.out.println("GROUP:" + obj.getKey() + "\t Value: " + val)) );


        Observable<GroupedObservable<Integer,Integer>> source2 =
                Observable.range(1,10).groupBy( num -> num % 3 );

//        source2.subscribe( groupedObservable -> groupedObservable.toList().subscribe( item -> System.out.println(item) ));


        source2.subscribe( this::onNext );

        CommonUtils.sleep(2000);
    }

    public void onNext(GroupedObservable<Integer, Integer> groupedObservable) {
        onSuccess(groupedObservable.toList());
    }

    public void onSuccess(Single<List<Integer>> list) {
        list.subscribe( System.out::println );
    }

    public static void main(String[] args) {
        new groupBy().emit();
    }


}
