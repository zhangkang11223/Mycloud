package com.atzhangkang.springcloud.tests.generics.supplier;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class CountedObject {
    private static long counter = 0;
    private final long id = counter++;

    public long id() {
        return id;
    }

    @Override
    public String toString() {
        return "CountedObject " + id;
    }
}
