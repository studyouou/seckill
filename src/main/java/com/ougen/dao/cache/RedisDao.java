package com.ougen.dao.cache;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.ougen.Entity.Seckill;
import org.springframework.web.bind.annotation.ExceptionHandler;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author:ougen
 * @date:2018/3/2912:45
 */

public class RedisDao {


    JedisPool jedisPool;
    private RuntimeSchema<Seckill> schema=RuntimeSchema.createFrom(Seckill.class);
    public RedisDao(String host,int port){
        jedisPool=new JedisPool(host,port);
    }
    public Seckill getSeckill(long seckillId){
       try{
           Jedis jedis = jedisPool.getResource();

           try {
               String key = "seckillId:" + seckillId;
               byte[] value = jedis.get(key.getBytes());
               if (value != null) {
                   Seckill seckill = schema.newMessage();
                   ProtobufIOUtil.mergeFrom(value, seckill, schema);
                   return seckill;
               }
           }finally {
               jedis.close();
           }
       }catch (Exception e){

       }
        return null;
    }

    public String putSeckill(Seckill seckill){
        long id=seckill.getSeckillId();
        Jedis jedis=jedisPool.getResource();

        try{
            try {
                String key = "seckillId:" + id;
                byte[] value = ProtobufIOUtil.toByteArray(seckill, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
                int timeout = 60 * 60;
                return jedis.setex(key.getBytes(), timeout, value);
            }finally {
                jedis.close();
            }
        }catch (Exception e){

        }
        return  null;
    }
}
