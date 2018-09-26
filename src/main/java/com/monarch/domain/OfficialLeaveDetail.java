package com.monarch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "officialleave")
public class OfficialLeaveDetail {

	 	@Id
	    @GeneratedValue
	   
	    @Column(name = "idofficialleave")
	    private long idofficialleave;

	    @Column(name = "LeaveDate")
	    private Date leaveDate;

	    @Column(name = "Day")
	    private String day;

	    @Column(name = "Festival")
	    private String festival;

	    public long getIdofficialleave() {
	        return idofficialleave;
	    }

	    public void setIdofficialleave(long idofficialleave) {
	        this.idofficialleave = idofficialleave;
	    }

	    public Date getLeaveDate() {
	        return leaveDate;
	    }

	    public void setLeaveDate(Date leaveDate) {
	        this.leaveDate = leaveDate;
	    }

	    public String getDay() {
	        return day;
	    }

	    public void setDay(String day) {
	        this.day = day;
	    }

	    public String getFestival() {
	        return festival;
	    }

	    public void setFestival(String festival) {
	        this.festival = festival;
	    }
	   
	
	
	
}
