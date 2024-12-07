package com.example.lats.repository;

import com.example.lats.model.entity.MarriageCertificate;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface MarriageCertificateRepository extends BaseJpaRepository<MarriageCertificate, String> {
    @Query(value = """
            SELECT FLOOR(MONTHS_BETWEEN(MC.SIGN_DATE, MC.WIFE_DATE_OF_BIRTH) / 12) AS age,
                   COUNT(*) AS total
            FROM MARRIAGE_CERTIFICATE MC
            WHERE (:hometown IS NULL OR MC.WIFE_HOMETOWN LIKE %:hometown%)
            GROUP BY FLOOR(MONTHS_BETWEEN(MC.SIGN_DATE, MC.WIFE_DATE_OF_BIRTH) / 12)
            ORDER BY age
            """, nativeQuery = true)
    List<Map<String, Object>> findWifeMarriageAges(@Param("hometown") String hometown);

    @Query(value = """
            SELECT FLOOR(MONTHS_BETWEEN(MC.SIGN_DATE, MC.HUSBAND_DATE_OF_BIRTH) / 12) AS age,
                   COUNT(*) AS total
            FROM MARRIAGE_CERTIFICATE MC
            WHERE (:hometown IS NULL OR MC.HUSBAND_HOMETOWN LIKE %:hometown%)
            GROUP BY FLOOR(MONTHS_BETWEEN(MC.SIGN_DATE, MC.HUSBAND_DATE_OF_BIRTH) / 12)
            ORDER BY age
            """, nativeQuery = true)
    List<Map<String, Object>> findHusbandMarriageAges(@Param("hometown") String hometown);

}
