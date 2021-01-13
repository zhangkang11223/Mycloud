package com.atzhangkang.springcloud.tests.generics.methods;

import java.util.ArrayList;
import java.util.List;

/**
 * 变长参数和泛型方法
 * 泛型方法和变长参数列表可以很好地共存
 * @SafeVarargs 是jdk1.7引入的适用于可变参数与泛型能够更好结合的一个注解
 * 如果你认为你的方法或者构造方法是类型安全的，那么你也就可以使用@SafeVarargs来
 * 跳过@SuppressWarnings("unchecked")检查。
 */
public class GenericVarargs {
    /**
     * makeList() 方法产生的功能与标准库的 java.util.Arrays.asList() 方法相同。
     */
    @SafeVarargs
    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<>();
        for (T item : args)
            result.add(item);
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls = makeList("A", "B", "C");
        System.out.println(ls);
        ls = makeList("ABCDEFFHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}
