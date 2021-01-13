package com.atzhangkang.springcloud.tests.generics.fibonacci;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class MyFibonacciTest implements Supplier<Integer> {
    public Integer count = 0;

    @Override
    public Integer get() {
        return getFib(count++);
    }

    public Integer getFib(Integer index) {
        if (index < 2) {
            return 1;
        } else {
            return getFib(index -2) + getFib(index -1 );
        }
    }

    public static void main(String[] args) {
        // 获取前x位数字
        Stream.generate(new MyFibonacciTest()).limit(5).forEach(System.out::print);
        MyFibonacciTest myFibonacciTest = new MyFibonacciTest();

        // 获取第几位数字
        Integer fib = myFibonacciTest.getFib(5);
        System.out.println(fib);
    }
}
