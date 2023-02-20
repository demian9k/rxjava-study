package io.demian.rxjava.schedulers.appliedExample;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import io.demian.rxjava.common.OkHttpHelper;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class weatherApi {

//    private static final String URL = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";
    private static final String URL = "https://jsonplaceholder.typicode.com/todos/1";


    public void run() {

        Observable<String> urlSource = Observable.just(URL)
                                .map(OkHttpHelper::getWithLog)
                        .subscribeOn( Schedulers.io() );

        Observable<String> title = urlSource.map( this::parseTitle );
        Observable<String> completed = urlSource.map( this::parseCompleted );

        CommonUtils.exampleStart();

        /**
         * CONCAT
         */
        Observable<String> concatenatedSource = Observable
                .concat( title, completed )
                .observeOn(Schedulers.newThread());
        /**
         * subscribe
         */
        concatenatedSource.subscribe(Log::it);
//
        CommonUtils.sleep(10000);

    }

    private String parseCompleted(String json) {
        return parse(json, "\"completed\":\"[a-zA-Z]*\"");
    }

    private String parseTitle(String json) {
        return parse(json, "\"title\":\"[a-zA-Z]*\"");
    }


    public String parse(String json, String regex) {

        Pattern pattern = Pattern.compile(regex);

        Matcher match = pattern.matcher(json);

        if( match.find() ) {
            return match.group();
        }

        return "";
    }

    public static void main(String[] args) {
        weatherApi demo = new weatherApi();
        try {
            demo.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



