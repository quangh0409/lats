package com.example.lats.common.exception;

import com.example.lats.common.Constant;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
public class BaseErrorCode {
    String code;
    String message;
    HttpStatus httpStatus;
    String originService;
    String path;

    public BaseErrorCode(int code, String message, HttpStatus httpStatus){
        this.code = String.valueOf(code);
        this.message = message;
        this.httpStatus = httpStatus;
        this.originService = System.getProperty(Constant.APPLICATION_NAME);
        this.path = null;
    }
}
