package com.example.lats.repository;

import com.example.lats.model.entity.Education;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EducationRepository extends BaseJpaRepository<Education, String>, JpaSpecificationExecutor<Education> {

}
