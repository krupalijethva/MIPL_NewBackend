package com.monarch.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "internetaccessdetail")
public class InternetAccessRequestDetail {

	    public enum Status {
	        Pending, Approved, Denied,RemovedAccess;
	    }
	    
	    @Id
	    @GeneratedValue
	    @Column(name = "internetaccessid")
	    private Long internetaccessid;

	    @Column(name = "username")
	    private String username;

	    @Column(name = "accessdate")
	    private Date accessdate;

	    @Column(name = "returndate")
	    private Date returndate;

	    @Column(name = "description")
	    private String description;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "status")
	    //private String status;
	     private Status status ;
	    
	    @Column(name = "requestdate")
	    private Date requestdate;
	 
	    @Column(name = "comments")
	    private String comments;
	    
	    @Column(name = "duration")
	    private String duration;

	    public Long getInternetaccessid() {
	        return internetaccessid;
	    }

	    public void setInternetaccessid(Long internetaccessid) {
	        this.internetaccessid = internetaccessid;
	    }

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

	    public Date getAccessdate() {
	        return accessdate;
	    }

	    public void setAccessdate(Date accessdate) {
	        this.accessdate = accessdate;
	    }

	    public Date getReturndate() {
	        return returndate;
	    }

	    public void setReturndate(Date returndate) {
	        this.returndate = returndate;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public Status getStatus() {
	        return status;
	    }

	    public void setStatus(Status status) {
	        this.status = status;
	    }

	    public Date getRequestdate() {
	        return requestdate;
	    }

	    public void setRequestdate(Date requestdate) {
	        this.requestdate = requestdate;
	    }

	    public String getComments() {
	        return comments;
	    }

	    public void setComments(String comments) {
	        this.comments = comments;
	    }

	    public String getDuration() {
	        return duration;
	    }

	    public void setDuration(String duration) {
	        this.duration = duration;
	    }
	    
}
