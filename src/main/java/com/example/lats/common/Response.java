package com.example.lats.common;

import com.dslplatform.json.CompiledJson;
import com.dslplatform.json.JsonAttribute;
import com.example.lats.common.exception.FieldVialation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class Response<T> {
    private T data;
    private Metadata meta;

    //@CompiledJson
    Response(T data, Metadata meta){
        this.data = data;
        this.meta = meta;
    }


    public static <T> Response<T> success(){
        return  success((T) null);
    }

    public static < T > Response < T > success(T data) {
        Response<T> response = new Response<>();
        response.data = data;
        response.meta.code = Metadata.OK_CODE;
//        response.meta.path = ThreadContext.get(CommonConstant.PATH);
//        response.meta.originService = System.getProperty(CommonConstant.APPLICATION_NAME);
        return response;
    }


    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        public static final String OK_CODE = "200";
        @JsonAttribute(name = "code")
        String code;
        Integer page;
        Integer size;
        Integer pages;
        Long total;
        String message;
        List<FieldVialation> errors;
        String originService;
        String path;

//        @CompiledJson
        public Metadata(String code, Integer page, Integer size, Integer pages, Long total, String message, List<FieldVialation> errors, String originService, String path) {
            this.code = code;
            this.page = page;
            this.size = size;
            this.pages = pages;
            this.total = total;
            this.message = message;
            this.errors = errors;
            this.originService = originService;
            this.path = path;
        }
    }
};

