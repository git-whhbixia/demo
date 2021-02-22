package com.example.demo;

import com.example.demo.utils.ExecutorsUtil;

import java.util.concurrent.*;

/**
 * Create by Hercules
 * 默认的4种创建线程池的方式
 * 2021-01-07 11:05
 */
public class Test0 {

    public static void main(String[] args) {
        ExecutorService service1 = Executors.newFixedThreadPool(10);
        ExecutorService service2 = Executors.newSingleThreadExecutor();
        ExecutorService service3 = Executors.newCachedThreadPool();
        ExecutorService service4 = Executors.newScheduledThreadPool(10);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue(5),new ThreadPoolExecutor.CallerRunsPolicy());

    }
}
