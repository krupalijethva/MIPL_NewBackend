package com.monarch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.PendriveRequestDetail;

public interface PendriveRequestRepository extends JpaRepository<PendriveRequestDetail, Long>  {
	
	
	@Query("from PendriveRequestDetail p where p.username = :username")
	public Page<PendriveRequestDetail> findAll(@Param("username") String username,Pageable p);

	
	@Query("from PendriveRequestDetail")
	public Page<PendriveRequestDetail> findAll(Pageable p);
	
	@Query("from PendriveRequestDetail p where LOWER(p.status) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<PendriveRequestDetail> findAllByStatus(@Param("status") String status,Pageable p);

	@Query("select prd from PendriveRequestDetail prd WHERE ("
			+"LOWER(prd.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(prd.issuedate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(prd.returndate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(prd.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "	
			+"LOWER(prd.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(prd.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(prd.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<PendriveRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm);
	
	@Query("select prd from PendriveRequestDetail prd WHERE LOWER(prd.status) LIKE LOWER(CONCAT('%',:status,'%')) AND ("
			+"LOWER(prd.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(prd.issuedate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(prd.returndate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(prd.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "	
			+"LOWER(prd.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(prd.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(prd.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<PendriveRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm,@Param("status") String status);
}
