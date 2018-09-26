package com.monarch.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.LeaveRecords;


public interface LeaveService {
	
	 	public int getWorkingdays(String startDate, String endDate);

	    public Date stringToDate(String date);

	    public Date stringToDateTime(String datetime);

	    public long stringToTime(String timeStr);

	    public String timeToString(long time);

	    public List<LeaveRecords> readTeamMemberLeave(String leaderName);

	    public List<LeaveRecords> readApprovedLeave(String projectController);
	    
	    public List<LeaveRecords> monthlyLeaveRecords(Date startDate, Date endDate);
	    
	    public long getTotalCredit(String username);
	    
	    public LeaveRecords getLeave(long id);
	    
	    public void deleteLeave(LeaveRecords ls);
	   
	    public void create(LeaveRecords ls);
	    //dao services
	   
	    public List<LeaveRecords> readAllLeaveSearchbyId(long id);
	   
	    public Pageable createPageRequest(int pageno,int perpage,String sort,String title);   
	    public Page<LeaveRecords> getAllLeaveRecords(int pageno,int perpage,String sort,String title,long leaveid,String generalSearch,String status);

	    public Page<LeaveRecords> getLeaveRecords(int pageno, int perpage, String sort, String title,String punchstatus,String generalsearch);
	    
	    public Page<LeaveRecords> getLeaveRecords(int pageno, int perpage, String sort, String title,String punchstatus,String generalsearch,String leader);
	    public Pageable createPageRequest1(int pageno, int perpage, String sort, String title);
	    
	    
	    
	
}





