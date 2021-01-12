package com.example.demo;

import java.util.concurrent.*;

/**
 * Create by Hercules
 * 2021-01-03 20:26
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1, 1, 10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6));

        execute(threadPool);

//        try {
//            Object submit = submit(threadPool);
//            if (submit == null) {//如果Future's get返回null，任务完成
//                System.out.println("任务完成");
//            }
//        } catch (InterruptedException e) {
//
//        } catch (ExecutionException e) {
//            //否则我们可以看看任务失败的原因是什么
//            System.out.println(e.getCause().getMessage());
//        }

        threadPool.shutdown();
    }

    private static void execute(ThreadPoolExecutor threadPool) {
        threadPool.execute(() -> {
            throw new RuntimeException("RuntimeException from inside execute");
        });
    }

    private static Object submit(ThreadPoolExecutor threadPool) throws ExecutionException, InterruptedException {
        Future<Object> future = threadPool.submit(() -> {
            throw new RuntimeException("RuntimeException from inside submit");
//            return null;
        });
        return future.get();
        
        
        String username = "hello world";
    }
}
