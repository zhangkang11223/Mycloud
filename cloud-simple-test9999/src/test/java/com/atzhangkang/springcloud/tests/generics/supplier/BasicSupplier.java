package com.atzhangkang.springcloud.tests.generics.supplier;

import java.util.function.Supplier;

/**
 * Supplier from a class with a no-arg constructor
 */
public class BasicSupplier<T> implements Supplier<T> {

    private Class<T> type;


    public BasicSupplier(Class<T> type) {
        this.type = type;
    }

    @Override
    public T get() {
        try {
            // Assumes type is a public class:
            return type.newInstance();
        } catch (InstantiationException |
                IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    // Produce a default Supplier from a type token:
    public static <T> Supplier<T> create(Class<T> type) {
        return new BasicSupplier<>(type);
    }
}

