package com.monarch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.PendriveRequestDetail;
import com.monarch.domain.StationeryItemRequestDetail;

public interface StationeryItemRequestRepository extends JpaRepository<StationeryItemRequestDetail, Long> {
	
	
	@Query("from StationeryItemRequestDetail p where p.username = :username")
	public Page<StationeryItemRequestDetail> findAll(@Param("username") String username,Pageable p);
	
	@Query("from StationeryItemRequestDetail")
	public Page<StationeryItemRequestDetail> findAll(Pageable p);
	
	@Query("from StationeryItemRequestDetail sird where LOWER(sird.status) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<StationeryItemRequestDetail> findAllByStatus(@Param("status") String status,Pageable p);

	@Query("select sird from StationeryItemRequestDetail sird WHERE ("
			+"LOWER(sird.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(sird.issuedate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(sird.returndate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(sird.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "	
			+"LOWER(sird.selectitem) LIKE CONCAT('%',:searchterm,'%') OR "
			+"sird.noofquantity LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(sird.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(sird.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(sird.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<StationeryItemRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm);
	
	@Query("select sird from StationeryItemRequestDetail sird WHERE LOWER(sird.status) LIKE LOWER(CONCAT('%',:status,'%')) AND ("
			+"LOWER(sird.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(sird.issuedate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(sird.returndate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(sird.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "		
			+"LOWER(sird.selectitem) LIKE CONCAT('%',:searchterm,'%') OR "
			+"sird.noofquantity LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(sird.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(sird.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(sird.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<StationeryItemRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm,@Param("status") String status);


}
