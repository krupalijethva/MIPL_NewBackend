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
@Table(name = "languagedetail")
public class LanguageDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "lanuguage_id")
    private Long lanuguage_id;
    @Column(name = "lanuguage")
    private String lanuguage;

    @Column(name = "speak_level")
    private String speak_level;
    @Column(name = "read_level")
    private String read_level;
    @Column(name = "write_level")
    private String write_level;

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
    

    public Long getLanuguage_id() {
        return lanuguage_id;
    }

    public void setLanuguage_id(Long lanuguage_id) {
        this.lanuguage_id = lanuguage_id;
    }

    public String getLanuguage() {
        return lanuguage;
    }

    public void setLanuguage(String lanuguage) {
        this.lanuguage = lanuguage;
    }

    public String getSpeak_level() {
        return speak_level;
    }

    public void setSpeak_level(String speak_level) {
        this.speak_level = speak_level;
    }

    public String getRead_level() {
        return read_level;
    }

    public void setRead_level(String read_level) {
        this.read_level = read_level;
    }

    public String getWrite_level() {
        return write_level;
    }

    public void setWrite_level(String write_level) {
        this.write_level = write_level;
    }

   

}
