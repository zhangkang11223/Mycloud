package com.atzhangkang.springcloud.entities;

import com.atzhangkang.springcloud.exception.BaseErrorInfoInterface;
import com.atzhangkang.springcloud.exception.CommonEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T>{

    private Integer code;
    private String message;
    private T data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

    public CommonResult(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    /**
     * 返回成功，data为空
     */
    public static CommonResult<Object> success() {
        return success(null);
    }

    /**
     * 返回成功，data不为空
     */
    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> rb = new CommonResult<>();
        rb.setCode(CommonEnum.SUCCESS.getResultCode());
        rb.setMessage(CommonEnum.SUCCESS.getResultMsg());
        rb.setData(data);
        return rb;
    }

    /**
     * 返回失败入参：错误码和错误信息
     */
    public static <T>CommonResult<T> error(Integer code, String message) {
        CommonResult<T> rb = new CommonResult<T>();
        rb.setCode(code);
        rb.setMessage(message);
        rb.setData(null);
        return rb;
    }

    /**
     * 返回失败入参：枚举型错误码
     */
    public static <T> CommonResult<T> error(BaseErrorInfoInterface errorInfo) {
        CommonResult<T> rb = new CommonResult<>();
        rb.setCode(errorInfo.getResultCode());
        rb.setMessage(errorInfo.getResultMsg());
        rb.setData(null);
        return rb;
    }
}
