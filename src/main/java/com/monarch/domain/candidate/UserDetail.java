/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.domain.candidate;

import java.util.Date;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author bhumikap
 */
@Entity
@Table(name = "user_detail")
@Inheritance(strategy = InheritanceType.JOINED)
public class UserDetail {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private long user_id;
    @Column(name = "branch")
    private String branch;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "middle_name")
    private String middle_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "email")
    private String email;
    @Column(name = "gender")
    private String gender;
    @Column(name = "marital_status")
    private String marital_status;
    @Column(name = "birth_date")
    private Date birth_date;
    @Column(name = "contact_number")
    private String contact_number;
    @Column(name = "present_address")
    private String present_address;
    @Column(name = "present_city")
    private String present_city;
    @Column(name = "present_code")
    private Integer present_code;
    @Column(name = "present_state")
    private String present_state;
    @Column(name = "present_country")
    private String present_country;
    @Column(name = "permanent_address")
    private String permanent_address;
    @Column(name = "permanent_city")
    private String permanent_city;
    @Column(name = "permanentt_code")
    private Integer permanentt_code;
    @Column(name = "permanent_state")
    private String permanent_state;
    @Column(name = "permanent_country")
    private String permanent_country;
    @Column(name = "reference1")
    private String reference1;
    @Column(name = "reference2")
    private String reference2;
    @Column(name = "passport_number")
    private String passport_number;
    @Column(name = "adhar_number")
    private  String adhar_number;
    @Column(name = "election_number")
    private String election_number;
    @Column(name = "driving_number")
    private String driving_number;
    @Column(name = "salary_pakage")
    private Integer salary_pakage;
    @Column(name = "address_same")
    private String address_same;
    @Column(name = "other_information")
    private String other_information;
    @Column(name = "mailing_address")
    private String mailing_address;
    @Column(name = "major_illness")
    private String major_illness;
    @Column(name = "photo")
    private String photo;
    @Column(name = "newphoto")
    private String newphoto;
    @Column(name = "area_intrested")
    private  String area_intrested;
     @Column(name = "remark_admin")
    private String remark_admin;
    @Column(name = "mark_admin")
    private String mark_admin;
    @Column(name = "uploadad_ans")
    private String uploadad_ans;
    @Column(name = "upload_newans")
    private String upload_newans;
    @Column(name = "total_mark")
    private String total_mark;
    @Column(name = "interview_date")
    private Date interview_date;
    @Column(name = "selected_not")
    private String selected_not;
    @Column(name = "persentageans")
    private String persentageans;
    @Column(name = "appliedtype")
    private String appliedtype;
    @Column(name = "upload_resume")
    private String upload_resume;
    @Column(name = "new_resume")
    private String new_resume;
    
    
    @OneToMany(mappedBy = "userdetail")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<QualificationDetail> qualificationdetail;
    
    @OneToMany(mappedBy = "userdetail")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<TrainingDetail> trainingdetail;
    
    @OneToMany(mappedBy = "userdetail")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<LanguageDetail> languagedetail;
    
    @OneToMany(mappedBy = "userdetail")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<ExpirenceDetail> expirencedetail;
    
    @OneToMany(mappedBy = "userdetail")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<SoftwareDetail> softwaredetail;
    
    @OneToMany(mappedBy = "userdetail")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<ActivityDetail> activitydetail;
    
    @OneToMany(mappedBy = "userdetail")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Set<MembershipDetail> membershipdetail;
    
    
    
    
    public String getUpload_resume() {
		return upload_resume;
	}

	public void setUpload_resume(String upload_resume) {
		this.upload_resume = upload_resume;
	}

	public String getNew_resume() {
		return new_resume;
	}

	public void setNew_resume(String new_resume) {
		this.new_resume = new_resume;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMarital_status() {
        return marital_status;
    }

    public void setMarital_status(String marital_status) {
        this.marital_status = marital_status;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getPresent_address() {
        return present_address;
    }

    public void setPresent_address(String present_address) {
        this.present_address = present_address;
    }

    public String getPresent_city() {
        return present_city;
    }

    public void setPresent_city(String present_city) {
        this.present_city = present_city;
    }

    public Integer getPresent_code() {
        return present_code;
    }

    public void setPresent_code(Integer present_code) {
        this.present_code = present_code;
    }

    public String getPresent_state() {
        return present_state;
    }

    public void setPresent_state(String present_state) {
        this.present_state = present_state;
    }

    public String getPresent_country() {
        return present_country;
    }

    public void setPresent_country(String present_country) {
        this.present_country = present_country;
    }

    public String getPermanent_address() {
        return permanent_address;
    }

    public void setPermanent_address(String permanent_address) {
        this.permanent_address = permanent_address;
    }

    public String getPermanent_city() {
        return permanent_city;
    }

    public void setPermanent_city(String permanent_city) {
        this.permanent_city = permanent_city;
    }

    public Integer getPermanentt_code() {
        return permanentt_code;
    }

    public void setPermanentt_code(Integer permanentt_code) {
        this.permanentt_code = permanentt_code;
    }

    public String getPermanent_state() {
        return permanent_state;
    }

    public void setPermanent_state(String permanent_state) {
        this.permanent_state = permanent_state;
    }

    public String getPermanent_country() {
        return permanent_country;
    }

    public void setPermanent_country(String permanent_country) {
        this.permanent_country = permanent_country;
    }

    public String getReference1() {
        return reference1;
    }

    public void setReference1(String reference1) {
        this.reference1 = reference1;
    }

    public String getReference2() {
        return reference2;
    }

    public void setReference2(String reference2) {
        this.reference2 = reference2;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    public String getAdhar_number() {
        return adhar_number;
    }

    public void setAdhar_number(String adhar_number) {
        this.adhar_number = adhar_number;
    }

    public String getElection_number() {
        return election_number;
    }

    public void setElection_number(String election_number) {
        this.election_number = election_number;
    }

    public String getDriving_number() {
        return driving_number;
    }

    public void setDriving_number(String driving_number) {
        this.driving_number = driving_number;
    }

    public Integer getSalary_pakage() {
        return salary_pakage;
    }

    public void setSalary_pakage(Integer salary_pakage) {
        this.salary_pakage = salary_pakage;
    }

    public String getAddress_same() {
        return address_same;
    }

    public void setAddress_same(String address_same) {
        this.address_same = address_same;
    }

    public String getOther_information() {
        return other_information;
    }

    public void setOther_information(String other_information) {
        this.other_information = other_information;
    }

    public String getMailing_address() {
        return mailing_address;
    }

    public void setMailing_address(String mailing_address) {
        this.mailing_address = mailing_address;
    }

    public String getMajor_illness() {
        return major_illness;
    }

    public void setMajor_illness(String major_illness) {
        this.major_illness = major_illness;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getNewphoto() {
        return newphoto;
    }

    public void setNewphoto(String newphoto) {
        this.newphoto = newphoto;
    }

    public String getArea_intrested() {
        return area_intrested;
    }

    public void setArea_intrested(String area_intrested) {
        this.area_intrested = area_intrested;
    }

    public String getRemark_admin() {
        return remark_admin;
    }

    public void setRemark_admin(String remark_admin) {
        this.remark_admin = remark_admin;
    }

    public String getMark_admin() {
        return mark_admin;
    }

    public void setMark_admin(String mark_admin) {
        this.mark_admin = mark_admin;
    }

    public String getUploadad_ans() {
        return uploadad_ans;
    }

    public void setUploadad_ans(String uploadad_ans) {
        this.uploadad_ans = uploadad_ans;
    }

    public String getUpload_newans() {
        return upload_newans;
    }

    public void setUpload_newans(String upload_newans) {
        this.upload_newans = upload_newans;
    }

    public String getTotal_mark() {
        return total_mark;
    }

    public void setTotal_mark(String total_mark) {
        this.total_mark = total_mark;
    }

    public Date getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(Date interview_date) {
        this.interview_date = interview_date;
    }

    public String getSelected_not() {
        return selected_not;
    }

    public void setSelected_not(String selected_not) {
        this.selected_not = selected_not;
    }

    public String getPersentageans() {
        return persentageans;
    }

    public void setPersentageans(String persentageans) {
        this.persentageans = persentageans;
    }
      
    public Set<QualificationDetail> getQualificationdetail() {
        return qualificationdetail;
    }

    public void setQualificationdetail(Set<QualificationDetail> qualificationdetail) {
        this.qualificationdetail = qualificationdetail;
    }

    public Set<TrainingDetail> getTrainingdetail() {
        return trainingdetail;
    }

    public void setTrainingdetail(Set<TrainingDetail> trainingdetail) {
        this.trainingdetail = trainingdetail;
    }

    public Set<LanguageDetail> getLanguagedetail() {
        return languagedetail;
    }

    public void setLanguagedetail(Set<LanguageDetail> languagedetail) {
        this.languagedetail = languagedetail;
    }

    public Set<ExpirenceDetail> getExpirencedetail() {
        return expirencedetail;
    }

    public void setExpirencedetail(Set<ExpirenceDetail> expirencedetail) {
        this.expirencedetail = expirencedetail;
    }

    public Set<SoftwareDetail> getSoftwaredetail() {
        return softwaredetail;
    }

    public void setSoftwaredetail(Set<SoftwareDetail> softwaredetail) {
        this.softwaredetail = softwaredetail;
    }

    public Set<ActivityDetail> getActivitydetail() {
        return activitydetail;
    }

    public void setActivitydetail(Set<ActivityDetail> activitydetail) {
        this.activitydetail = activitydetail;
    }

    public Set<MembershipDetail> getMembershipdetail() {
        return membershipdetail;
    }

    public void setMembershipdetail(Set<MembershipDetail> membershipdetail) {
        this.membershipdetail = membershipdetail;
    }

    public String getAppliedtype() {
        return appliedtype;
    }

    public void setAppliedtype(String appliedtype) {
        this.appliedtype = appliedtype;
    }
    
    
}
