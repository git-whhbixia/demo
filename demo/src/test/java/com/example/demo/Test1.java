package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Create by Hercules
 * 2021-01-06 17:37
 */
public class Test1 {

    public static void main(String[] args) {

        ExecutorService service = Executors.newSingleThreadExecutor();
        Runnable task = () -> {
            System.out.println("start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Future<?> future = service.submit(task);
        try {

//            future.get(4, TimeUnit.SECONDS);
            System.out.println("end...");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
