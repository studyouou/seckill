package com.ougen.dao;


import com.ougen.Entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * @author:ougen
 * @date:2018/3/1315:04
 */
public interface SuccessSeckillDao {

    /**
    * 方法实现说明
    * @author      ougen
    * @param seckillId为秒杀商品的Id，userPhone为用户手机号
    * @return   插入几条数据
    */
    int insertSuccessKill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
    * 方法实现说明
    * @author      ougen
    * @param successKilledId查询成功秒杀的商品的Id,userPhone为用户手机号
    * @return      信息
    */
    SuccessKilled queryBySuccessKilledId(@Param("successKilledId") long successKilledId, @Param("userPhone") long userPhone);
}
