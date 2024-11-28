package com.example.lats.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "EDUCATION")
public class Education extends BaseClass<Education>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDUCATION_SEQ")
    @SequenceGenerator(sequenceName = "EDUCATION_SEQ", allocationSize = 1, name = "EDUCATION_SEQ")
    private Long Id;
    private String citizenId;
    private String educationalLevel;
    private String technicalQualification;
}
