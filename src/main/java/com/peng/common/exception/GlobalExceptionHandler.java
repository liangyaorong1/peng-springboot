package com.peng.common.exception;


import com.peng.common.lang.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * 全局异常处理
 * ShiroException：shiro抛出的异常，比如没有权限，用户登录异常
 * IllegalArgumentException：处理Assert的异常
 * MethodArgumentNotValidException：处理实体校验的异常
 * RuntimeException：捕捉其他异常
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    public R handler(BindException e) {
        log.info(e.getMessage());
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public R handler(Exception e) {
        log.info(e.getMessage());
        return R.fail(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public R handler(AccessDeniedException e) {
        log.info("security权限不足：---------------->>", e.getMessage());
        return R.fail("权限不足");
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handler(MethodArgumentNotValidException e) {
        log.info("实体校验异常：---------------->>", e.getMessage());
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return R.fail(objectError.getDefaultMessage());
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public R handler(IllegalArgumentException e) {
        log.error("Assert异常：---------------->>", e.getMessage());
        return R.fail(e.getMessage());

    }
    @ExceptionHandler(value = RuntimeException.class)
    public R handler(RuntimeException e) {
        log.error("运行时异常：--------------->>", e);
        return R.fail(e.getMessage());
    }
}
