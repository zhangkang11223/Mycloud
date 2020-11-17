package com.atzhangkang.springcloud.service;

import com.atzhangkang.springcloud.entities.exception.BizException;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

@Service
public class SimpleTestService {


    public void testException() throws InterruptedException {
        System.out.println("enter method testException .....");

        Callable callable = () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("111111111111111111111111");
            throw new BizException(10001, "token expired");

        };

        Thread thread1 = new Thread(new FutureTask<Void>(callable));
        thread1.start();
        System.out.println("2222222222222");
        thread1.join();


    }
}
