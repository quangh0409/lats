package com.example.lats.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "BIRTH_CERTIFICATE")
public class BirthCertificate extends BaseClass<BirthCertificate> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BIRTH_CERTIFICATE_ID_SEQ")
    @SequenceGenerator(sequenceName = "BIRTH_CERTIFICATE_ID_SEQ", allocationSize = 1, name = "BIRTH_CERTIFICATE_ID_SEQ")
    Long birthCertificateId; // Mã định danh giấy khai sinh
    Date dateOfBirth;
    String gender;
    String hometown;
    String childId; // Mã định danh của trẻ
    String motherId; // Mã định danh của mẹ
    String fatherId; // Mã định danh của cha
    String informantId; // Mã định danh người khai sinh
    String informantRelationship; // Quan hệ người khai sinh với trẻ
    String registrationPlace; // Nơi đăng ký khai sinh
    Date registrationDate; // Ngày đăng ký khai sinh
}
