package com.yanzhen.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentServiceFallback.class)
public interface PaymentService {
    @GetMapping("/payment/ok/{id}")
    public String payment_ok(@PathVariable("id") Integer id);

    @GetMapping("/payment/error/{id}")
    public String payment_error(@PathVariable("id")Integer id);
}
