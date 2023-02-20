package io.demian.rxjava.observables;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;

/**

package com.pandora.rxandroid.activities;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.pandora.rxandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


//memory leak이 있는 예제
//참조가 완료되었지만 할당한메모리를 해제하지않아서 발생한다.
//특히 강한 참조의 경우 가비지 컬렉터가 메모리에서 객체를 제거할 수 없으므로
//라이프사이클에 맞게 참조를 끊어야 사용하지 않는 메모리를 해제할 수 있다.
public class DisposableExampleActivity extends Activity {

    public static final String TAG = HelloActivity.class.getSimpleName();

    @BindView(R.id.textView) TextView textView;

    private Disposable mDisposable;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        DisposableObserver<String> observer = new DisposableObserver<String>() {
            @Override
            public void onNext(String s) {


//                Observable은 안드로이드 컨텍스트를 복사하여 유지한다.
//                onComplete(), onError() 함수가 호출되면 내부에서 자동으로 unsubscribe() 함수를 유지합니다.
//                 그런데 구독자가 텍스트뷰를 참조하기 때문에 액티비티가 비정상적으로 종료되면
//                 텍스트뷰가 참조하는 액티비티는 종료해도 가비지 컬렉션의 대상이 되지 못하고
//                  메모리 누수가 발생하게 된다.

                textView.setText(s);
            }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onComplete() { }
        };

        mDisposable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("Hello world!");
                e.onComplete();
            }
        }).subscribeWith(observer);
    }


    @Override
    protected void onDestroy() {
         // activity destory 시에 disposal 객체를 검사해서 메모리 참조를 해재한다.
        super.onDestroy();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
**/