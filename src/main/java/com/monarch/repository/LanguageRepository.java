package com.monarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monarch.domain.candidate.LanguageDetail;

public interface LanguageRepository extends JpaRepository<LanguageDetail, Long> {

}
