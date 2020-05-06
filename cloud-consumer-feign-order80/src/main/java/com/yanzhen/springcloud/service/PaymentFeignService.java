package com.yanzhen.springcloud.service;

import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient("cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    public CommenResult<Payment> get(@PathVariable("id")Long id);

    @GetMapping("/payment/timeout")
    public String timeout() throws InterruptedException;
}
