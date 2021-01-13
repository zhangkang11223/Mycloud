package com.atzhangkang.springcloud.tests.async;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * whenComplete：是执行当前任务的线程执行继续执行 whenComplete 的任务。
 * whenCompleteAsync：是执行把 whenCompleteAsync 这个任务继续提交给线程池来进行执行。
 *
 */
public class WhenCompleteTest {

    public static void whenComplete() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            System.out.println("whenComplete method :" + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ignored) {
            }
            if(new Random().nextInt()%2>0) {
                int i = 12/0;
            }
            System.out.println("run end ...");
        });

        future.whenCompleteAsync(new BiConsumer<Void, Throwable>() {
            /**
             * 入参t : future的返回值
             */
            @Override
            public void accept(Void t, Throwable action) {
                System.out.println("accept method :" + Thread.currentThread().getName());
                System.out.println("执行完成！");
            }

        });
        future.exceptionally(new Function<Throwable, Void>() {
            @Override
            public Void apply(Throwable t) {
                System.out.println("apply method :" + Thread.currentThread().getName());
                System.out.println("执行失败！"+t.getMessage());
                return null;
            }
        });

        TimeUnit.SECONDS.sleep(2);
    }

    public static void main(String[] args) throws Exception {
        WhenCompleteTest.whenComplete();
    }
}