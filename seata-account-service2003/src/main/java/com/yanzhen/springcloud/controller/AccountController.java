package com.yanzhen.springcloud.controller;

import com.yanzhen.springcloud.domain.CommonResult;
import com.yanzhen.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money){
        accountService.decrease(userId,money);
        return new CommonResult(200,"减账户成功了呢");
    }
}
