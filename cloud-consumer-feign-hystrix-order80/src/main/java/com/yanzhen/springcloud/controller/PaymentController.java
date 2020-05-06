package com.yanzhen.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.yanzhen.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "global")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id){
        return paymentService.payment_ok(id);
    }
    @GetMapping("/consumer/payment/error/{id}")
//    @HystrixCommand(fallbackMethod = "payment_errorhandler",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    public String payment_error(@PathVariable("id")Integer id){
        return paymentService.payment_error(id);
    }
    //fallback方法要与原方法头一模一样 参数也不能改
    public String payment_errorhandler(@PathVariable("id")Integer id){
        return "系统繁忙，请稍后再试";
    }
    public String global(){
        return "global error";
    }
}
