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
@Table(name = "activitydetail")
public class ActivityDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "activity_id")
    private Long activity_id;
    @Column(name = "activity_name")
    private String activity_name;
    @Column(name = "activity_institute")
    private String activity_institute;
    @Column(name = "year")
    private Date year;
    @Column(name = "prize_won")
    private String prize_won;
    // @Column(name = "candidate_id")
    // private Integer candidate_id;
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
   
    
    public Long getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(Long activity_id) {
        this.activity_id = activity_id;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_institute() {
        return activity_institute;
    }

    public void setActivity_institute(String activity_institute) {
        this.activity_institute = activity_institute;
    }

    public Date getYear() {
        return year;
    }

    public void setYear(Date year) {
        this.year = year;
    }

    public String getPrize_won() {
        return prize_won;
    }

    public void setPrize_won(String prize_won) {
        this.prize_won = prize_won;
    }

    // public Integer getCandidate_id() {
    //   return candidate_id;
    //}
    // public void setCandidate_id(Integer candidate_id) {
    //    this.candidate_id = candidate_id;
    // }
   

}
