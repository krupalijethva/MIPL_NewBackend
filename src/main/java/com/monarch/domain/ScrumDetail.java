/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author bhavishar
 */
@Entity
@Table(name = "scrumdetail")
public class ScrumDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "idscrumdetail")
    private long idscrumdetail;

    @Column(name = "scrumdate")
    private Date scrumdate;

    @Column(name = "projectname")
    private String projectname;

    @Column(name = "teamleadername")
    private String teamleadername;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "scrumdetail_user1", joinColumns = {
        @JoinColumn(name = "idscrumdetail", nullable = false, updatable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "idUser1",
                        nullable = false, updatable = false)})
    private Set<User> teamleaders;

    @Column(name = "todaystask")
    private String todaystask;

    @Column(name = "tomorrowstask")
    private String tomorrowstask;

    @Column(name = "username")
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUser1", nullable = false)
    private User user;

    //TEMP
    @Column(name = "notAssignwork")
    private boolean notAssignWork;

    //TEMP
    @Column(name = "issues")
    private String issues;

    @Column(name = "todaysComment")
    private String todaysComment;

    @Column(name = "tomorrowsComment")
    private String tomorrowsComment;

    public long getIdscrumdetail() {
        return idscrumdetail;
    }

    public void setIdscrumdetail(long idscrumdetail) {
        this.idscrumdetail = idscrumdetail;
    }

    public Date getScrumdate() {
        return scrumdate;
    }

    public void setScrumdate(Date scrumdate) {
        this.scrumdate = scrumdate;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTeamleadername() {
        return teamleadername;
    }

    public void setTeamleadername(String teamleadername) {
        this.teamleadername = teamleadername;
    }

    public String getTodaystask() {
        return todaystask;
    }

    public void setTodaystask(String todaystask) {
        this.todaystask = todaystask;
    }

    public String getTomorrowstask() {
        return tomorrowstask;
    }

    public void setTomorrowstask(String tomorrowstask) {
        this.tomorrowstask = tomorrowstask;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getTeamleaders() {
        return teamleaders;
    }

    public void setTeamleaders(Set<User> teamleaders) {
        this.teamleaders = teamleaders;
    }

    //TEMP
    public boolean isNotAssignWork() {
        return notAssignWork;
    }

    public void setNotAssignWork(boolean notAssignWork) {
        this.notAssignWork = notAssignWork;
    }

    //TEMP
    public String getIssues() {
        return issues;
    }

    public void setIssues(String issues) {
        this.issues = issues;
    }

    public String getTodaysComment() {
        return todaysComment;
    }

    public void setTodaysComment(String todaysComment) {
        this.todaysComment = todaysComment;
    }

    public String getTomorrowsComment() {
        return tomorrowsComment;
    }

    public void setTomorrowsComment(String tomorrowsComment) {
        this.tomorrowsComment = tomorrowsComment;
    }
    
    

}
