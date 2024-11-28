package com.example.lats.repository;

import com.example.lats.model.entity.Member;
import io.hypersistence.utils.spring.repository.BaseJpaRepository;

public interface MemberRepository extends BaseJpaRepository<Member, String> {
}
