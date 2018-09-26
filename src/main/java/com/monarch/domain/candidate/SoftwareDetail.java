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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Table(name = "softwaredetail")
public class SoftwareDetail implements Serializable {
@Id
    @GeneratedValue
    @Column(name = "softwatre_id")
    private Long softwatre_id;
    @Column(name = "software_name")
    private String software_name;
    @Column(name = "proficiency")
    private String proficiency;

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
    
    public Long getSoftwatre_id() {
        return softwatre_id;
    }

    public void setSoftwatre_id(Long softwatre_id) {
        this.softwatre_id = softwatre_id;
    }

    public String getSoftware_name() {
        return software_name;
    }

    public void setSoftware_name(String software_name) {
        this.software_name = software_name;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }

//    public Integer getCandidate_id() {
//        return candidate_id;
//    }
//
//    public void setCandidate_id(Integer candidate_id) {
//        this.candidate_id = candidate_id;
//    }
   
}
