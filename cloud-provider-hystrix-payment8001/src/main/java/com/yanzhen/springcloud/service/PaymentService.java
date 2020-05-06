package com.yanzhen.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {


    public String service_ok(Integer id){
        return "线程池"+Thread.currentThread().getName()+"\t"+id+"O(∩_∩)O哈哈~";
    }


    @HystrixCommand(fallbackMethod = "service_errorhandler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "3000")
    })
    public String service_error(Integer id){
//        int a = 10/0;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"\t"+id+"O(∩_∩)O哈哈~";
    }
    public String service_errorhandler(Integer id){
        return "线程池"+Thread.currentThread().getName()+"\t"+id+"o(╥﹏╥)o错误";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerError",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id <0){
            throw new RuntimeException("id 不能小于零");
        }
        String s = IdUtil.simpleUUID();
        return "调用成功"+s;
    }
    public String paymentCircuitBreakerError(@PathVariable("id") Integer id){
        return "id 为负数 请稍后再试";
    }
}
