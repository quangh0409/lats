package com.example.lats.common;

import com.example.lats.common.exception.BaseErrorCode;
import org.springframework.http.HttpStatus;

public interface ErrorCode {
    BaseErrorCode SHEET_NOT_SUPPORT = new BaseErrorCode(4000, "Sheet not support", HttpStatus.BAD_REQUEST);
}
