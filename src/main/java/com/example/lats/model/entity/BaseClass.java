package com.example.lats.model.entity;

import jakarta.persistence.Inheritance;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@Data
@MappedSuperclass
//@Inheritance
public class BaseClass<T extends BaseClass<?>> {
    private OffsetDateTime createAt;
    private OffsetDateTime updateAt;

    @SuppressWarnings("unchecked")
    public T setUpdateAt(OffsetDateTime updateAt) {
        this.updateAt = updateAt;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setCreateAt(OffsetDateTime createAt) {
        this.createAt = createAt;
        return (T) this;
    }
}
