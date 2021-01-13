package com.example.demo;

import com.example.demo.pojo.Order;

/**
 * Create by Hercules
 * 2021-01-07 10:34
 */
public class OrderTask implements Runnable {

    private Order order;

    public OrderTask(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String taskId = order.getTaskId();
//        System.out.println("订单信息：" + order);
    }

    public Order getOrder() {
        return order;
    }

}
