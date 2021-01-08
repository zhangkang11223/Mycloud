package com.atzhangkang.springcloud.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class RandomList <T> extends ArrayList<T> {
    // 若种子数相同，则产生的随机数也相同
    private Random rand = new Random(47);

    public T select() {
        int i = rand.nextInt(size());
        return get(i);
    }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<>();
        rs.addAll(Arrays.asList("The quick brown fox jumped over the lazy brown dog".split(" ")));
        IntStream.range(0, 11).forEach(i -> System.out.println(rs.select()));
    }
}