package com.monarch.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.monarch.domain.FolderAccessRequestDetail;
import com.monarch.domain.InternetAccessRequestDetail;


public interface FolderAccessRequestRepository extends JpaRepository<FolderAccessRequestDetail, Long>  {
	
	@Query("from FolderAccessRequestDetail p where p.username = :username")
	public Page<FolderAccessRequestDetail> findAll(@Param("username") String username,Pageable p);
	
	@Query("from FolderAccessRequestDetail")
	public Page<FolderAccessRequestDetail> findAll(Pageable p);
	
	@Query("from FolderAccessRequestDetail fard where LOWER(fard.status) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<FolderAccessRequestDetail> findAllByStatus(@Param("status") String status,Pageable p);

	@Query("select fard from FolderAccessRequestDetail fard WHERE ("
			+"LOWER(fard.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(fard.accessdate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(fard.accessremoveddate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(fard.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "	
			+"LOWER(fard.accesstype) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(fard.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(fard.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(fard.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<FolderAccessRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm);
	
	@Query("select fard from FolderAccessRequestDetail fard WHERE LOWER(fard.status) LIKE LOWER(CONCAT('%',:status,'%')) AND ("
			+"LOWER(fard.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(fard.accessdate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(fard.accessremoveddate) LIKE CONCAT('%',:searchterm,'%') OR "
			+"DATE(fard.requestdate) LIKE CONCAT('%',:searchterm,'%') OR "	
			+"LOWER(fard.accesstype) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(fard.description) LIKE CONCAT('%',:searchterm,'%') OR "
			+"LOWER(fard.comments) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(fard.status) LIKE CONCAT('%',:searchterm,'%'))")
	public Page<FolderAccessRequestDetail> findAllDetails(Pageable p,@Param("searchterm") String searchterm,@Param("status") String status);
	
	

}
