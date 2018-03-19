package com.ougen.infoenum;

/**
 * @author:ougen
 * @date:2018/3/149:39
 */
public enum InfoEnum {
    SUCCESS(1,"秒杀成功"),
    REPEAT(-2,"重复秒杀"),
    CLOSE(-3,"活动关闭"),
    FAIL(0,"秒杀失败"),
    PUBLIC_ERROR(-1,"其他错误");
    private String info;
    private int code;

    InfoEnum(int code, String info) {
        this.info = info;
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static InfoEnum getInfoEnum(int index){
        for (InfoEnum infoEnum:values()) {
            if (infoEnum.getCode()==index){
                return  infoEnum;
            }
        }
        return null;
    }
}
