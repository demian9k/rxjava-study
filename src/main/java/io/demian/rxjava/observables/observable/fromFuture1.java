package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class fromFuture1 {
    public void emit() {

        Callable c = () -> {
          Thread.sleep(1000);
          return "Hello Future";
        };

        Callable<String> callable2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello Future2";
            }
        };

        Future<String> future = Executors.newSingleThreadExecutor().submit(c);

        Observable<String> source = Observable.fromFuture(future);

        source.subscribe(data -> System.out.println(data));
    }

    public static void main(String[] args) {
        new fromFuture1().emit();
    }

}


