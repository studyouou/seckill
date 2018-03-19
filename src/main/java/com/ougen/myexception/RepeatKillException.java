package com.ougen.myexception;

/**
 * 重复秒杀的异常
 * @author:ougen
 * @date:2018/3/1322:40
 */
public class RepeatKillException extends SeckillException {
    public RepeatKillException() { }

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
