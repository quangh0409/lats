package com.example.lats.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "MARRIAGE_CERTIFICATE")
public class MarriageCertificate extends BaseClass<MarriageCertificate>{
    @Id
    private Long marriageCertificateId;
    private Date signDate;
    private String registrationPlace;
    private Long wifeId; // Reference to Citizen entity
    private Long husbandId; // Reference to Citizen entity
    private String marriageStatus;
}
