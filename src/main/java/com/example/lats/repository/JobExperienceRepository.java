package com.example.lats.repository;

import com.example.lats.model.entity.JobExperience;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface JobExperienceRepository extends BaseJpaRepository<JobExperience, String> {
    @Query(value = """
            SELECT
                GENDER,
                COUNT(*) AS COUNT_OVER_18
            FROM
                JOB_EXPERIENCE JE
            WHERE
                FLOOR(MONTHS_BETWEEN(SYSDATE, JE.DATE_OF_BIRTH) / 12) > 18
                AND (:hometown IS NULL OR :hometown = '' OR JE.HOMETOWN LIKE %:hometown%)
            GROUP BY
                GENDER
            """, nativeQuery = true)
    List<Object[]> findGenderCountOver18ByHometown(@Param("hometown") String hometown);
}
