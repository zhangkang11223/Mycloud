package com.atzhangkang.springcloud.tests.generics.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class Sets {
    /**
     * 返回一个包含两个参数并集的 Set
     */
    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 返回一个包含两个参数集合交集的 Set
     */
    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    /**
     * 返回所有不在交集中的元素的 Set
     * Reflexive--everything not in the intersection:
     */
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }

    /**
     * 从 superset 中减去 subset 后的元素
     * Subtract subset from superset:
     */
    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
        Set<T> result = new HashSet<>(superset);
        result.removeAll(subset);
        return result;
    }
}
