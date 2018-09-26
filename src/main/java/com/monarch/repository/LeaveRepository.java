package com.monarch.repository;

import java.util.Date;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monarch.domain.LeaveRecords;

@Repository
public interface LeaveRepository extends PagingAndSortingRepository<LeaveRecords, Long> {

	@Query("from LeaveRecords r where r.userleavedetail.leaveid = :leaveid")
	public Page<LeaveRecords> findAllById(@Param("leaveid") long leaveid,Pageable p);
	
	@Query("select lr from LeaveRecords lr WHERE lr.userleavedetail.leaveid = :leaveid AND("
			+ "lr.fromdate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "lr.todate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "lr.appliedtime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(lr.leavetype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.leaveCategory) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.durationtype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.leavestatus) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.commentsEmployee) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.commentsProjectControler) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.commentsTeamLead) LIKE LOWER(CONCAT('%',:searchterm,'%')))")
	public Page<LeaveRecords> findAllById(@Param("leaveid") long leaveid,@Param("searchterm") String searchterm,Pageable p);
	
	@Query("from LeaveRecords lr where lr.userleavedetail.leaveid = :leaveid AND LOWER(lr.leavestatus) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<LeaveRecords> findAllById(@Param("leaveid") long leaveid,Pageable p,@Param("status") String status);
	
	@Query("select lr from LeaveRecords lr WHERE lr.userleavedetail.leaveid = :leaveid AND LOWER(lr.leavestatus) LIKE LOWER(CONCAT('%',:status,'%')) AND("
			+ "lr.fromdate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "lr.todate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "lr.appliedtime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(lr.leavetype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.leaveCategory) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.durationtype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.leavestatus) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.commentsEmployee) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.commentsProjectControler) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(lr.commentsTeamLead) LIKE LOWER(CONCAT('%',:searchterm,'%')))")
	public Page<LeaveRecords> findAllById(@Param("leaveid") long leaveid,Pageable p,@Param("status") String status,@Param("searchterm") String searchterm);

	
	@Query("FROM LeaveRecords where leaveID = :leaveid")
	public List<LeaveRecords> readAllLeave(@Param("leaveid") long leaveid );
	
	@Query("FROM LeaveRecords AS leave WHERE leave.teamlead = :teamlead ORDER BY leave.fromdate")
	public List<LeaveRecords> readTeamMemberLeave(String teamlead);
	
	@Query("FROM LeaveRecords AS leave ORDER BY leave.fromdate")
	public List<LeaveRecords> readAllLeave();
	
	@Query("FROM LeaveRecords AS Leave WHERE Leave.fromdate BETWEEN :startDate AND :endDate")
    public List<LeaveRecords> monthlyLeaveRecords(Date startDate, Date endDate);

	
	@Query("Select COUNT(*) from LeaveRecords where leaveID= :Leaveid")
	public long getDuration(@Param("Leaveid") long Leaveid);
	
	@Query("Select sum(duration) from LeaveRecords where leaveID= :leaveid  AND leavestatus='APPROVED' AND  appliedTime >= '2017-04-01'")
	public Object LeaveCredit(@Param("leaveid") long leaveid);
	
	
	@Query( "select lr.fromdate,lr.todate from LeaveRecords as lr where leaveID= :leaveid AND leavestatus='Approved' AND (MONTH(lr.fromdate) =:month OR MONTH(lr.todate) =:month)")
 	public List getFromToDate(@Param("month") int month,@Param("leaveid") long leaveid);
	
	
	//Get Leave Approved Records 
	
	@Query("FROM LeaveRecords pd")
	public Page<LeaveRecords> getLeaveApprovalRecord(Pageable pageable);
	
	@Query("select pd from LeaveRecords pd WHERE LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<LeaveRecords> getLeaveApprovalRecord(Pageable pageable,@Param("status") String st);
	
	
	@Query("select pd from LeaveRecords pd WHERE ("
			+ "pd.fromdate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.todate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.appliedtime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(pd.leavetype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leaveCategory) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.teamlead) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsEmployee) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsProjectControler) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsTeamLead) LIKE LOWER(CONCAT('%',:searchterm,'%')))")
	public Page<LeaveRecords> getLApprovalRecord(Pageable pageable,@Param("searchterm") String searchterm);

	
	@Query("select pd from LeaveRecords pd WHERE LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:status,'%')) AND("
			+ "pd.fromdate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.todate LIKE CONCAT('%',:searchterm,'%') OR "
			+ "pd.appliedtime LIKE CONCAT('%',:searchterm,'%') OR "
			+ "LOWER(pd.leavetype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leaveCategory) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.teamlead) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsEmployee) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsProjectControler) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsTeamLead) LIKE LOWER(CONCAT('%',:searchterm,'%')))")
	public Page<LeaveRecords> getLeaveApprovalRecord(Pageable pageable,@Param("searchterm") String searchterm,@Param("status") String st);

	
	//Get Leave Approved Records For Team Leader 
	
	@Query("select pd from LeaveRecords pd WHERE pd.teamlead = :leader")
	public Page<LeaveRecords> getLeaveRecordLeader(@Param("leader") String leader,Pageable pageable);
	
	@Query("select pd from LeaveRecords pd WHERE pd.teamlead = :leader AND LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:status,'%'))")
	public Page<LeaveRecords> getLeaveRecordLeader(@Param("leader") String leader,Pageable pageable,@Param("status") String st);
	
	
@Query("select pd from LeaveRecords pd WHERE pd.teamlead = :leader AND("
			
			+ "(DATE(pd.fromdate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.fromdate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.fromdate) LIKE CONCAT('%',:year,'%')) OR "
			
			+ "(DATE(pd.todate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.todate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.todate) LIKE CONCAT('%',:year,'%')) OR "
			
			+ "(DATE(pd.appliedtime) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.appliedtime) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.appliedtime) LIKE CONCAT('%',:year,'%')) OR "
			
			+ "LOWER(pd.leavetype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leaveCategory) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.teamlead) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsProjectControler) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsTeamLead) LIKE LOWER(CONCAT('%',:searchterm,'%')))")
	public Page<LeaveRecords> getLeaveRecordLeader(Pageable pageable,@Param("searchterm") String searchterm,@Param("day") String day,@Param("month") String month,@Param("year") String year,@Param("leader") String leader);

	
	@Query("select pd from LeaveRecords pd WHERE pd.teamlead = :leader AND LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:status,'%')) AND("
			
			+ "(DATE(pd.fromdate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.fromdate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.fromdate) LIKE CONCAT('%',:year,'%')) OR "
			
			+ "(DATE(pd.todate) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.todate) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.todate) LIKE CONCAT('%',:year,'%')) OR "
			
			+ "(DATE(pd.appliedtime) LIKE CONCAT('%',:day,'%') AND "
			+ "DATE(pd.appliedtime) LIKE CONCAT('%',:month,'%') AND "
			+ "DATE(pd.appliedtime) LIKE CONCAT('%',:year,'%')) OR "
			
			+ "LOWER(pd.leavetype) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leaveCategory) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.leavestatus) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.teamlead) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsProjectControler) LIKE LOWER(CONCAT('%',:searchterm,'%')) OR "
			+ "LOWER(pd.commentsTeamLead) LIKE LOWER(CONCAT('%',:searchterm,'%')))")
	public Page<LeaveRecords> getLeaveRecordLeader(Pageable pageable,@Param("searchterm") String searchterm,@Param("day") String day,@Param("month") String month,@Param("year") String year,@Param("status") String st,@Param("leader") String leader);


	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
}
