package com.ougen.dao.cache;

import com.ougen.Entity.Seckill;
import com.ougen.dao.SeckillDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author:ougen
 * @date:2018/3/2913:54
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void getSeckill() {
        long id = 1001l;
        Seckill seckill=redisDao.getSeckill(id);
        if (seckill==null){
            Seckill seckill1=seckillDao.queryById(id);
            String string= redisDao.putSeckill(seckill1);
            System.out.println(string);
        }
        System.out.println(seckill);
    }

    @Test
    public void putSeckill() {
    }

}