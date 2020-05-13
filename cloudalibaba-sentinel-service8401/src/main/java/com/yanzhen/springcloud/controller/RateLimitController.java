package com.yanzhen.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yanzhen.springcloud.entities.CommenResult;
import com.yanzhen.springcloud.entities.Payment;
import com.yanzhen.springcloud.myhandler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @GetMapping("/byresource")
    @SentinelResource(value = "byresource",blockHandler = "deal_byresource")
    public CommenResult byResource(){
        return new CommenResult(200,"按资源名称限流ok",new Payment(100l,"yan"));
    }
    public CommenResult deal_byresource(BlockException e){
        return new CommenResult(444,"服务不可用\t"+e.getClass().getCanonicalName());
    }

    @GetMapping("/ratelimit/byurl")
    @SentinelResource("ratelimitbyurl")
    public CommenResult rateLimitUrl(){
        return new CommenResult(200,"按url限流ok");
    }

    @GetMapping("/ratelimit/customerblockhandler")
    @SentinelResource(value = "customerblockhandler",blockHandlerClass = CustomerBlockHandler.class,blockHandler = "handler2")
    public CommenResult customerblockhandler(){
        return new CommenResult(200,"按客户自定义的成功访问");
    }
}
