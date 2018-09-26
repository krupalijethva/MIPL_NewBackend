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
@Table(name = "qualificationdetail")
public class QualificationDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "qualification_id")
    private Long qualification_id;
    
    
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "speicalization")
    private String speicalization;
    @Column(name = "school_collage")
    private String school_collage;
    @Column(name = "board_uni")
    private String board_uni;
    @Column(name = "startdate")
    private Date startdate;
    @Column(name = "enddate")
    private Date enddate;
    @Column(name="persentage")
    private String persentage;
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
     

    public Long getQualification_id() {
        return qualification_id;
    }

    public void setQualification_id(Long qualification_id) {
        this.qualification_id = qualification_id;
    }

   

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpeicalization() {
        return speicalization;
    }

    public void setSpeicalization(String speicalization) {
        this.speicalization = speicalization;
    }

    public String getSchool_collage() {
        return school_collage;
    }

    public void setSchool_collage(String school_collage) {
        this.school_collage = school_collage;
    }

    public String getBoard_uni() {
        return board_uni;
    }

    public void setBoard_uni(String board_uni) {
        this.board_uni = board_uni;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

  

//    public Integer getCandidate_id() {
//        return candidate_id;
//    }
//
//    public void setCandidate_id(Integer candidate_id) {
//        this.candidate_id = candidate_id;
//    }

    public String getPersentage() {
        return persentage;
    }

    public void setPersentage(String persentage) {
        this.persentage = persentage;
    }

}
