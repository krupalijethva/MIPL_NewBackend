package com.monarch.repository;

import java.util.Date;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monarch.domain.PunchingDetail;
import com.monarch.domain.PunchingDetailEdit;

@Repository
public interface PunchingEditRepository extends JpaRepository<PunchingDetailEdit, Long>  {

	
	
	
	@Query("FROM PunchingDetailEdit AS pd WHERE pd.punchStatus = :punchStatus")
	public List<PunchingDetailEdit> getPendingRecords(PunchingDetail.Status punchStatus);
	
	
	@Query("FROM PunchingDetailEdit AS pd WHERE pd.username = :username AND pd.logindate = :logindate")
	public PunchingDetailEdit read(String username ,Date logindate);
	
	
	@Query("FROM PunchingDetailEdit AS pd WHERE pd.username = :username AND pd.logindate >= :logindate ORDER BY pd.logindate DESC")
	public List<PunchingDetailEdit> getLast7DaysDetails(String username,Date logindate);
	


	@Query("select pd from PunchingDetailEdit pd WHERE pd.logindate >= :logindate")
	public Page<PunchingDetailEdit> punchingRecord(@Param("logindate")Date logindate,Pageable pageable);
	
	
	
	@Query("select pd from PunchingDetailEdit pd WHERE pd.logindate >= :logindate AND LOWER(pd.punchStatus) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<PunchingDetailEdit> punchingRecord(@Param("logindate") Date logindate,Pageable pageable,@Param("status") String status);
	
	
	@Query("select pd from PunchingDetailEdit pd WHERE pd.logindate >= :logindate AND ("
			+"LOWER(pd.username) LIKE CONCAT('%',:searchterm,'%') OR"
			+ "(DATE(pd.logindate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:year,'%')) OR "
			+ "TIME_FORMAT(pd.loginTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.loginTimeedit,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.logoutTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.logoutTimeedit,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.breakTime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.breakTimeedit LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.totalHours LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.totalHoursedit LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.comments LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.employeeComments LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.punchStatus LIKE CONCAT('%',:searchterm,'%'))")
	public Page<PunchingDetailEdit> punchingSearch(@Param("logindate")Date logindate,Pageable pageable,@Param("searchterm") String searchterm,@Param("day") String day,@Param("month") String month,@Param("year") String year);

	
	@Query("select pd from PunchingDetailEdit pd WHERE pd.logindate >= :logindate AND LOWER(pd.punchStatus) LIKE LOWER(CONCAT('%',:status,'%')) AND("
			+"LOWER(pd.username) LIKE CONCAT('%',:searchterm,'%') OR"
			+ "(DATE(pd.logindate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:year,'%')) OR "
			+ "TIME_FORMAT(pd.loginTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.loginTimeedit,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.logoutTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.logoutTimeedit,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.breakTime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.breakTimeedit LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.totalHours LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.comments LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.employeeComments LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.totalHoursedit LIKE CONCAT('%',:searchterm,'%') OR "

			+ "pd.punchStatus LIKE CONCAT('%',:searchterm,'%'))")
	public Page<PunchingDetailEdit> punchingSearchAndStat(@Param("logindate")Date logindate,Pageable pageable,@Param("searchterm") String searchterm,@Param("day") String day,@Param("month") String month,@Param("year") String year,@Param("status") String st);

	
	
	
	
	
	
	
	
	
}
