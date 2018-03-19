package com.ougen.myexception;

/**
 * @author:ougen
 * @date:2018/3/1322:40
 */
public class SeckillException extends RuntimeException {
    public SeckillException(){}

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
