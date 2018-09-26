package com.monarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monarch.domain.candidate.ExpirenceDetail;

public interface ExperienceRepository extends JpaRepository<ExpirenceDetail, Long> {

}
