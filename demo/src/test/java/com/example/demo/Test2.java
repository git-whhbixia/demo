package com.example.demo;

import com.example.demo.pojo.Task1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Create by Hercules
 * 线程池提交方式 execute submit区别
 * 2021-01-06 17:56
 */
public class Test2 {

    static ExecutorService pool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
//        execute("Task1");
        submit("Task2");
    }

    public static void execute(String taskName) {
        //没有返回值。可以执行任务，但无法判断任务是否成功完成。
        pool.execute(new Task1(taskName));
    }

    public static void submit(String taskName) {
        //返回一个future。可以用这个future来判断任务是否成功完成
        Future future = pool.submit(new Task1(taskName));

        try {
            if (future.get() == null) {//如果Future's get返回null，任务完成
                System.out.println("任务完成");
            }
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
            //否则我们可以看看任务失败的原因是什么
            System.out.println(e.getCause().getMessage());
        }
    }
}

