package com.example.lats.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "COMMUNE")
public class Commune extends BaseClass<Commune>{
    @Id
    private Long communeId;
    private String communeName;
    private Long districtId; // Reference to District entity
    private Integer population;
    private Double area;
}
