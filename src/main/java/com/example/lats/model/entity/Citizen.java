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
@Table(name = "CITIZEN")
public class Citizen extends BaseClass<Citizen> {
    @Id
    String citizenId;
    String fullName;
    Date dateOfBirth;
    String gender;
    String placeOfBirth;
    String hometown;
    String ethnicity;
    String religion;
    String nationality;
    String personalTaxId;
    String permanentAddress;
    String currentAddress;
    String RelationToHead;
    String HouseholdId;
}
