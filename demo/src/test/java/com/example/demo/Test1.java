package com.example.demo;

import com.example.demo.pojo.Order;
import com.example.demo.task.OrderTask;
import com.example.demo.utils.ExecutorsUtil;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
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
        ExecutorsUtil executorsUtil = new ExecutorsUtil(2, 4, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue(4), "订单线程", new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (r instanceof OrderTask) {
                    System.err.println("持久化到磁盘,OrderTask类型");
                    try {
                        ObjUtils.writeObj(((OrderTask) r).getOrder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        new Thread(() -> {
            while (true) {
                try {
                    List<Order> orders = ObjUtils.readObj();
                    if (CollectionUtils.isEmpty(orders)) {
                        Thread.sleep(1000);
                    }
                    System.out.println("获取磁盘数据写入线程池 size" + orders.size());
                    for (Order order : orders) {
                        executorsUtil.execute(new OrderTask(order));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }).start();
        for (int i = 0; i < 20; i++) {
            Order order = new Order();
            order.setTaskId("oderId-" + i);
            order.setTaskName("orderTask");
            order.setDesc("desc" + i);
            OrderTask orderTask = new OrderTask(order);
            executorsUtil.execute(orderTask);
        }
        Thread.sleep(200);
        //可以通过disconf来修改
//        executorsUtil.setCorePoolSize(2);
        executorsUtil.shutdown();

    }
}
