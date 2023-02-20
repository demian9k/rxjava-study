package io.demian.rxjava.schedulers.appliedExample;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenWeatherMapV2 {
    private static final String URL = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";


    public void run() {
        CommonUtils.exampleStart();

        /**
         * publish 는 ConnectableOnservable을 만들고, refCount는 ConnectableObservable을 cold Observable로 만드는데
         * 이 cold observable은 subscribe()시에 connect() 함수를 내포하고 있어
         * connect()의 실행 없이 데이터 발행(emit) 이 가능해진다.
         */
        Observable<String> source = Observable.just(URL)
                .map(OkHttpHelper::getWithLog)
                .subscribeOn(Schedulers.io())
//                .share()
                .publish().refCount() //두 함수의 조합은 share 와 동일하다.
                .observeOn(Schedulers.newThread());

        source.map(this::parseTemperature).subscribe(Log::it);
        source.map(this::parseCityName).subscribe(Log::it);
        source.map(this::parseCountry).subscribe(Log::it);

        CommonUtils.sleep(3000);
    }

    private String parseTemperature(String json) {
        return parse(json, "\"temp\":[0-9]*.[0-9]*");
    }

    private String parseCityName(String json) {
        return parse(json, "\"name\":\"[a-zA-Z]*\"");
    }

    private String parseCountry(String json) {
        return parse(json, "\"country\":\"[a-zA-Z]*\"");
    }

    private String parse(String json, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(json);
        if (match.find()) {
            return match.group();
        }
        return "N/A";
    }

    public static void main(String[] args) {
        OpenWeatherMapV2 demo = new OpenWeatherMapV2();
        demo.run();
    }
}
