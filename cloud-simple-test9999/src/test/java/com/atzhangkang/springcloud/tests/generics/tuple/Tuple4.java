package com.atzhangkang.springcloud.tests.generics.tuple;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class Tuple4<A, B, C, D>
        extends Tuple3<A, B, C> {
    public final D a4;
    public Tuple4(A a, B b, C c, D d) {
        super(a, b, c);
        a4 = d;
    }

    @Override
    public String rep() {
        return super.rep() + ", " + a4;
    }
}