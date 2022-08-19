package com.study.boke.exception;

import com.study.boke.common.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
public class ExceptionGlobalHandle {
    private static final Logger log =  LoggerFactory.getLogger(ExceptionGlobalHandle.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        log.error("Default Exception: ",e);
        return R.error("系统异常!");
    }

    @ExceptionHandler(ReggieException.class)
    @ResponseBody
    public R handleException(ReggieException e){
        log.error("ReggieException: ",e);
        return R.error(e.getCode(),e.getMsg());
    }
}
