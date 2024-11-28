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
@Table(name = "MARITAL_STATUS")
public class MaritalStatus extends BaseClass<MaritalStatus> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MARITAL_STATUS_ID_SEQ")
    @SequenceGenerator(sequenceName = "MARITAL_STATUS_ID_SEQ", allocationSize = 1, name = "MARITAL_STATUS_ID_SEQ")
    private Long Id;
    private String citizenId;
    private String maritalStatus;
    private Long marriageAge;
}
