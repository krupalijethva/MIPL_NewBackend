/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain;

import java.io.Serializable;


import java.util.Set;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.monarch.domain.candidate.EmployeeDetail;




/**
 *
 * @author vishald
 */
@Entity
@Table(name = "user1")
public class User implements Serializable {
  public enum Status {
        MORNING, GENERAL, NIGHT;
    }
  
  public enum ErrorStatus {
        ERROR, SUCCESS, FAIL;
    }
  
  	@Id
    @GeneratedValue
    @Column(name = "idUser1")
    private long id;

    @Column(name = "userName")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "employeeid")
    private String employeeid;

    @Column(name = "fullname")
    private String fullname;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "shift_type")
    //private String shifttype;
    private Status shifttype;
    
   //@Enumerated(EnumType.STRING)
    @Column(name="userStatus")
    private String userStatus;
    
    @Column(name="ACId")
    private int acid;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<ScrumDetail> scrumDetails;

    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "scrumdetail_user1", joinColumns = {
        @JoinColumn(name = "idUser1", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "idscrumdetail",
                        nullable = false, updatable = false)})
    private Set<ScrumDetail> teamScrumDetails;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user1", cascade = CascadeType.ALL)
//    private UserLeaveDetail leavedetail;

   @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private UserLeaveDetail leavedetail;

  
   @OneToOne(fetch = FetchType.EAGER, mappedBy = "user1", cascade = CascadeType.ALL)
   @JsonIgnoreProperties("user1")
   private EmployeeDetail employeedetail;
    
  
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user1", cascade = CascadeType.ALL)
    private SalaryDetail salarydetail;

    public EmployeeDetail getEmployeedetail() {
        return employeedetail;
    }

    public void setEmployeedetail(EmployeeDetail employeedetail) {
        this.employeedetail = employeedetail;
    }
   

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<ScrumDetail> getScrumDetails() {
        return scrumDetails;
    }

    public void setScrumDetails(Set<ScrumDetail> scrumDetails) {
        this.scrumDetails = scrumDetails;
    }

    public Set<ScrumDetail> getTeamScrumDetails() {
        return teamScrumDetails;
    }

    public void setTeamScrumDetails(Set<ScrumDetail> teamScrumDetails) {
        this.teamScrumDetails = teamScrumDetails;
    }

    public UserLeaveDetail getLeavedetail() {
        return leavedetail;
    }

    public void setLeavedetail(UserLeaveDetail leavedetail) {
        this.leavedetail = leavedetail;
    }

    public SalaryDetail getSalarydetail() {
        return salarydetail;
    }

    public void setSalarydetail(SalaryDetail salarydetail) {
        this.salarydetail = salarydetail;
    }

    public Status getShifttype() {
        return shifttype;
    }

    public void setShifttype(Status shifttype) {
        this.shifttype = shifttype;
    }

   public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public int getAcid() {
        return acid;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", employeeid=" + employeeid
				+ ", fullname=" + fullname + ", shifttype=" + shifttype + ", userStatus=" + userStatus + ", acid="
				+ acid + ", scrumDetails=" + scrumDetails + ", teamScrumDetails=" + teamScrumDetails + ", leavedetail="
				+ leavedetail + ", employeedetail=" + employeedetail + ", salarydetail=" + salarydetail + "]";
	}


    
    
    

}
