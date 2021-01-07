package com.example.demo;

import com.example.demo.utils.ExecutorsUtil;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Create by Hercules
 * 2021-01-06 17:56
 */
public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorsUtil executorsUtil = new ExecutorsUtil(5,10,5, TimeUnit.SECONDS,
                new ArrayBlockingQueue(15),"测试线程-");
        for (int i = 0; i < 10; i++) {
            executorsUtil.execute(()->{
                try {
                    Thread.sleep(300 + new Random().nextInt(10));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程名称:"+Thread.currentThread().getName());
            });
        }

        Thread.sleep(10000);
        executorsUtil.shutdown();
    }
}
