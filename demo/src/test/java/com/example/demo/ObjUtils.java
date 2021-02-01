package com.example.demo;

import com.example.demo.pojo.Order;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjUtils {

    public static void writeObj(Order order) throws IOException {
        //1,明确存储对象的文件。
        String path = "D:/tempfile/";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(path + "\\" + order.getTaskId());
        //2，给操作文件对象加入写入对象功能。
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        //3，调用了写入对象的方法。
        oos.writeObject(order);
        //关闭资源。
        oos.close();
    }

    public static List<Order> readObj() throws IOException, ClassNotFoundException {
        List<Order> orders = new ArrayList<>();
        String prefixPath = "D:\\tempfile";
        File file = new File(prefixPath);
        File[] files = file.listFiles();
        if (files == null || files.length == 0) {
            return orders;
        }
        for (File fileInfo : files) {
            //1,定义流对象关联存储了对象文件。
            FileInputStream fis = new FileInputStream(fileInfo.getPath());
            //2,建立用于读取对象的功能对象。
            ObjectInputStream ois = new ObjectInputStream(fis);
            Order obj = (Order) ois.readObject();
            orders.add(obj);
            File delete = new File(prefixPath + "\\" + obj.getTaskId());
            delete.delete();
            System.out.println("删除成功," + delete.getPath());
        }
        return orders;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Order order = new Order();
        order.setTaskId("oderId-1");
        order.setTaskName("orderTask");
        order.setDesc("desc1");
        writeObj(order);
        List<Order> orders = readObj();
        System.out.println(orders);

        new File("D:\\tempfile\\oderId-1").delete();
    }

}