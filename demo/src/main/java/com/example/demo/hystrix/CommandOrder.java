package com.example.demo.hystrix;
 
import com.netflix.hystrix.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import java.util.concurrent.TimeUnit;
 
public class CommandOrder extends HystrixCommand<String> {
 
    private final static Logger logger = LoggerFactory.getLogger(CommandOrder.class);
 
    private String orderName;

    public CommandOrder(String orderName) {
        super(Setter.withGroupKey(
                //服务分组
                HystrixCommandGroupKey.Factory.asKey("OrderGroup"))
                //线程分组
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("OrderPool"))
                //线程池配置
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                        .withCoreSize(10)
                        .withKeepAliveTimeMinutes(5)
                        .withMaxQueueSize(10)
                        .withQueueSizeRejectionThreshold(10000))
                //线程池隔离
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        );
        this.orderName = orderName;
    }
 
    @Override
    protected String run() throws Exception {
 
        logger.info("orderName = [{}] ", orderName);
 
        TimeUnit.MILLISECONDS.sleep(100);
 
        return "OrderName=" + orderName;
    }
}