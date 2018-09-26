package com.monarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monarch.domain.candidate.TrainingDetail;

public interface TrainingRepository extends JpaRepository<TrainingDetail, Long>{

}
