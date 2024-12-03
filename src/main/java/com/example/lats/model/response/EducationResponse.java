package com.example.lats.model.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EducationResponse {
    String level;
    double percentage;
}
