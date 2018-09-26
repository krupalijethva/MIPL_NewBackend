package com.monarch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.monarch.domain.candidate.Candidatedetail;


public interface CandidateDetailRepository extends JpaRepository<Candidatedetail, Long> 
{
	
	@Query("select cd from Candidatedetail cd")
	public Page<Candidatedetail> getCandidateRecords(Pageable pageable);
	@Query("select cd from Candidatedetail cd WHERE("
			+ "LOWER(cd.first_name) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(cd.last_name) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(cd.email) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(cd.gender) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "cd.contact_number LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(cd.appliedtype) LIKE LOWER(CONCAT('%',:searchterm,'%')))")
	public Page<Candidatedetail> getCandidateRecordsBySearch(Pageable pageable,@Param("searchterm") String searchterm);
}
