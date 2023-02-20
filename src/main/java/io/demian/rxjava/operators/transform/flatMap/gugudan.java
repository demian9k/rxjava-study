package io.demian.rxjava.operators.transform.flatMap;

import io.demian.rxjava.common.Log;
import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;

public class gugudan {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("input:");

        int dan = Integer.parseInt(in.nextLine());

        for(int row = 1; row <= 9 ; ++row ) {
            System.out.println(dan + "*" + row + " = " + dan * row);
        }

    }
}
