package com.monarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monarch.domain.candidate.MembershipDetail;

public interface MembershipRepository extends JpaRepository<MembershipDetail, Long>{

}
