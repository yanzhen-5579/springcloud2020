package com.yanzhen.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import com.yanzhen.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/{id}",produces = "application/json")
    public CommenResult<Payment> getById(@PathVariable("id") Long id){
        return  paymentFeignService.get(id);
    }

    @GetMapping("/consumer/payment/timeout")
    public String timeout() throws InterruptedException{
        return paymentFeignService.timeout();
    }
}
