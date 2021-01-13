package com.atzhangkang.springcloud.tests.generics.tuple;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class Tuple3<A, B, C> extends Tuple2<A, B> {
    public final C a3;
    public Tuple3(A a, B b, C c) {
        super(a, b);
        a3 = c;
    }

    @Override
    public String rep() {
        return super.rep() + ", " + a3;
    }
}
