package com.monarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.monarch.domain.candidate.SoftwareDetail;

public interface SoftwareRepository extends JpaRepository<SoftwareDetail, Long>{

}
