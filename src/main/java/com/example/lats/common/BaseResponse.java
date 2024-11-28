package com.example.lats.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class BaseResponse<T> implements Serializable {

    private HttpStatus status;
    private T data;

    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<T>()
                .setStatus(HttpStatus.OK)
                .setData(data);
    }
    public static <T> BaseResponse<T> notFound() {
        return new BaseResponse<T>()
                .setStatus(HttpStatus.NOT_FOUND);
    }

    public static <T> BaseResponse<T> badRequest(T data) {
        return new BaseResponse<T>()
                .setStatus(HttpStatus.BAD_REQUEST)
                .setData(data);
    }
}
