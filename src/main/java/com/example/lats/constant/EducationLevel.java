package com.example.lats.constant;

import lombok.Getter;

@Getter
public enum EducationLevel {
    PRIMARY("Cấp 1"),
    SECONDARY("Cấp 2"),
    HIGH_SCHOOL("Cấp 3"),
    VOCATIONAL_COLLEGE("Cao đẳng nghề"),
    UNIVERSITY("Đại học"),
    POST_GRADUATE("Trên đại học");

    // Getter
    private final String description;

    // Constructor
    EducationLevel(String description) {
        this.description = description;
    }

}
