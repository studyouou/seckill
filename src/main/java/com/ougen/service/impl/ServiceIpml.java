package com.ougen.service.impl;

import com.ougen.Entity.Seckill;
import com.ougen.dto.Exposer;
import com.ougen.dto.SeckillExecution;
import com.ougen.myexception.RepeatKillException;
import com.ougen.myexception.SeckillCloseException;
import com.ougen.myexception.SeckillException;

import java.util.List;

/**
 * Service的接口
 * @author:ougen
 * @date:2018/3/1322:19
 */
public interface ServiceIpml {

    /**
    * 方法实现说明
    * @author      ougen
    * @param 商品的id
    * @return     秒杀的商品
    */
    Seckill queryById(long seckillId);

    List<Seckill> queryAll(int offset, int limit);

    //再往下，是我们最重要的行为的一些接口

    /**
     * 在秒杀开启时输出秒杀接口的地址，否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);


    /**
     * 执行秒杀操作，有可能失败，有可能成功，所以要抛出我们允许的异常
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException,RepeatKillException,SeckillCloseException;

}
