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
@Table(name = "OCCUPATION")
public class Occupation extends BaseClass<Occupation>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OCCUPATION_SEQ")
    @SequenceGenerator(sequenceName = "OCCUPATION_SEQ", allocationSize = 1, name = "OCCUPATION_SEQ")
    private Long Id;
    private String citizenId;
    private String occupation;
    private String workplace;
}
