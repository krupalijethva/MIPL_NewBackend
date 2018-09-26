package com.monarch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.InternetAccessRequestDetail;
import com.monarch.domain.PendriveRequestDetail;

public interface InternetAccessRequestRepository extends JpaRepository<InternetAccessRequestDetail, Long> {
	
	@Query("from InternetAccessRequestDetail p where p.username = :username")
	public Page<InternetAccessRequestDetail> findAll(@Param("username") String username,Pageable p);

	@Query("from InternetAccessRequestDetail ")
	public Page<InternetAccessRequestDetail> findAll(Pageable p);
	
	
	@Query("from InternetAccessRequestDetail iard where LOWER(iard.status) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<InternetAccessRequestDetail> findAllByStatus(@Param("status") String status,Pageable p);

	@Query("select iard from InternetAccessRequestDetail iard WHERE ("
			+"LOWER(iard.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(iard.accessdate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(iard.returndate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(iard.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "	
			+"iard.duration LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(iard.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(iard.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(iard.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<InternetAccessRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm);
	
	@Query("select iard from InternetAccessRequestDetail iard WHERE LOWER(iard.status) LIKE LOWER(CONCAT('%',:status,'%')) AND ("
			+"LOWER(iard.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(iard.accessdate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(iard.returndate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(iard.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "	
			+"iard.duration LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(iard.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(iard.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(iard.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<InternetAccessRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm,@Param("status") String status);

}
