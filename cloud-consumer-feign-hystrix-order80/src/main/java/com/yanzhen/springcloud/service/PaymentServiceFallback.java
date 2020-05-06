package com.yanzhen.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentServiceFallback implements PaymentService {
    @Override
    public String payment_ok(Integer id) {
        return "payment_ok method error";
    }

    @Override
    public String payment_error(Integer id) {
        return "payment_error method error";
    }
}
