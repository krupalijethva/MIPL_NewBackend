package com.monarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monarch.domain.candidate.ActivityDetail;

public interface ActivityRepository extends JpaRepository<ActivityDetail, Long>   {

}
