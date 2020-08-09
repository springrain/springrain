package org.springrain.system.config;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springrain.frame.util.ReturnDatas;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Http Status Code 500
    public ReturnDatas handleException(Exception e) {
        ReturnDatas returnDatas = ReturnDatas.getErrorReturnDatas(e.getMessage());
        return returnDatas;
    }

}
