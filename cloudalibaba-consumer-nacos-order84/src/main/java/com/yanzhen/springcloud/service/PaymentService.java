package com.yanzhen.springcloud.service;

import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider",fallback = FallBackPayment.class)
public interface PaymentService {
    @GetMapping("/get/{id}")
    public CommenResult<Payment> get(@PathVariable("id") Long id);
}
