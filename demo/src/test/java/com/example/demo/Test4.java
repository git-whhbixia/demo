package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Create by Hercules
 * 2021-01-13 19:29
 */
public class Test4 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        executorService.execute(() -> {
//            try {
                int i = 1 / 0;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        });
        Future<?> future = executorService.submit(() -> {
           throw new RuntimeException("当线程池抛出异常后继续新的任务");
        });
        try {
            Object o = future.get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
