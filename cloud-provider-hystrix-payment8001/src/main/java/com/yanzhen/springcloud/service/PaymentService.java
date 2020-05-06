package com.yanzhen.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String service_ok(Integer id){
        return "线程池"+Thread.currentThread().getName()+"\t"+id+"O(∩_∩)O哈哈~";
    }
    public String service_error(Integer id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池"+Thread.currentThread().getName()+"\t"+id+"O(∩_∩)O哈哈~ 超时3000";
    }
}
