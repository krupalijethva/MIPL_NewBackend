package com.monarch.domain;

import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.monarch.utils.DateUtil;

@Entity
@Table(name = "punchingdetailedit")
public class PunchingDetailEdit {


	    private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Basic(optional = false)
	    @Column(name = "idPunching")
	    private long idPunching;

	    @Column(name = "logindate")
	    @Temporal(TemporalType.DATE)
	    private Date logindate;

	    @Column(name = "username")
	    private String username;

	    @Column(name = "loginTime")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date loginTime;

	    @Column(name = "logoutTime")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date logoutTime;

	    @Column(name = "breakTime")
	    private String breakTime;

	    @Column(name = "loginTime_edit")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date loginTimeedit;

	    @Column(name = "logoutTime_edit")
	    @Temporal(TemporalType.TIMESTAMP)
	    private Date logoutTimeedit;

	    @Column(name = "breakTime_edit")
	    private String breakTimeedit;

	    @Column(name = "teamlead")
	    private String teamlead;

	    @Column(name = "totalHours")
	    private Time totalHours;

	   
		@Column(name = "totalHoursedit")
	    private Time totalHoursedit;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "punchStatus")
	    private PunchingDetail.Status punchStatus;

	    @Column(name = "comments")
	    private String comments;

	    @Column(name = "employeeComments")
	    private String employeeComments;

	    public PunchingDetailEdit() {
	    }

	    public PunchingDetailEdit(PunchingDetail pd) {
	        this.logindate = pd.getLogindate();
	        this.username = pd.getUsername();
	        this.loginTime = pd.getLoginTime();
	        this.logoutTime = pd.getLogoutTime();
	        this.breakTime = pd.getBreakTime();
	        this.totalHours = pd.getTotalHours();
	    }

	    public PunchingDetailEdit(Integer idPunching) {
	        this.idPunching = idPunching;
	    }

	    public long getIdPunching() {
	        return idPunching;
	    }

	    public void setIdPunching(Integer idPunching) {
	        this.idPunching = idPunching;
	    }

	    public Date getLogindate() {
	        return logindate;
	    }

	    public void setLogindate(Date logindate) {
	        this.logindate = logindate;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public Date getLoginTime() {
	        return loginTime;
	    }

	    public void setLoginTime(Date loginTime) {
	        this.loginTime = loginTime;
	    }

	    public Date getLogoutTime() {
	        return logoutTime;
	    }

	    public void setLogoutTime(Date logoutTime) {
	        this.logoutTime = logoutTime;
	    }

	    public String getBreakTime() {
	        return breakTime;
	    }

	    public void setBreakTime(String breakTime) {
	        this.breakTime = breakTime;
	    }

	    public Date getLoginTimeedit() {
	        return loginTimeedit;
	    }

	    public void setLoginTimeedit(Date loginTimeedit) {
	        this.loginTimeedit = loginTimeedit;
	    }

	    public Date getLogoutTimeedit() {
	        return logoutTimeedit;
	    }

	    public void setLogoutTimeedit(Date logoutTimeedit) {
	        this.logoutTimeedit = logoutTimeedit;
	    }

	    public String getBreakTimeedit() {
	        return breakTimeedit;
	    }

	    public void setBreakTimeedit(String breakTimeedit) {
	        this.breakTimeedit = breakTimeedit;
	    }

	    public String getTeamlead() {
	        return teamlead;
	    }

	    public void setTeamlead(String teamlead) {
	        this.teamlead = teamlead;
	    }

	 


	  

	    public Time getTotalHours() {
			return totalHours;
		}

		public void setTotalHours(Time totalHours) {
			this.totalHours = totalHours;
		}

		public Time getTotalHoursedit() {
			return totalHoursedit;
		}

		public void setTotalHoursedit(Time totalHoursedit) {
			this.totalHoursedit = totalHoursedit;
		}

		public PunchingDetail.Status getPunchStatus() {
	        return punchStatus;
	    }

	    public void setPunchStatus(PunchingDetail.Status punchStatus) {
	        this.punchStatus = punchStatus;
	    }

	    public String getComments() {
	        return comments;
	    }

	    public void setComments(String comments) {
	        this.comments = comments;
	    }

	    public String getEmployeeComments() {
	        return employeeComments;
	    }

	    public void setEmployeeComments(String employeeComments) {
	        this.employeeComments = employeeComments;
	    }

	    @Override
	    public String toString() {
	        return "com.monarch.domain.Punchingdetailedit[ idPunching=" + idPunching + " ]";
	    }

	    public void calculateTotalHoursEdit() {
	        try {

	            long totalHrs = getLogoutTimeedit().getTime() - getLoginTimeedit().getTime();
	            //totalHours = new Time(diff - TimeZone.getDefault().getRawOffset());

	            long breakTime2 = calculateBreakTimeedit();
	            totalHoursedit = new Time(totalHrs - TimeZone.getDefault().getRawOffset() - breakTime2);
	        } catch (Exception e) {
	            System.out.println("com.monarch.domain.PunchingDetailEdit.calculateTotalHoursEdit()" + e.getMessage());
	        }
	    }

	    private long calculateBreakTimeedit() {
	        return DateUtil.calculateBreakTime(breakTimeedit);
	    }
	}

	
	
	
	


