package com.mr.config;

import com.mr.exception.RRException;
import com.mr.utils.R;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.naming.AuthenticationException;
import javax.validation.ConstraintViolationException;

/**
 * @author LiangYongjie
 * @date 2019-01-15
 */
@RestControllerAdvice
public class ExceptionHandlerConfig {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerConfig.class);

    @ExceptionHandler(RRException.class)
    public R handlerRRException(RRException e) {
        log.debug(e.getMessage(), e);
        return R.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(AuthorizationException.class)
    public R handlerAuthorizationException() {
        return R.error(403, "无权限");
    }

    @ExceptionHandler(AuthenticationException.class)
    public R handlerAuthenticationException() {
        return R.error(401, "未登录");
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R handlerMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.debug(e.getMessage(), e);
        return R.error("参数异常");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R handlerHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.debug(e.getMessage(), e);
        return R.error("参数异常");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R handlerHttpRequestMethodNotSupportedException() {
        return R.error("请求方式错误");
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R handlerHttpMediaTypeNotSupportedException() {
        return R.error("请求方式错误");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public R handlerConstraintViolationException(ConstraintViolationException e) {
        return R.error(e.getConstraintViolations().iterator().next().getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R handlerException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error("未知异常");
    }

}
