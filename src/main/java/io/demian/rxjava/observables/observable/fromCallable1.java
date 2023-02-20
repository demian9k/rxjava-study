package io.demian.rxjava.observables.observable;

import io.reactivex.Observable;

import java.util.concurrent.Callable;

public class fromCallable1 {
    public void emit() {

        Callable<String> callable = () -> {
          Thread.sleep(1000);
          return "Hello Callable";
        };

//        Callable<String> callable2 = new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "Hello Callable";
//            }
//        };

        Observable<String> source = Observable.fromCallable(callable);

        source.subscribe(data -> System.out.println(data));
    }

    public static void main(String[] args) {
        new fromCallable1().emit();
    }

}


