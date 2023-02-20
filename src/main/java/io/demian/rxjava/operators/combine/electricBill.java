package io.demian.rxjava.operators.combine;


import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import org.apache.commons.lang3.tuple.Pair;

import java.text.DecimalFormat;

import static java.lang.Math.max;
import static java.lang.Math.min;


/**
 * zip 연산을 활용한 전기 요금 계산 예제
 *
 * 기본요금
 *   200kwh 이하 910
 *   201 ~ 400 1600
 *   400kwh 초과 사용 7300
 *
 * 전력량 요금(원/kwh)
 *  0 ~ 200kwh = 93.3
 *  200 ~ 400kwh = 187.9
 *  400 ~ = 280.6
 */
public class electricBill {

    public void emit() {
        String[] data = {
                "100", //910 + 93.3 * 100 = 10,240원
                "300", //1600 + 93.3 * 200 + 187.9 * 100 = 39,950원
                "800",  //7300 + 93.3 * 200 + 187.9 * 200 + 280.65 * 200 = 175,800원
        };


        /**
         *  기본요금
         *    200kwh 이하 910
         *    201 ~ 400 1600
         *    400kwh 초과 사용 7300
         */
        Observable<Integer> basePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                        if (val <= 200) return 910;
                        if (val <= 400) return 1600;
                        return 7300;
                    }
                );

        /**
         * * 전력량 요금(원/kwh)
         *  0 ~ 200kwh = 93.3
         *  200 ~ 400kwh = 187.9
         *  400 ~ = 280.6
         */
        Observable<Integer> usagePrice = Observable.fromArray(data)
                .map(Integer::parseInt)
                .map(val -> {
                   double series1 = min(200, val) * 93.3;
                   double series2 = min(200, max(val-200, 0)) * 187.9;
                   double series3 = max(0, max(val-400, 0)) * 280.65;
                   return (int)(series1 + series2 + series3);
                });

        /**
         * usage data 직접 접근을 위해 pair 사용
         */
        Observable<Pair<String, Integer>> source = Observable.zip(
                basePrice,
                usagePrice,
                Observable.fromArray(data),
                (v1, v2, i) -> Pair.of(i, v1+v2));


        /**
         * 가격 양식 변경
         */
        Observable<Pair<String, String>> pairs = source
                .map( pair -> Pair.of( pair.getLeft(), decimalFormat( pair.getRight())  ) );

        pairs.subscribe( pair -> {
            StringBuilder sb = new StringBuilder();
            sb.append("Usage : " + pair.getLeft() + "Kwh => ");
            sb.append("Price : " + pair.getRight() + "원");

            Log.i( sb.toString() );
        });

    }

    public Pair<String, Integer> dd(Pair<String, Integer> pair) {
       return pair;
    }

    public String decimalFormat(int value) {
        return new DecimalFormat("#,###").format(value);
    }

    public static void main(String[] args) {
        new electricBill().emit();
    }
}
