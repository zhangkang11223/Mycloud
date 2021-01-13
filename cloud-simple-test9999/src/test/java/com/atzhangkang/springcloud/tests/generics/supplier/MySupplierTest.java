package com.atzhangkang.springcloud.tests.generics.supplier;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class MySupplierTest implements Supplier<Integer> {
    private int count = 111;

    @Override
    public java.lang.Integer get() {
        return count;
    }

    public static void main(String[] args) {

        List<Integer> collect = Stream.generate(new MySupplierTest()).limit(5).collect(Collectors.toList());

    }
}
