package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by Hercules
 * 2021-01-07 11:05
 */
public class Test0 {

    public static void main(String[] args) {
        ExecutorService service1 = Executors.newFixedThreadPool(10);
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        ExecutorService service3 = Executors.newCachedThreadPool();
        ExecutorService service4 = Executors.newScheduledThreadPool(10);

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            service1.execute(()->{
                System.out.println(finalI);
            });
        }

    }
}
