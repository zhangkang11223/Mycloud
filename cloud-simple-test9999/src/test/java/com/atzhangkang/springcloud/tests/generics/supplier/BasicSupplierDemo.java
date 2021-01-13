package com.atzhangkang.springcloud.tests.generics.supplier;

import java.util.stream.Stream;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class BasicSupplierDemo {
    public static void main(String[] args) {
        Stream.generate(BasicSupplier.create(CountedObject.class))
            .limit(5).forEach(System.out::println);
    }
}