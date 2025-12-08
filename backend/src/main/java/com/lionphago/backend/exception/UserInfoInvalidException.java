package com.lionphago.backend.exception;

/**
 * 用户信息不合法异常
 */
public class UserInfoInvalidException extends BaseException {
    public UserInfoInvalidException(String message) {
        super(message);
    }
}
