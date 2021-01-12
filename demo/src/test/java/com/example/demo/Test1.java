package com.example.demo;

import com.example.demo.pojo.Order;
import com.example.demo.utils.ExecutorsUtil;

import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Create by Hercules
 * 2021-01-06 17:56
 */
public class Test1 {

    public static void main(String[] args) {
        ExecutorsUtil executorsUtil = new ExecutorsUtil(2,4,5, TimeUnit.SECONDS,
                new ArrayBlockingQueue(4),"订单线程");
        for (int i = 0; i < 5; i++) {
            Order order = new Order();
            order.setTaskId("oderId-"+ UUID.randomUUID().toString());
            order.setTaskName("orderTask");
            order.setDesc("desc"+i);
            executorsUtil.execute(new OrderTask(order));
        }
        executorsUtil.shutdown();

    }
}
