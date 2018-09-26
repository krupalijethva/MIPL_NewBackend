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
@Table(name = "stationeryitemdetail")
public class StationeryItemRequestDetail {

	


    public enum Status {
        Pending, Approved, Denied, Return;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "issuedate")
    private Date issuedate;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    //private String status;
    private Status status;

    @Column(name = "requestdate")
    private Date requestdate;
    
    @Column(name = "selectitem")
    private String selectitem;

    @Column(name = "comments")
    private String comments;
    
    @Column(name = "noofquantity")
    private int noofquantity;
 
    @Column(name = "returndate")
    private Date returndate;
            
    public Long getId() {
        return id;
   }

    public void setId(Long id) {
        this.id = id;
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

    public String getSelectitem() {
        return selectitem;
    }

    public void setSelectitem(String selectitem) {
        this.selectitem = selectitem;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getNoofquantity() {
        return noofquantity;
    }

    public void setNoofquantity(int noofquantity) {
        this.noofquantity = noofquantity;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

	
	
	
}
