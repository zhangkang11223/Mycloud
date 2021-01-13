package com.atzhangkang.springcloud.tests.generics.tuple;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class Tuple5<A, B, C, D, E>
        extends Tuple4<A, B, C, D> {
    public final E a5;
    public Tuple5(A a, B b, C c, D d, E e) {
        super(a, b, c, d);
        a5 = e;
    }

    @Override
    public String rep() {
        return super.rep() + ", " + a5;
    }
}