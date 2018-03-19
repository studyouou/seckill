package com.ougen.myexception;

/**
 * 秒杀关闭异常
 * @author:ougen
 * @date:2018/3/1322:41
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(){ }

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
