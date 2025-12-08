package com.lionphago.backend.exception;

/**
 * 用户已经存在时抛出异常
 */
public class UserAlreadyExsistException extends BaseException {
    public UserAlreadyExsistException(String message) {
        super(message);
    }
}
