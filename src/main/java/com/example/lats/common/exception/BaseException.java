package com.example.lats.common.exception;

import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{
    private final transient BaseErrorCode baseErrorCode;

    /**
     * Build exception with BaseErrorCode
     *
     * @param baseErrorCode BaseErrorCode Object
     */
    public BaseException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode.getMessage());
        this.baseErrorCode = baseErrorCode;
    }

    /**
     * Build exception with BaseErrorCode and custom message
     *
     * @param baseErrorCode BaseErrorCode Object
     * @param message error message
     */
    public BaseException(BaseErrorCode baseErrorCode, String message){
        super(message);
        this.baseErrorCode = baseErrorCode;
    }
}
