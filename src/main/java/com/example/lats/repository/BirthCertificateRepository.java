package com.example.lats.repository;

import com.example.lats.model.entity.BirthCertificate;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BirthCertificateRepository extends BaseJpaRepository<BirthCertificate, String> {
    // Đếm số trẻ em theo giới tính
    @Query(value = "SELECT COUNT(b) FROM BirthCertificate b JOIN Citizen c ON b.childId = c.citizenId WHERE c.gender = :gender")
    Long countGenderBirths(@Param(value = "gender") String gender);


    @Query(value = "SELECT c.HOMETOWN AS HOMETOWN, " +
            "SUM(CASE WHEN c.GENDER = 'Nam' THEN 1 ELSE 0 END) AS BOYS, " +
            "SUM(CASE WHEN c.GENDER = 'Nữ' THEN 1 ELSE 0 END) AS GIRLS, " +
            "CASE " +
            "WHEN SUM(CASE WHEN c.GENDER = 'Nữ' THEN 1 ELSE 0 END) = 0 THEN NULL " +
            "ELSE ROUND(SUM(CASE WHEN c.GENDER = 'Nam' THEN 1 ELSE 0 END) * 100.0 / " +
            "SUM(CASE WHEN c.GENDER = 'Nữ' THEN 1 ELSE 0 END), 2) " +
            "END AS RATIO " +
            "FROM CITIZEN c " +
            "JOIN BIRTH_CERTIFICATE b ON b.CHILD_ID = c.CITIZEN_ID " +
            "GROUP BY c.HOMETOWN " +
            "ORDER BY c.HOMETOWN", nativeQuery = true)
    List<Map<String, Object>> getHometownGenderData();
}
