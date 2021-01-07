package com.example.demo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Create by Hercules
 * 2021-01-03 20:26
 */
public class Test3 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                1,
                10,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(6),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        thread.setName("test-name-" + atomicInteger.incrementAndGet());
                        return thread;
                    }
                }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("拒绝策略，丢弃");
            }
        });
        threadPool.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 10; i++) {
            threadPool.execute(() -> {
//            throw new RuntimeException("RuntimeException from inside execute");
                System.out.println("线程名称" + Thread.currentThread().getName() + ",优先级" + Thread.currentThread().getPriority() + ",守护线程" + Thread.currentThread().isDaemon());
            });
        }

//        Future<Object> future = threadPool.submit(() -> {
////            throw new RuntimeException("RuntimeException from inside submit");
//            return null;
//        });
//        try {
//            if (future.get() == null) {//如果Future's get返回null，任务完成
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
}
