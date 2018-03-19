package com.ougen.service;

import com.ougen.Entity.Seckill;
import com.ougen.Entity.SuccessKilled;
import com.ougen.dao.SeckillDao;
import com.ougen.dao.SuccessSeckillDao;
import com.ougen.dto.Exposer;
import com.ougen.dto.SeckillExecution;
import com.ougen.infoenum.InfoEnum;
import com.ougen.myexception.RepeatKillException;
import com.ougen.myexception.SeckillCloseException;
import com.ougen.myexception.SeckillException;
import com.ougen.service.impl.ServiceIpml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author:ougen
 * @date:2018/3/1322:47
 */

@Service
public class SeckillService implements ServiceIpml {
    private final Logger logger= LoggerFactory.getLogger(SeckillService.class);

    //加入一个混淆字符串(秒杀接口)的salt，为了我避免用户猜出我们的md5值，值任意给，越复杂越好
    private final String string="sfjkajflalkjlj23ljk3-=-/[;[p3-plkfa";

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessSeckillDao successSeckillDao;

    public Seckill queryById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public List<Seckill> queryAll(int offset, int limit) {
        return seckillDao.queryAll(offset,limit);
    }
    @Transactional
    public Exposer exportSeckillUrl(long seckillId) {
        Long now=System.currentTimeMillis();
        Seckill seckill=seckillDao.queryById(seckillId);
        Date start=seckill.getStartTime();
        Date end=seckill.getEndTime();
        if (now<start.getTime() || now>end.getTime()){
            return new Exposer(false,seckillId,seckill.getStartTime(),seckill.getEndTime(),new Date());
        }
        String md5=getMD5(seckillId);
        return new Exposer(true,md5,seckillId);
    }
    private String getMD5(long seckillId){
        String md5=seckillId+"/"+string;
        return   DigestUtils.md5DigestAsHex(md5.getBytes());
    }
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
       try{
           if (md5==null || !md5.equals(getMD5(seckillId))){
               throw  new SeckillCloseException("seckill is closed");
           }
           Date now =new Date();
           int statue=successSeckillDao.insertSuccessKill(seckillId,userPhone);
           if (statue<=0){
               throw new RepeatKillException("repeatKill");
           }else {
               int redu = seckillDao.reduceNum(seckillId, now);
               if (redu <= 0) {
                   throw new SeckillCloseException("time out");
               } else {
                   SuccessKilled successKilled = successSeckillDao.queryBySuccessKilledId(seckillId, userPhone);
                   return new SeckillExecution(InfoEnum.SUCCESS, successKilled);
               }
           }
       }catch (RepeatKillException re){
           throw re;
       }catch (SeckillCloseException sce){
           throw sce;
       }catch (Exception e){
           logger.error(e.getMessage(),e);
           throw new SeckillException("其他错误",e);
       }
    }
}
