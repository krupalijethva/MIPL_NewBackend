package com.monarch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monarch.domain.UserLeaveDetail;

public interface UserLeaveDetailRepository extends JpaRepository<UserLeaveDetail, Long>   {

}
