package com.example.lats.repository;

import com.example.lats.model.entity.District;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DistrictRepository extends BaseJpaRepository<District, String> {
    @Query("SELECT d.districtName FROM District d WHERE d.districtId = :districtId")
    String findDistrictNameById(@Param("districtId") Long districtId);
}
