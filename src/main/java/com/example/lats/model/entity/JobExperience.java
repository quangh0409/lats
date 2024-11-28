package com.example.lats.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "JOB_EXPERIENCE")
public class JobExperience extends BaseClass<JobExperience>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "JOB_EXPERIENCE_SEQ")
    @SequenceGenerator(sequenceName = "JOB_EXPERIENCE_SEQ", allocationSize = 1, name = "JOB_EXPERIENCE_SEQ")
    private String Id;
    private Long citizenId; // Reference to Citizen entity
    private Date startDate;
    private Date endDate;
    private String jobPosition;
    private String workLocation;
    private String jobDescription;
    private String employer;
}
