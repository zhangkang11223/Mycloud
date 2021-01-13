package com.atzhangkang.springcloud.tests.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * thenApply : 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
 * thenApplyAsync
 */
public class ThenApplyTest {

    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(new Supplier<Long>() {
            @Override
            public Long get() {
                System.out.println("get method : " + Thread.currentThread().getName());
                long result = new Random().nextInt(100);
                System.out.println("result1="+result);
                return result;
            }
        }).thenApplyAsync(new Function<Long, Long>() {
            @Override
            public Long apply(Long t) {
                System.out.println("apply method: " + Thread.currentThread().getName());
                long result = t*5;
                System.out.println("result2="+result);
                return result;
            }
        });

        long result = future.get();
        System.out.println(result);
    }

    public static void main(String[] args) throws Exception {
        ThenApplyTest.thenApply();
    }
}
