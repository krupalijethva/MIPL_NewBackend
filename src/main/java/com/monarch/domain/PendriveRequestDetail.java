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
@Table(name = "pendrivedetail")
public class PendriveRequestDetail 
{
	public enum Status {
        Pending, Approved, Denied,Return;
    }

    @Id
    @GeneratedValue
    @Column(name = "idpendrivedetail")
    private Long pendrive_id;

    @Column(name = "username")
    private String username;

    @Column(name = "issuedate")
    private Date issuedate;

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

	public Long getPendrive_id() {
		return pendrive_id;
	}

	public void setPendrive_id(Long pendrive_id) {
		this.pendrive_id = pendrive_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getIssuedate() {
		return issuedate;
	}

	public void setIssuedate(Date issuedate) {
		this.issuedate = issuedate;
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
	
 	
    
}
