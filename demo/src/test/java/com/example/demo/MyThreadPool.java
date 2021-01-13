package com.example.demo;
 
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
 
public class MyThreadPool {
 
    private ThreadPoolExecutor pool = null;
 
 
    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }
 
 
    public void init() {
        pool = new ThreadPoolExecutor(
                3,
                3,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(5),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());
    }
 
 
    public void destory() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }
 
 
    private class CustomThreadFactory implements ThreadFactory {
 
        private AtomicInteger count = new AtomicInteger(0);
 
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = MyThreadPool.class.getName() + count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }
 
 
    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
 
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 记录异常
            // 报警处理等
            System.out.println("error.............");
            throw new NullPointerException("enough");
        }
    }
 
//
//    public static void test1() {
//        throw new NullPointerException("test");
//    }
 
 
    public static void main(String[] args) {
        try {
            System.out.println("hello");
            MyThreadPool myThreadPool = new MyThreadPool();
            myThreadPool.init();
            ExecutorService pool = myThreadPool.getCustomThreadPoolExecutor();
            for (int i = 1; i < 10; i++) {
                System.out.println("提交第" + i + "个任务!");
                pool.execute(()-> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running=====");
                });
 
            }
            //myThreadPool.destory();
        } catch (Exception e) {
            System.out.println("ss" + e);
        }
    }
}
 
 