/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "salarydetail")
public class SalaryDetail implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "salary_id")
    private Integer salary_id;
    @Column(name = "ctc")
    private Double ctc;
    @Column(name = "lwp")
    private Double lwp;
    @Column(name = "basic")
    private Double basic;
    @Column(name = "hra")
    private Double hra;
    @Column(name = "conveyance")
    private Double conveyance;
    @Column(name = "spll_allow")
    private Double spll_allow;
    @Column(name = "other_allow")
    private Double other_allow;
    @Column(name = "gross")
    private Double gross;
    @Column(name = "pf_employer")
    private Double pf_employer;
    @Column(name = "esi_employer")
    private Double esi_employer;
    @Column(name = "bonus")
    private Double bonus;
    @Column(name = "gratuity")
    private Double gratuity;
    @Column(name = "ctc_calculated")
    private Double ctc_calculated;
    @Column(name = "pf_employee")
    private Double pf_employee;
    @Column(name = "esi_employee")
    private Double esi_employee;
    @Column(name = "prof_tax")
    private Double prof_tax;
    @Column(name = "net_salary")
    private Double net_salary;
    @Column(name = "pf")
    private Double pf;
    @Column(name = "esi")
    private Double esi;
    @Column(name = "employer_countri")
    private Double employer_countri;
    @Column(name = "employee_countri")
    private Double employee_countri;

    @Column(name = "working_days")
    private Double working_days;

    @OneToOne
    @JoinColumn(name = "idUser1")
    private User user1;

    public Integer getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(Integer salary_id) {
        this.salary_id = salary_id;
    }

    public Double getCtc() {
        return ctc;
    }

    public void setCtc(Double ctc) {
        this.ctc = ctc;
    }

    public Double getLwp() {
        return lwp;
    }

    public void setLwp(Double lwp) {
        this.lwp = lwp;
    }

    public Double getBasic() {
        return basic;
    }

    public void setBasic(Double basic) {
        this.basic = basic;
    }

    public Double getHra() {
        return hra;
    }

    public void setHra(Double hra) {
        this.hra = hra;
    }

    public Double getConveyance() {
        return conveyance;
    }

    public void setConveyance(Double conveyance) {
        this.conveyance = conveyance;
    }

    public Double getSpll_allow() {
        return spll_allow;
    }

    public void setSpll_allow(Double spll_allow) {
        this.spll_allow = spll_allow;
    }

    public Double getOther_allow() {
        return other_allow;
    }

    public void setOther_allow(Double other_allow) {
        this.other_allow = other_allow;
    }

    public Double getGross() {
        return gross;
    }

    public void setGross(Double gross) {
        this.gross = gross;
    }

    public Double getPf_employer() {
        return pf_employer;
    }

    public void setPf_employer(Double pf_employer) {
        this.pf_employer = pf_employer;
    }

    public Double getEsi_employer() {
        return esi_employer;
    }

    public void setEsi_employer(Double esi_employer) {
        this.esi_employer = esi_employer;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Double getGratuity() {
        return gratuity;
    }

    public void setGratuity(Double gratuity) {
        this.gratuity = gratuity;
    }

    public Double getCtc_calculated() {
        return ctc_calculated;
    }

    public void setCtc_calculated(Double ctc_calculated) {
        this.ctc_calculated = ctc_calculated;
    }

    public Double getPf_employee() {
        return pf_employee;
    }

    public void setPf_employee(Double pf_employee) {
        this.pf_employee = pf_employee;
    }

    public Double getEsi_employee() {
        return esi_employee;
    }

    public void setEsi_employee(Double esi_employee) {
        this.esi_employee = esi_employee;
    }

    public Double getProf_tax() {
        return prof_tax;
    }

    public void setProf_tax(Double prof_tax) {
        this.prof_tax = prof_tax;
    }

    public Double getNet_salary() {
        return net_salary;
    }

    public void setNet_salary(Double net_salary) {
        this.net_salary = net_salary;
    }

    public Double getPf() {
        return pf;
    }

    public void setPf(Double pf) {
        this.pf = pf;
    }

    public Double getEsi() {
        return esi;
    }

    public void setEsi(Double esi) {
        this.esi = esi;
    }

    public Double getEmployer_countri() {
        return employer_countri;
    }

    public void setEmployer_countri(Double employer_countri) {
        this.employer_countri = employer_countri;
    }

    public Double getEmployee_countri() {
        return employee_countri;
    }

    public void setEmployee_countri(Double employee_countri) {
        this.employee_countri = employee_countri;
    }

    public Double getWorking_days() {
        return working_days;
    }

    public void setWorking_days(Double working_days) {
        this.working_days = working_days;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    
}
