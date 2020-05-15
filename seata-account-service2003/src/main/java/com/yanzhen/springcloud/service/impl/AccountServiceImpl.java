package com.yanzhen.springcloud.service.impl;

import com.yanzhen.springcloud.dao.AccountDao;
import com.yanzhen.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountDao accountDao;

    @Override
    public void decrease(Long userId, BigDecimal money) {
        log.info("减账户开始了");
        accountDao.decrease(userId,money);
    }
}
