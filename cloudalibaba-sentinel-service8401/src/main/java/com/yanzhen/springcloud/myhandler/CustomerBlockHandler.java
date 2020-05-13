package com.yanzhen.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.yanzhen.springcloud.entities.CommenResult;

public class CustomerBlockHandler {
    //定义处理器必须返回值一样 而且参数要有blockexception
    public static CommenResult handler1(BlockException exception){
        return  new CommenResult(444,"错误处理器1");

    }
    public static CommenResult handler2(BlockException e){
        return  new CommenResult(444,"错误处理器2");
    }
}
