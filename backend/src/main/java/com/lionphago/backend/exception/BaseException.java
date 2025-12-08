package com.lionphago.backend.exception;

/**
 * 异常基类
 */
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
