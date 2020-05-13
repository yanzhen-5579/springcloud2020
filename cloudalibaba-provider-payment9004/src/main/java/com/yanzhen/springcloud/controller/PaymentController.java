package com.yanzhen.springcloud.controller;

import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    public static HashMap<Long, Payment> paymentHashMap = new HashMap<>();
    static{
        paymentHashMap.put(1l,new Payment(1l,"user1"));
        paymentHashMap.put(2l,new Payment(2l,"user2"));
        paymentHashMap.put(3l,new Payment(3l,"user3"));
    }

    @Value("${server.port}")
    String port;

    @GetMapping("/get/{id}")
    public CommenResult<Payment> get(@PathVariable("id") Long id){
        Payment payment = paymentHashMap.get(id);
        return new CommenResult(200,"获得patmnet\t"+port,payment);
    }
}
