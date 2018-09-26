/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain;

import java.io.Serializable;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author akanxis
 */
@Entity
@Table(name = "userleavedetail")
public class UserLeaveDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "leaveID")
    private long leaveid;

    @Column(name = "leaveCredit")
    private double leavecredit;

    @Column(name = "totalLeave")
    private int totalleave;

    @Column(name = "openingBalance")
    private long openingbalance;

    @Column(name = "joiningMonth")
    private int joiningMonth;


   
    @OneToOne
    @JoinColumn(name = "idUser1")
    private User user;

    //temp

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userleavedetail")
    private Set<LeaveRecords> leaveRecords;

    public long getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(long leaveid) {
        this.leaveid = leaveid;
    }

    public double getLeavecredit() {
        return leavecredit;
    }

    public void setLeavecredit(double leavecredit) {
        this.leavecredit = leavecredit;
    }

    public int getTotalleave() {
        return totalleave;
    }

    public void setTotalleave(int totalleave) {
        this.totalleave = totalleave;
    }

    public long getOpeningbalance() {
        return openingbalance;
    }

    public void setOpeningbalance(long openingbalance) {
        this.openingbalance = openingbalance;
    }

    public int getJoiningMonth() {
        return joiningMonth;
    }

    public void setJoiningMonth(int joiningMonth) {
        this.joiningMonth = joiningMonth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<LeaveRecords> getLeaveRecords() {
        return leaveRecords;
    }

    public void setLeaveRecords(Set<LeaveRecords> leaveRecords) {
        this.leaveRecords = leaveRecords;
    }

    public String getLeaveBalance(long leaveCredit) {
        String leaveBalance;

        long totalSecs = leaveCredit / 1000;
        long hours = (totalSecs / 3600);
        long mins = (totalSecs / 60) % 60;

        int days = (int) (hours / 8);
        int totalHours = (int) (hours % 8);

        if (hours % 8 == 0) {
            leaveBalance = days + " days";
        } else {
            leaveBalance = days + "-days" + "  " + totalHours + "-hours";
        }

        return leaveBalance;
    }
}
