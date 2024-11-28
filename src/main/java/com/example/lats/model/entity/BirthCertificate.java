package com.example.lats.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long birthCertificateId; // Mã định danh giấy khai sinh
    private Long childId; // Mã định danh của trẻ
    private Long motherId; // Mã định danh của mẹ
    private Long fatherId; // Mã định danh của cha
    private Long informantId; // Mã định danh người khai sinh
    private String informantRelationship; // Quan hệ người khai sinh với trẻ
    private String registrationPlace; // Nơi đăng ký khai sinh
    private Date registrationDate; // Ngày đăng ký khai sinh
}
