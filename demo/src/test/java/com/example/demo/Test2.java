package com.example.demo;

import java.util.concurrent.*;

/**
 * Create by Hercules
 * 处理执行时间过长的任务
 * 2021-01-06 17:37
 */
public class Test2 {

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<?> future = service.submit(() -> {
            System.out.println("start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        try {
            future.get(4, TimeUnit.SECONDS);
            System.out.println("end...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
