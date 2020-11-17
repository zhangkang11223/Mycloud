package com.atzhangkang.springcloud.entities.exception;

public interface BaseErrorInfoInterface {
    /** 错误码*/
    Integer getResultCode();

    /** 错误描述*/
    String getResultMsg();
}
