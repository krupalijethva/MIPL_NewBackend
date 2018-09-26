/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain.candidate;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import com.monarch.domain.User;

/**
 *
 * @author bhumikap
 */
@Entity
@Table(name = "employee_detail")
@PrimaryKeyJoinColumn(name = "user_id")
public class EmployeeDetail extends UserDetail implements Serializable {

    @Column(name = "desig")
    private String desig;
    @Column(name = "joining_date")
    private Date joining_date;
    @Column(name = "pan_number")
    private String pan_number;
    @Column(name = "bank_name")
    private String bank_name;
    @Column(name = "bankbranch_name")
    private String bankbranch_name;
    @Column(name = "account_number")
    private String account_number;
    @Column(name = "ifsc_code")
    private String ifsc_code;
    @Column(name = "height")
    private String height;
    @Column(name = "weight")
    private String weight;
    @Column(name = "power_glass")
    private String power_glass;
    @Column(name = "nature_illness")
    private String nature_illness;
    @Column(name = "phisical_disability")
    private String phisical_disability;
    @Column(name = "blood_group")
    private String blood_group;
    @Column(name = "identification_mark")
    private String identification_mark;
    @Column(name = "doctor_name")
    private String doctor_name;
    @Column(name = "doctor_number")
    private String doctor_number;
    @Column(name = "ssc_marksheet")
    private String ssc_marksheet;
    @Column(name = "hsc_marksheet")
    private String hsc_marksheet;
    @Column(name = "leaving_certi")
    private String leaving_certi;
    @Column(name = "lastsem_marksheet")
    private String lastsem_marksheet;
    @Column(name = "deg_cirty")
    private String deg_cirty;
    @Column(name = "proof_1")
    private String proof_1;
    @Column(name = "proof_2")
    private String proof_2;
    @Column(name = "emp_id")
    private Integer emp_id;
    @Column(name = "proof_3")
    private String proof_3;
    @Column(name = "approved")
    private Boolean approved;
    @Column(name = "document_file")
    private String document_file;
    @Column(name = "alternate_contact")
    private String alternate_contact;
    @Column(name = "newdocument_file")
    private String newdocument_file;
    @Column(name = "varification")
    private Boolean varification;
    
    @OneToOne
    @JoinColumn(name = "idUser1")

    private User user1;

    
    @Override
	public void setNew_resume(String new_resume) {
		super.setNew_resume(new_resume);
	}
	
	@Override
	public void setUpload_resume(String upload_resume) {
		super.setUpload_resume(upload_resume);
	}

   
    
    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public String getDesig() {
        return desig;
    }

    public void setDesig(String desig) {
        this.desig = desig;
    }

    public Date getJoining_date() {
        return joining_date;
    }

    public void setJoining_date(Date joining_date) {
        this.joining_date = joining_date;
    }

    public String getPan_number() {
        return pan_number;
    }

    public void setPan_number(String pan_number) {
        this.pan_number = pan_number;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBankbranch_name() {
        return bankbranch_name;
    }

    public void setBankbranch_name(String bankbranch_name) {
        this.bankbranch_name = bankbranch_name;
    }

    public String getAccount_number() {
        return account_number;
    }

    public void setAccount_number(String account_number) {
        this.account_number = account_number;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPower_glass() {
        return power_glass;
    }

    public void setPower_glass(String power_glass) {
        this.power_glass = power_glass;
    }

    public String getNature_illness() {
        return nature_illness;
    }

    public void setNature_illness(String nature_illness) {
        this.nature_illness = nature_illness;
    }

    public String getPhisical_disability() {
        return phisical_disability;
    }

    public void setPhisical_disability(String phisical_disability) {
        this.phisical_disability = phisical_disability;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public String getIdentification_mark() {
        return identification_mark;
    }

    public void setIdentification_mark(String identification_mark) {
        this.identification_mark = identification_mark;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public void setDoctor_name(String doctor_name) {
        this.doctor_name = doctor_name;
    }

    public String getDoctor_number() {
        return doctor_number;
    }

    public void setDoctor_number(String doctor_number) {
        this.doctor_number = doctor_number;
    }

    public String getSsc_marksheet() {
        return ssc_marksheet;
    }

    public void setSsc_marksheet(String ssc_marksheet) {
        this.ssc_marksheet = ssc_marksheet;
    }

    public String getHsc_marksheet() {
        return hsc_marksheet;
    }

    public void setHsc_marksheet(String hsc_marksheet) {
        this.hsc_marksheet = hsc_marksheet;
    }

    public String getLeaving_certi() {
        return leaving_certi;
    }

    public void setLeaving_certi(String leaving_certi) {
        this.leaving_certi = leaving_certi;
    }

    public String getLastsem_marksheet() {
        return lastsem_marksheet;
    }

    public void setLastsem_marksheet(String lastsem_marksheet) {
        this.lastsem_marksheet = lastsem_marksheet;
    }

    public String getDeg_cirty() {
        return deg_cirty;
    }

    public void setDeg_cirty(String deg_cirty) {
        this.deg_cirty = deg_cirty;
    }

    public String getProof_1() {
        return proof_1;
    }

    public void setProof_1(String proof_1) {
        this.proof_1 = proof_1;
    }

    public String getProof_2() {
        return proof_2;
    }

    public void setProof_2(String proof_2) {
        this.proof_2 = proof_2;
    }

    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
    }

    public String getProof_3() {
        return proof_3;
    }

    public void setProof_3(String proof_3) {
        this.proof_3 = proof_3;
    }

    public String getDocument_file() {
        return document_file;
    }

    public void setDocument_file(String document_file) {
        this.document_file = document_file;
    }

    public String getAlternate_contact() {
        return alternate_contact;
    }

    public void setAlternate_contact(String alternate_contact) {
        this.alternate_contact = alternate_contact;
    }

    public String getNewdocument_file() {
        return newdocument_file;
    }

    public void setNewdocument_file(String newdocument_file) {
        this.newdocument_file = newdocument_file;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public boolean isVarification() {
        return varification;
    }

    public void setVarification(boolean varification) {
        this.varification = varification;
    }

//    public Set<QualificationDetail> getQualificationdetail() {
//        return qualificationdetail;
//    }
//
//    public void setQualificationdetail(Set<QualificationDetail> qualificationdetail) {
//        this.qualificationdetail = qualificationdetail;
//    }
//
//    public Set<TrainingDetail> getTrainingdetail() {
//        return trainingdetail;
//    }
//
//    public void setTrainingdetail(Set<TrainingDetail> trainingdetail) {
//        this.trainingdetail = trainingdetail;
//    }
//
//    public Set<LanguageDetail> getLanguagedetail() {
//        return languagedetail;
//    }
//
//    public void setLanguagedetail(Set<LanguageDetail> languagedetail) {
//        this.languagedetail = languagedetail;
//    }
//
//    public Set<ExpirenceDetail> getExpirencedetail() {
//        return expirencedetail;
//    }
//
//    public void setExpirencedetail(Set<ExpirenceDetail> expirencedetail) {
//        this.expirencedetail = expirencedetail;
//    }
//
//    public Set<SoftwareDetail> getSoftwaredetail() {
//        return softwaredetail;
//    }
//
//    public void setSoftwaredetail(Set<SoftwareDetail> softwaredetail) {
//        this.softwaredetail = softwaredetail;
//    }
//
//    public Set<ActivityDetail> getActivitydetail() {
//        return activitydetail;
//    }
//
//    public void setActivitydetail(Set<ActivityDetail> activitydetail) {
//        this.activitydetail = activitydetail;
//    }
//
//    public Set<MembershipDetail> getMembershipdetail() {
//        return membershipdetail;
//    }
//
//    public void setMembershipdetail(Set<MembershipDetail> membershipdetail) {
//        this.membershipdetail = membershipdetail;
//    }
//
//   
    @Override
    public void setBranch(String branch) {
        super.setBranch(branch); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setFirst_name(String first_name) {
        super.setFirst_name(first_name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMiddle_name(String middle_name) {
        super.setMiddle_name(middle_name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLast_name(String last_name) {
        super.setLast_name(last_name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setGender(String gender) {
        super.setGender(gender); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMarital_status(String marital_status) {
        super.setMarital_status(marital_status); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBirth_date(Date birth_date) {
        super.setBirth_date(birth_date); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setContact_number(String contact_number) {
        super.setContact_number(contact_number); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPresent_address(String present_address) {
        super.setPresent_address(present_address); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPresent_city(String present_city) {
        super.setPresent_city(present_city); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPresent_code(Integer present_code) {
        super.setPresent_code(present_code); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPresent_state(String present_state) {
        super.setPresent_state(present_state); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPresent_country(String present_country) {
        super.setPresent_country(present_country); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPermanent_address(String permanent_address) {
        super.setPermanent_address(permanent_address); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPermanent_city(String permanent_city) {
        super.setPermanent_city(permanent_city); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPermanentt_code(Integer permanentt_code) {
        super.setPermanentt_code(permanentt_code); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPermanent_state(String permanent_state) {
        super.setPermanent_state(permanent_state); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPermanent_country(String permanent_country) {
        super.setPermanent_country(permanent_country); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReference1(String reference1) {
        super.setReference1(reference1); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setReference2(String reference2) {
        super.setReference2(reference2); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPassport_number(String passport_number) {
        super.setPassport_number(passport_number); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAdhar_number(String adhar_number) {
        super.setAdhar_number(adhar_number); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setElection_number(String election_number) {
        super.setElection_number(election_number); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDriving_number(String driving_number) {
        super.setDriving_number(driving_number); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSalary_pakage(Integer salary_pakage) {
        super.setSalary_pakage(salary_pakage); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddress_same(String address_same) {
        super.setAddress_same(address_same); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOther_information(String other_information) {
        super.setOther_information(other_information); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMailing_address(String mailing_address) {
        super.setMailing_address(mailing_address); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMajor_illness(String major_illness) {
        super.setMajor_illness(major_illness); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPhoto(String photo) {
        super.setPhoto(photo); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setNewphoto(String newphoto) {
        super.setNewphoto(newphoto); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setQualificationdetail(Set<QualificationDetail> qualificationdetail) {
        super.setQualificationdetail(qualificationdetail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTrainingdetail(Set<TrainingDetail> trainingdetail) {
        super.setTrainingdetail(trainingdetail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLanguagedetail(Set<LanguageDetail> languagedetail) {
        super.setLanguagedetail(languagedetail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setExpirencedetail(Set<ExpirenceDetail> expirencedetail) {
        super.setExpirencedetail(expirencedetail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setSoftwaredetail(Set<SoftwareDetail> softwaredetail) {
        super.setSoftwaredetail(softwaredetail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setActivitydetail(Set<ActivityDetail> activitydetail) {
        super.setActivitydetail(activitydetail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setMembershipdetail(Set<MembershipDetail> membershipdetail) {
        super.setMembershipdetail(membershipdetail); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setArea_intrested(String area_intrested) {
        super.setArea_intrested(area_intrested); //To change body of generated methods, choose Tools | Templates.
    }

}
