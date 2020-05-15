package com.yanzhen.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import com.yanzhen.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircleBreakerController {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.nacos-user-service}")
    String url;

    @GetMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback",fallback = "fallbackhandler",blockHandler = "blockhandler",exceptionsToIgnore = {IllegalArgumentException.class})
    public CommenResult get(@PathVariable("id") Long id){
        CommenResult<Payment> result =  restTemplate.getForObject(url+"/get/"+id,CommenResult.class);
        if(id == 4){
            throw new IllegalArgumentException("非法参数错误");
        }else if(result.getData() == null){
            throw new NullPointerException("该id没有对于记录 空指针异常");
        }
        return result;
    }
    public CommenResult fallbackhandler(@PathVariable("id") Long id){
        return new CommenResult(444,"fallbackhandler请稍后重试");
    }
    public CommenResult blockhandler(@PathVariable("id") Long id, BlockException exception){
        return new CommenResult(444,"blockhandler");
    }

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/consumer/get/{id}")
    public CommenResult demo1(@PathVariable("id") Long id){
        return paymentService.get(id);
    }
}
