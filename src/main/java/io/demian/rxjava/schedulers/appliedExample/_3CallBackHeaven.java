package io.demian.rxjava.schedulers.appliedExample;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static io.demian.rxjava.common.CommonUtils.GITHUB_ROOT;

public class _3CallBackHeaven {

    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_heaven";


    /**
     * concat을 이용해 차례대로 url 호출
     */
    public void usingConcat() {
        CommonUtils.exampleStart();

        Observable<String> source = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get)
                .concatWith(Observable.just(SECOND_URL)
                        .map(OkHttpHelper::get));

        source.subscribe(Log::it);

        CommonUtils.sleep(5000);
        CommonUtils.exampleComplete();
    }

    /**
     * zip을 이용해 두 url 요청 후 zip으로 합치기
     */
    public void usingZip() {
        CommonUtils.exampleStart();


        /**
         * 첫번째 observable
         */
        Observable<String> first = Observable.just(FIRST_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);

        /**
         * 두번째 observable
         */
        Observable<String> second = Observable.just(SECOND_URL)
                .subscribeOn(Schedulers.io())
                .map(OkHttpHelper::get);

        /**
         * zipping first, second
         */
        Observable.zip(first, second,
                (a, b) -> ("\n>>" + a + "\n>>" + b))
                .subscribe(Log::it);

        CommonUtils.sleep(5000);
    }

    public static void main(String[] args) {
        new _3CallBackHeaven().usingConcat();

        new _3CallBackHeaven().usingZip();
    }


}
