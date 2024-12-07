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
@Table(name = "MARRIAGE_CERTIFICATE")
public class MarriageCertificate extends BaseClass<MarriageCertificate>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MARRIAGE_CERTIFICATE_ID_SEQ")
    @SequenceGenerator(sequenceName = "MARRIAGE_CERTIFICATE_ID_SEQ", allocationSize = 1, name = "MARRIAGE_CERTIFICATE_ID_SEQ")
    Long marriageCertificateId;
    Date signDate;
    String registrationPlace;
    String wifeId; // Reference to Citizen entity
    Date wifeDateOfBirth; // Reference to Citizen entity
    String wifeHometown; // Reference to Citizen entity
    String husbandId; // Reference to Citizen entity
    Date husbandDateOfBirth; // Reference to Citizen entity
    String husbandHometown; // Reference to Citizen entity
    String marriageStatus;
}
