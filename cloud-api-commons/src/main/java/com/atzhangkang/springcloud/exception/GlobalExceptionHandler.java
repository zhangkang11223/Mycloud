package com.atzhangkang.springcloud.exception;

import com.atzhangkang.springcloud.entities.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BizException.class)
    @ResponseBody
    public CommonResult bizExceptionHandler(BizException e){
        logger.error("发生业务异常！原因是1：{}",e.getErrorMsg());
        return CommonResult.error(e.getErrorCode(),e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     */
    @ExceptionHandler(value =NullPointerException.class)
    @ResponseBody
    public CommonResult exceptionHandler(NullPointerException e){
        logger.error("发生空指针异常！原因是2:",e);
        return CommonResult.error(CommonEnum.BODY_NOT_MATCH);
    }


    /**
     * 处理其他异常
     */
    @ExceptionHandler(value =Exception.class)
    @ResponseBody
    public CommonResult exceptionHandler(Exception e){
        logger.error("未知异常！原因是3:",e);
        return CommonResult.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }
}