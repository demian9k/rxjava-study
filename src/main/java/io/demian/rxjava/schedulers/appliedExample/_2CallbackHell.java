package io.demian.rxjava.schedulers.appliedExample;

import io.demian.rxjava.common.Log;
import okhttp3.*;

import java.io.IOException;

import static io.demian.rxjava.common.CommonUtils.GITHUB_ROOT;

/**
 * 2개의 http request를 순서대로 호출하는 일반적인 callback 구조의 코드
 * RxJava를 통해 개선하기 전 코드의 예가 된다.
 */
public class _2CallbackHell {
    private static final String FIRST_URL = "https://api.github.com/zen";
    private static final String SECOND_URL = GITHUB_ROOT + "/samples/callback_hell";

    private final OkHttpClient client = new OkHttpClient();

    private Callback onSuccess = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            e.printStackTrace();
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            Log.i(response.body().string());
        }
    };

    public void run() {
        Request request = new Request.Builder()
                .url(FIRST_URL)
                .build();
        /**
         *  첫번째 request
         */
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(response.body().string());

                /**
                 *  두번째 request
                 */
                Request request = new Request.Builder()
                        .url(SECOND_URL)
                        .build();

                client.newCall(request).enqueue(onSuccess);
            }
        });
    }

    public static void main(String[] args) {
        _2CallbackHell demo = new _2CallbackHell();
        demo.run();
    }
}