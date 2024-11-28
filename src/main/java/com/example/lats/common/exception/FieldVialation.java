package com.example.lats.common.exception;

import com.dslplatform.json.CompiledJson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Value;

@Value
public class FieldVialation {
    String field;
    String description;
    @JsonIgnore
    Throwable throwable;

    //@CompiledJson
    public FieldVialation(String field, String description) {
        this(field, description, null);
    }

    public FieldVialation(String field, String description, Throwable throwable) {
        this.field = field;
        this.description = description;
        this.throwable = throwable;
    }
}
