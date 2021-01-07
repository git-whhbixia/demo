package com.example.demo.pojo;

/**
 * Create by Hercules
 * 2021-01-07 10:33
 */
public class Order extends Task{

    private String desc;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Order{" +
                "desc='" + desc + '\'' +
                "} " + super.toString();
    }
}
