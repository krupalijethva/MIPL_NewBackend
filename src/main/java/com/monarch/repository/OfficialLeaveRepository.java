package com.monarch.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.OfficialLeaveDetail;

public interface OfficialLeaveRepository extends JpaRepository<OfficialLeaveDetail, Long>{

	@Query("from OfficialLeaveDetail offdet where MONTH(offdet.leaveDate) =:month")
	 public List<OfficialLeaveDetail> getMonthHoliday(@Param("month") int month);
	
	@Query("select leaveDate from OfficialLeaveDetail offdet where MONTH(offdet.leaveDate) =:month")
	public List<OfficialLeaveDetail> getMonthHolidayDate(@Param("month") int month);
	
	
	 @Query("select officialLeave FROM OfficialLeaveDetail AS officialLeave ")
	 public Page<OfficialLeaveDetail> getOfficialList(Pageable p);
	
	
	
	
}
