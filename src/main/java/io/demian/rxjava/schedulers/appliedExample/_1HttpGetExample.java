package io.demian.rxjava.schedulers.appliedExample;

import io.demian.rxjava.common.CommonUtils;
import io.demian.rxjava.common.Log;
import okhttp3.*;

import java.io.IOException;
/*
    okhttp3로 http get request
 */
public class _1HttpGetExample {

    private final OkHttpClient client = new OkHttpClient();

    private static final String URL_README =
            "https://raw.githubusercontent.com/yudong80/reactivejava/master/README.md";

    private static final String URL = "https://jsonplaceholder.typicode.com/todos/1";
    private static final String WEATHER_URL = "https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22";


    public void run() {

        Request request = new Request.Builder()
                .url(URL_README)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.i("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(response.body().string());
                Log.i("onResponse");
            }
        });

        CommonUtils.exampleComplete();
    }

    public static void main(String[] args) {
        _1HttpGetExample demo = new _1HttpGetExample();
        demo.run();
    }
}
