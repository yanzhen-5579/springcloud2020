package com.yanzhen.springcloud.service;

import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import org.springframework.stereotype.Component;

@Component
public class FallBackPayment implements PaymentService{
    @Override
    public CommenResult<Payment> get(Long id) {
        return new CommenResult<>(44444,"fallback兜底方法");
    }
}
