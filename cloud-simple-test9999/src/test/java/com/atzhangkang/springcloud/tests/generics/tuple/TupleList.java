package com.atzhangkang.springcloud.tests.generics.tuple;

import java.util.ArrayList;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class TupleList<A, B, C, D>  extends ArrayList<Tuple4<A, B, C, D>> {

    public static void main(String[] args) {
        TupleList<Vehicle, Amphibian, String, Integer> tl = new TupleList<>();
        tl.add(TupleTest.h());
        tl.add(TupleTest.h());
        tl.forEach(System.out::println);
    }
}
