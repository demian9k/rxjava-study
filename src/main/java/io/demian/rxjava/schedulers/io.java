package io.demian.rxjava.schedulers;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import org.checkerframework.checker.units.qual.A;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 *
 * I/O 작업을 실행하기 위한 스케줄러
 * IO 스케줄러는 필요할 떄 스케줄러를 계속 생성한다.
 *
 * 입출력 작업은 비동기로 실행되지만 결과를 얻기까지 대기 시간이 길다.
 *
 * I/O 작업 : network request, file I/O DB Query
 */
public class io {
    public void emit() {

        String root = "c:\\";

        File[] files = new File(root).listFiles();

        Observable<String> source = Observable.fromArray(files)
                .filter( f -> !f.isDirectory() ) //파일만 걸러낸다.
                .map( f -> f.getAbsolutePath() )
                .subscribeOn( Schedulers.io() ); //데이터 흐름 발생 스레드 스케줄러를 io 스케줄러로 지정한다.

        source.subscribe(Log::i);

        CommonUtils.sleep(1000);
        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        new io().emit();
        new io().emit();
        new io().emit();
        new io().emit();
        new io().emit();
        new io().emit();
        new io().emit();
    }
}
