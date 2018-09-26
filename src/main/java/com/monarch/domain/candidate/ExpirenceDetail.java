/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain.candidate;

/**
 *
 * @author bhumikap
 */

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "expirencedetail")
public class ExpirenceDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "expirence_id")
    private Long expirence_id;
    @Column(name = "company_name")
    private String company_name;
    @Column(name = "designation")
    private String designation;
    @Column(name = "joining_date")
    private Date joining_date;
    @Column(name = "reliving_date")
    private Date reliving_date;
    @Column(name = "nature_duties")
    private String nature_duties;
    @Column(name = "notice_period")
    private String notice_period;

//    @Column(name = "candidate_id")
//    private Integer candidate_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserDetail userdetail;

    public UserDetail getUserdetail() {
        return userdetail;
    }

    public void setUserdetail(UserDetail userdetail) {
        this.userdetail = userdetail;
    }

    public Long getExpirence_id() {
        return expirence_id;
    }

    public void setExpirence_id(Long expirence_id) {
        this.expirence_id = expirence_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Date getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(Date joining_date) {
        this.joining_date = joining_date;
    }

    public Date getReliving_date() {
        return reliving_date;
    }

    public void setReliving_date(Date reliving_date) {
        this.reliving_date = reliving_date;
    }

    public String getNature_duties() {
        return nature_duties;
    }

    public void setNature_duties(String nature_duties) {
        this.nature_duties = nature_duties;
    }

    public String getNotice_period() {
        return notice_period;
    }

    public void setNotice_period(String notice_period) {
        this.notice_period = notice_period;
    }

//    public Integer getCandidate_id() {
//        return candidate_id;
//    }
//
//    public void setCandidate_id(Integer candidate_id) {
//        this.candidate_id = candidate_id;
//    }
   
}
