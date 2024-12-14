package com.example.lats.model.entity;

import com.dslplatform.json.CompiledJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@CompiledJson
public class Coordinates {
    Double x;
    Double y;
}
