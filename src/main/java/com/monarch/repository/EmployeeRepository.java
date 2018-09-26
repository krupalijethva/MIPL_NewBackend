package com.monarch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.candidate.EmployeeDetail;

public interface EmployeeRepository extends JpaRepository<EmployeeDetail, Long>  
{
	@Query("select ed from EmployeeDetail ed")
	public Page<EmployeeDetail> getCandidateRecords(Pageable pageable);
	
	
	@Query("select ed from EmployeeDetail ed WHERE("
			+ "ed.emp_id LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.first_name LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.last_name LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.desig LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.branch LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.email LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.gender LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.birth_date LIKE CONCAT('%',:searchterm,'%') OR "	
			+ "ed.contact_number LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.present_city LIKE CONCAT('%',:searchterm,'%') OR "
			+ "ed.permanent_city LIKE CONCAT('%',:searchterm,'%'))")
	public Page<EmployeeDetail> getCandidateRecordsBySearch(Pageable pageable,@Param("searchterm") String searchterm);


}
