package com.example.lafsys.exception;

import lombok.Getter;

/**
 * 自�E 义异常
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;
    public ServiceException(String code, String msg){
        super(msg);
        this.code = code;
    }
}
