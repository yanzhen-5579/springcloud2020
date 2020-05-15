package com.yanzhen.springcloud.service.impl;

import com.yanzhen.springcloud.dao.StorageDao;
import com.yanzhen.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl  implements StorageService {

    @Autowired
    private StorageDao storageDao;
    @Override
    public void decrease(Long productId, Integer count) {
        log.info("減庫存");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        storageDao.decrease(productId,count);
    }
}
