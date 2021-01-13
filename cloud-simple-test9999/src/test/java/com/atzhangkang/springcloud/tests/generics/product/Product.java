package com.atzhangkang.springcloud.tests.generics.product;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

/**
 * @author tule
 * @version 1.0
 * @date 2021/1/8
 */
public class Product {
    private final int id;
    private String description;
    private double price;

    Product(int idNumber, String descr, double price) {
        id = idNumber;
        description = descr;
        this.price = price;
        System.out.println(toString());
    }

    @Override
    public String toString() {
        return id + ": " + description + ", price: $" + price;
    }

    public void priceChange(double change) {
        price += change;
    }
    public static Supplier<Product> generator = new Supplier<Product>() {
        private Random rand = new Random(47);

        @Override
        public Product get() {
            return new Product(rand.nextInt(1000), "Test",
                Math.round(rand.nextDouble() * 1000.0) + 0.99);
        }
    };
}

class Shelf extends ArrayList<Product> {
/*
    Shelf(int nProducts) {
        Suppliers.fill(this, Product.generator, nProducts);
    }
*/
}