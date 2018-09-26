/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain;

import com.monarch.utils.DateUtil;


import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.TimeZone;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vishald
 */
@Entity
@Table(name = "punchingdetail")
public class PunchingDetail implements Serializable {

    public void setAc_hours(BigDecimal totalhrs) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public enum Status {
        NONE, LOGGEDIN, BREAKOUT, INCOMPLETE, COMPLETE,MODIFIED, UNDER_REVIEW, DENIED,LATEENTRY;
    }

    @Id
    @GeneratedValue
    @Column(name = "idPunching")
    private long idPunching;

    @Column(name = "username")
    private String username;

    @Temporal(TemporalType.DATE)
    @Column(name = "logindate")
    private Date logindate;

    @Column(name = "loginTime")
    private Date loginTime;

    @Column(name = "logoutTime")
    private Date logoutTime;

    @Column(name = "breakTime")
    private String breakTime;

    @Column(name = "totalHours")
    private Time totalHours;

    @Enumerated(EnumType.STRING)
    @Column(name = "punchStatus")
    private Status punchStatus = Status.NONE;

    @Column(name = "comments")
    private String comments;
    
    @Column(name = "commentsEmployee")
    private String commentsEmployee;
	
	 //TEMP
    @Column(name = "ipAddress")
    private String ipAddress;   
    
    //TEMP
    @Column(name = "hostName")
    private String hostName;  
    
    @Column(name = "ac_hours")
    private String ac_hours;
    
    public Status getPunchStatus() {
        return punchStatus;
    }

    public void setPunchStatus(Status punchStatus) {
        this.punchStatus = punchStatus;
    }

    public Time getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Time totalHours) {
        this.totalHours = totalHours;
    }

    public PunchingDetail() {
        logindate = new Date();
    }

    public long getIdPunching() {
        return idPunching;
    }

    public void setIdPunching(long idPunching) {
        this.idPunching = idPunching;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLogindate() {
        return logindate;
    }

    public void setLogindate(Date logindate) {
        this.logindate = logindate;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCommentsEmployee() {
        return commentsEmployee;
    }

    public void setCommentsEmployee(String commentsEmployee) {
        this.commentsEmployee = commentsEmployee;
    }

	public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

	
    public void calculateTotalHours() {

        long totalHrs = getLogoutTime().getTime() - getLoginTime().getTime();
        //totalHours = new Time(diff - TimeZone.getDefault().getRawOffset());

        long breakTime2 = calculateBreakTime();
        totalHours = new Time(totalHrs - TimeZone.getDefault().getRawOffset() - breakTime2);
    }

    public long calculateBreakTime() {
        return DateUtil.calculateBreakTime(breakTime);
    }

    public String getAc_hours() {
        return ac_hours;
    }

    public void setAc_hours(String ac_hours) {
        this.ac_hours = ac_hours;
    }

    
    
}
