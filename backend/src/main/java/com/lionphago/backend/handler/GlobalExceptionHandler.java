package com.lionphago.backend.handler;

import com.lionphago.backend.exception.BaseException;
import com.lionphago.backend.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     * @param { @code ex }
     * @return { @code Result }
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获权限校验异常
     * @param { @code ex }
     * @return { @code Result }
     */
    @ExceptionHandler
    public Result exceptionHandler(AccessDeniedException ex){
        log.error("权限校验异常：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }
}
