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
@Table(name = "DEATH_CERTIFICATE")
public class DeathCertificate extends BaseClass<DeathCertificate>{
    @Id
    private Long deathCertificateId;
    private Long deceasedId; // Reference to Citizen entity
    private Date timeOfDeath;
    private String placeOfDeath;
    private String causeOfDeathCode;
    private String registrationPlace;
    private Long confirmedId; // Reference to Citizen entity
    private Date dateOfRegistration;
}
