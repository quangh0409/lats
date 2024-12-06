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
@Table(name = "DEATH_CERTIFICATE")
public class DeathCertificate extends BaseClass<DeathCertificate> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DEATH_CERTIFICATE_ID_SEQ")
    @SequenceGenerator(sequenceName = "DEATH_CERTIFICATE_ID_SEQ", allocationSize = 1, name = "DEATH_CERTIFICATE_ID_SEQ")
    Long deathCertificateId;
    String gender;
    String deceasedId; // Reference to Citizen entity
    Date timeOfDeath;
    Date dateOfBirth;
    String placeOfDeath;
    String causeOfDeathCode;
    String registrationPlace;
    Long confirmedId; // Reference to Citizen entity
    Date dateOfRegistration;
}
