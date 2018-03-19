package com.ougen.dao;

import com.ougen.Entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author:ougen
 * @date:2018/3/1315:04
 */
public interface SeckillDao {
    /**
    * 方法实现说明
    * @author      ougen
    * @param  seckillId为传入的商品id，killTime秒杀时间。
    * @return  库存减少的数量
    */
    public int reduceNum(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
    * 方法实现说明
    * @author      ougen
    * @param seckillId查询的商品的id
    * @return      商品
    */
    public Seckill queryById(long seckillId);

    /**
    * 方法实现说明
    * @author      ougen
    * @param offset查询的起点，limit查询条数
    * @return      返回商品的集合
    */
    public List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
