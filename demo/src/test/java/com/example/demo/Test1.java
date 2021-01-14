package com.example.demo;

import com.example.demo.pojo.Order;
import com.example.demo.task.OrderTask;
import com.example.demo.utils.ExecutorsUtil;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Create by Hercules
 * 线程池的创建、监控
 * 2021-01-06 17:56
 */
public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorsUtil executorsUtil = new ExecutorsUtil(1, 2, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue(4), "订单线程", new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println("持久化到磁盘");
            }
        });
        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setTaskId("oderId-" + i);
            order.setTaskName("orderTask");
            order.setDesc("desc" + i);
            executorsUtil.execute(new OrderTask(order));
        }
        Thread.sleep(200);
        //可以通过disconf来修改
        executorsUtil.setCorePoolSize(2);
        executorsUtil.shutdown();

    }
}
