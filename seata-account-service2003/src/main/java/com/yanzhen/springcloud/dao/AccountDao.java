package com.yanzhen.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountDao {
    //有两个以上的参数时需要加@param
    void decrease(@Param("userId") Long userId, @Param("money") BigDecimal money);
}
