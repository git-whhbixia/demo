//package com.example.demo;
//
//import com.example.demo.hystrix.CommandOrder;
//import com.example.demo.hystrix.CommandUser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;
//
///**
// * 用户服务和订单服务分成两个线程池组运行，互不干扰
// */
//public class App {
//
//    private final static Logger logger = LoggerFactory.getLogger(App.class);
//
//    public static void main(String [] args) throws Exception {
//
//        CommandOrder commandPhone = new CommandOrder("手机");
//        CommandOrder commandTV = new CommandOrder("电视");
//
//        //阻塞方式执行
//        String execute = commandPhone.execute();
//        logger.info("execute = [{}] ", execute);
//
//        //异步非阻塞方式
//        Future<String> queue = commandTV.queue();
//        String value = queue.get(200, TimeUnit.MILLISECONDS);
//        logger.info("value = [{}] ", value);
//
//        CommandUser commandUser = new CommandUser("许仙");
//        String name = commandUser.execute();
//        logger.info("name = [{}] ", name);
//
//    }
//}