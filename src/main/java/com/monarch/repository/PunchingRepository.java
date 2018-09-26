package com.monarch.repository;




import java.sql.Time;
import java.util.Date;


import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monarch.domain.PunchingDetail;


@Repository
public interface PunchingRepository extends PagingAndSortingRepository<PunchingDetail, Long> 
{
 
	
	public List<PunchingDetail> findByusername(String username);
	
	@Query("select pd from PunchingDetail pd WHERE pd.username = :username AND("
			+ "(DATE(pd.logindate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:year,'%')) OR "
			+ "TIME_FORMAT(pd.loginTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.logoutTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.breakTime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.totalHours LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.punchStatus LIKE CONCAT('%',:searchterm,'%'))")
	public Page<PunchingDetail> findBySearchTerm(@Param("username") String username, Pageable pageable,@Param("searchterm") String searchterm,@Param("day") String day,@Param("month") String month,@Param("year") String year);
	
	
	@Query("select pd from PunchingDetail pd WHERE LOWER(pd.username) LIKE LOWER(CONCAT('%',:username,'%')) AND pd.logindate >= :logindate")
	public Page<PunchingDetail> findBySearchTerm(@Param("username") String username, Pageable pageable,@Param("logindate") Date logindate);

	
	@Query("select pd from PunchingDetail pd WHERE LOWER(pd.username) LIKE LOWER(CONCAT('%',:username,'%')) AND LOWER(pd.punchStatus) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<PunchingDetail> findBySearchTerm(@Param("username") String username, Pageable pageable,@Param("status") String st);
	
	@Query("select pd from PunchingDetail pd WHERE pd.username = :username AND pd.logindate = :workingDate")
	public PunchingDetail read(@Param("username") String username,@Param("workingDate") Date workingDate);
	
	@Query("FROM PunchingDetail AS pd WHERE pd.username = :username AND pd.logindate = :logindate")
	public PunchingDetail isPunching(@Param("username")String username,@Param("logindate")Date logindate );
	
	@Query("FROM PunchingDetail AS pd WHERE pd.username = :username AND pd.logindate >= :logindate ORDER BY pd.logindate DESC")
	public List<PunchingDetail> getLast30DaysDetails(@Param("username")String username,@Param("logindate")Date logindate );
	
	@Query("select max(idPunching) from PunchingDetail")
	public long getLastID();
	
	
	//Admin Punching Records
	
	@Query("select pd from PunchingDetail pd WHERE pd.logindate >= :logindate")
	public Page<PunchingDetail> punchingRecord(@Param("logindate")Date logindate,Pageable pageable);
	
	
	@Query("select pd from PunchingDetail pd WHERE pd.logindate >= :logindate AND LOWER(pd.punchStatus) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<PunchingDetail> punchingRecordWithStatus(@Param("logindate")Date logindate,Pageable pageable,@Param("status") String st);
	
	
	@Query("select pd from PunchingDetail pd WHERE pd.logindate >= :logindate AND ("
			+"LOWER(pd.username) LIKE CONCAT('%',:searchterm,'%') OR "
			+ "(DATE(pd.logindate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:year,'%')) OR "
			+ "TIME_FORMAT(pd.loginTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.logoutTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.breakTime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.totalHours LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.ipAddress LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.hostName LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.punchStatus LIKE CONCAT('%',:searchterm,'%'))")
	public Page<PunchingDetail> punchingSearch(@Param("logindate")Date logindate,Pageable pageable,@Param("searchterm") String searchterm,@Param("day") String day,@Param("month") String month,@Param("year") String year);

	
	@Query("select pd from PunchingDetail pd WHERE pd.logindate >= :logindate AND LOWER(pd.punchStatus) LIKE LOWER(CONCAT('%',:status,'%')) AND("
			+"LOWER(pd.username) LIKE CONCAT('%',:searchterm,'%') OR"
			+ "(DATE(pd.logindate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.logindate) LIKE CONCAT('%',:year,'%')) OR "
			+ "TIME_FORMAT(pd.loginTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "TIME_FORMAT(pd.logoutTime,'%H %i') LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.breakTime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.totalHours LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.ipAddress LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.hostName LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.punchStatus LIKE CONCAT('%',:searchterm,'%'))")
	public Page<PunchingDetail> punchingSearchAndStat(@Param("logindate")Date logindate,Pageable pageable,@Param("searchterm") String searchterm,@Param("day") String day,@Param("month") String month,@Param("year") String year,@Param("status") String st);

	
	
	//admin generate reports
	@Query("FROM PunchingDetail AS pd WHERE pd.logindate BETWEEN :fromdate AND :todate")
	public List<PunchingDetail> getPunchingRecords(@Param("fromdate") Date fromdate,@Param("todate") Date todate );
	
	
	
	@Query("SELECT pd.totalHours FROM PunchingDetail AS pd WHERE pd.logindate = :logindate AND pd.username=:username")
	public Time getRecordByHours(@Param("username") String username,@Param("logindate") Date logindate);
		
	
	@Query("select logindate from PunchingDetail pd where MONTH(pd.logindate) = :month  AND pd.username = :username2")
	public List<PunchingDetail> getLoginDateList(@Param("username2") String username,@Param("month") int month);
	
	
	
	

}
