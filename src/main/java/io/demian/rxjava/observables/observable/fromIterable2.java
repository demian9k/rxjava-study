package io.demian.rxjava.observables.observable;

import io.demian.rxjava.common.Order;
import io.reactivex.Observable;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class fromIterable2 {
    public void emit() {

        BlockingQueue<Order> orderQueue = new ArrayBlockingQueue<>(100);
        orderQueue.add(new Order("ORD-1"));
        orderQueue.add(new Order("ORD-2"));
        orderQueue.add(new Order("ORD-3"));


        Observable<Order> source = Observable.fromIterable(orderQueue);

        source.subscribe(order -> System.out.println(order.getId()));
    }

    public static void main(String[] args) {
        new fromIterable2().emit();
    }

}


