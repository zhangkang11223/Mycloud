package com.atzhangkang.springcloud.tests.generics.caffee;

import java.util.Iterator;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 集成Supplier这个接口，相当于创建一个集合，集合的值是重写的get()方法的返回值
 * 可以搭配Iterable接口，重写迭代器的next接口返回这个集合中的一个对象
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class CoffeeSupplier implements Supplier<Coffee>, Iterable<Coffee>  {

    private Class<?>[] types = { Latte.class, Mocha.class,
            Cappuccino.class, Americano.class, Breve.class };

    private static Random rand = new Random(47);

    public CoffeeSupplier() {}

    // For iteration:
    private int size = 0;

    public CoffeeSupplier(int sz) {
        size = sz;
    }

    @Override
    public Coffee get() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;
        @Override
        public boolean hasNext() { return count > 0; }
        @Override
        public Coffee next() {
            count--;
            return CoffeeSupplier.this.get();
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        Stream.generate(new CoffeeSupplier()).limit(5).forEach(System.out::println);
        System.out.println("=================================");
        CoffeeSupplier coffees = new CoffeeSupplier(5);
        for (Coffee c : coffees) {
            System.out.println(c);
        }
    }
}