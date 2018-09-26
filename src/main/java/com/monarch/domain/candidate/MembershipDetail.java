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
@Table(name = "membershipdetail")
public class MembershipDetail implements Serializable{

    @Id
    @GeneratedValue
    @Column(name = "membership_id")
    private Long membership_id;
    @Column(name = "membership_name")
    private String membership_name;
    @Column(name = "expiry_date")
    private Date expiry_date;
    //@Column(name = "candidate_id")
    //private Integer candidate_id;
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
   

    public Long getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(Long membership_id) {
        this.membership_id = membership_id;
    }

    public String getMembership_name() {
        return membership_name;
    }

    public void setMembership_name(String membership_name) {
        this.membership_name = membership_name;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

//    public Integer getCandidate_id() {
//        return candidate_id;
//    }
//
//    public void setCandidate_id(Integer candidate_id) {
//        this.candidate_id = candidate_id;
//    }
  

}
