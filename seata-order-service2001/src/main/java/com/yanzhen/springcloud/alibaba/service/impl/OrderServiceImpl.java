package com.yanzhen.springcloud.alibaba.service.impl;

import com.yanzhen.springcloud.alibaba.dao.OrderDao;
import com.yanzhen.springcloud.alibaba.domain.Order;
import com.yanzhen.springcloud.alibaba.service.AccountService;
import com.yanzhen.springcloud.alibaba.service.OrderService;
import com.yanzhen.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountService accountService;

    @Autowired
    private StorageService storageService;
    @Override
    @GlobalTransactional(name = "fsp_tx_group",rollbackFor = Exception.class)
    public void create(Order order) {
        log.info("创建order"+order);
        orderDao.create(order);
        log.info("调用storage微服务，做库存减法");
        storageService.decrease(order.getProductId(),order.getCount());
        log.info("调用account微服务，做账户减法");
        accountService.decrease(order.getUserId(),order.getMoney());
        log.info("修改订单状态");
        orderDao.update(order.getUserId(),0);
    }
}
