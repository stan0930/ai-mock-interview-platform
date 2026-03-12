package com.a05.admin.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.StringJoiner;

/**
 * 全局异常处理器
 *
 * <p>统一捕获并转换异常，确保所有接口异常都以 {@link Result} 格式响应，
 * 避免默认 Spring 错误页面泄露堆栈信息。
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理参数校验失败（@Valid / @Validated 触发）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringJoiner joiner = new StringJoiner("; ");
        bindingResult.getFieldErrors().forEach(fe ->
                joiner.add(fe.getField() + ": " + fe.getDefaultMessage())
        );
        log.warn("[参数校验失败] {}", joiner);
        return Result.error(ResultCode.VALIDATION_FAILED.getCode(), joiner.toString());
    }

    /**
     * 处理业务异常（自定义 RuntimeException 子类可在此扩展）
     */
    @ExceptionHandler(RuntimeException.class)
    public Result<Void> handleRuntimeException(RuntimeException ex) {
        log.error("[业务异常] {}", ex.getMessage(), ex);
        return Result.error(ResultCode.ERROR.getCode(), ex.getMessage());
    }

    /**
     * 兜底：处理所有未被捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception ex) {
        log.error("[系统异常] {}", ex.getMessage(), ex);
        return Result.error(ResultCode.ERROR);
    }
}
