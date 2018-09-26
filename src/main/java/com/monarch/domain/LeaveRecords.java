package com.monarch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.monarch.service.LeaveService;
import com.monarch.service.LeaveServiceImpl;

@Entity
@Table(name = "leaverecord")
public class LeaveRecords {
	
	
	
	
	 public void setUserleavedetail(long userleaveId)
	 	{
	        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	    }


	    public enum Status {
	        PENDING_TEAMLEAD, PENDING_ADMIN, APPROVED, REJECT, CANCEL,CANCELLED;
	    }

	    public enum LeaveDurationType {
	        NONE, FULLDAY, PARTIAL_DAY;
	    }

	    @Id
	    @GeneratedValue
	    @Column(name = "id")
	    private long id;

	    @Column(name = "teamLead")
	    private String teamlead;

	    @Column(name = "notificationLeader")
	    private String notificationleader;

	    @Column(name = "leaveType")
	    private String leavetype;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "durationType")
	    private LeaveDurationType durationtype = LeaveDurationType.NONE;

	    @Column(name = "fromDate")
	    private Date fromdate;

	    @Column(name = "toDate")
	    private Date todate;

	    @Column(name = "commentsProjectControler")
	    private String commentsProjectControler;

	    @Column(name = "commentsEmployee")
	    private String commentsEmployee;

	    @Column(name = "commentsTeamLead")
	    private String commentsTeamLead;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "leaveStatus")
	    private Status leavestatus = Status.PENDING_TEAMLEAD;

	    @Column(name = "duration")
	    private long duration;

	    @Column(name = "appliedTime")
	    private Date appliedtime;

	    @Column (name = "leaveCategory")
	    private String leaveCategory;
	    
	   

	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "leaveID", nullable = false)
	    private UserLeaveDetail userleavedetail;

	    public long getId() {
	        return id;
	    }

	    public void setId(long id) {
	        this.id = id;
	    }

	    public String getTeamlead() {
	        return teamlead;
	    }

	    public void setTeamlead(String teamlead) {
	        this.teamlead = teamlead;
	    }

	    public String getCommentsEmployee() {
	        return commentsEmployee;
	    }

	    public void setCommentsEmployee(String commentsEmployee) {
	        this.commentsEmployee = commentsEmployee;
	    }

	    public String getCommentsTeamLead() {
	        return commentsTeamLead;
	    }

	    public void setCommentsTeamLead(String commentsTeamLead) {
	        this.commentsTeamLead = commentsTeamLead;
	    }

	    public String getNotificationleader() {
	        return notificationleader;
	    }

	    public void setNotificationleader(String notificationleader) {
	        this.notificationleader = notificationleader;
	    }

	    public String getLeavetype() {
	        return leavetype;
	    }

	    public void setLeavetype(String leavetype) {
	        this.leavetype = leavetype;
	    }

	    public LeaveDurationType getDurationtype() {
	        return durationtype;
	    }

	    public void setDurationtype(LeaveDurationType durationtype) {
	        this.durationtype = durationtype;
	    }

	    public Date getFromdate() {
	        return fromdate;
	    }

	    public void setFromdate(Date fromdate) {
	        this.fromdate = fromdate;
	    }

	    public Date getTodate() {
	        return todate;
	    }

	    public void setTodate(Date todate) {
	        this.todate = todate;
	    }

	    public String getCommentsProjectControler() {
	        return commentsProjectControler;
	    }

	    public void setCommentsProjectControler(String commentsProjectControler) {
	        this.commentsProjectControler = commentsProjectControler;
	    }

	    public Status getLeavestatus() {
	        return leavestatus;
	    }

	    public void setLeavestatus(Status leavestatus) {
	        this.leavestatus = leavestatus;
	    }

	    public long getDuration() {
	        return duration;
	    }

	    public void setDuration(long duration) {
	        this.duration = duration;
	    }

	    public Date getAppliedtime() {
	        return appliedtime;
	    }

	    public void setAppliedtime(Date appliedtime) {
	        this.appliedtime = appliedtime;
	    }

	    public UserLeaveDetail getUserleavedetail() {
	        return userleavedetail;
	    }

	    public void setUserleavedetail(UserLeaveDetail userleavedetail) {
	        this.userleavedetail = userleavedetail;
	    }

	    public long calculateDuration() {
	        long duration = 0;
	        LeaveDurationType durationType = getDurationtype();
	        LeaveService ls = new LeaveServiceImpl();

	        Date fromDate;
	        Date toDate;
	        long totalDuration = 0;

	        String startDate = getFromdate().toString();
	        String endDate = getTodate().toString();

	        if (durationType.equals(LeaveDurationType.FULLDAY)) {

	            int workingDays = ls.getWorkingdays(startDate, endDate);
	            int leaveHours = (int) (workingDays * 8);
	            String time = leaveHours + ":00:00";

	            totalDuration = ls.stringToTime(time);

	        } else if (durationType.equals(LeaveDurationType.PARTIAL_DAY)) {
	            fromDate = ls.stringToDateTime(startDate);
	            toDate = ls.stringToDateTime(endDate);

	            totalDuration = toDate.getTime() - fromDate.getTime();
	        }
	        return duration;
	    }

	   

	    public String getLeaveCategory() {
	        return leaveCategory;
	    }

	    public void setLeaveCategory(String leaveCategory) {
	        this.leaveCategory = leaveCategory;
	    }
	    
	    

	    public String getDurationValue() {
	        String durationVal;
	        long time = getDuration();

	        long totalSecs = time / 1000;
	        long hours = (totalSecs / 3600);
	        long mins = (totalSecs / 60) % 60;
	        long secs = totalSecs % 60;

	        if (getDurationtype().equals(LeaveDurationType.FULLDAY)) {
	            int days = (int) (hours / 8);
	           // durationVal = days + "   days";
	            
	            String days1 = String.valueOf(days);
	            durationVal = days1 + "." + "00" +":" + "00" + ":" + "00";

	        } else {
	            //durationVal = hours + ":" + mins + "   hours";
	            durationVal = "0" + "." + hours + ":" + mins + ":" + "00";
	        }

	        return durationVal;
	    }
	    
	    public String LeaveCredit(long creditVal) {
	        String durationVal = "";

	        long time = creditVal;

	        long totalSecs = time / 1000;
	        long hours = (totalSecs / 3600);
	        long mins = (totalSecs / 60) % 60;
	        //long secs = totalSecs % 60;

	        int days = (int) (hours / 8);
	        int totalHours = (int) (hours % 8);
	       // int mins=(int) (hours/8) % 8;

	        durationVal = days + "   days" + "  " + totalHours + "   hours"+ " "+ + mins + "  mins";
	        return durationVal;
	    }
	
	
	

}
