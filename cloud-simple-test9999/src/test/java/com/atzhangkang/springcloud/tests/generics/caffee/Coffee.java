package com.atzhangkang.springcloud.tests.generics.caffee;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
