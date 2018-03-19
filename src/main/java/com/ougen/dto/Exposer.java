package com.ougen.dto;

import java.util.Date;

/**
 * @author:ougen
 * @date:2018/3/1322:39
 */
public class Exposer {
    private long seckillId;
    //是否开启秒杀
    private boolean exposed;
    //md5加密
    private String md5;
    //开始时间
    private Date startTime;
    //结束时间
    private Date endTime;
    //当前时间
    private Date now;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed,long seckillId, Date startTime, Date endTime, Date now) {
        this.exposed = exposed;
        this.md5 = md5;
        this.startTime = startTime;
        this.endTime = endTime;
        this.now = now;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getNow() {
        return now;
    }

    public void setNow(Date now) {
        this.now = now;
    }
}
