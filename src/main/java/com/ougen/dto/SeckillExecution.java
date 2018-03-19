package com.ougen.dto;

import com.ougen.Entity.SuccessKilled;
import com.ougen.infoenum.InfoEnum;

/**
 * @author:ougen
 * @date:2018/3/1322:40
 */
public class SeckillExecution {
    private long sekillId;
    //状态码
    private int code;
    //状态信息
    private String info;
    //成功信息
    private SuccessKilled successKilled;

    public SeckillExecution(InfoEnum infoEnum, SuccessKilled successKilled) {
        this.code=infoEnum.getCode();
        this.info=infoEnum.getInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(InfoEnum infoEnum, long sekillId) {
        this.code=infoEnum.getCode();
        this.info=infoEnum.getInfo();
        this.sekillId = sekillId;
    }

    public long getSekillId() {
        return sekillId;
    }

    public void setSekillId(long sekillId) {
        this.sekillId = sekillId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}
