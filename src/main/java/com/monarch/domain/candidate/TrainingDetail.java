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
@Table(name = "trainingdetail")
public class TrainingDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "training_id")
    private Long training_id;
    @Column(name = "course_name")
    private String course_name;
    @Column(name = "from_date")
    private Date from_date;
    @Column(name = "to_date")
    private Date to_date;
    @Column(name = "institute")
    private String institute;
    @Column(name = "remark")
    private String remark;
    //  @Column(name = "candidate_id")
    //   private Integer candidate_id;
    
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
    

    public Long getTraining_id() {
        return training_id;
    }

    public void setTraining_id(Long training_id) {
        this.training_id = training_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

//    public Integer getCandidate_id() {
//        return candidate_id;
//    }
//
//    public void setCandidate_id(Integer candidate_id) {
//        this.candidate_id = candidate_id;
//    }
   

}
