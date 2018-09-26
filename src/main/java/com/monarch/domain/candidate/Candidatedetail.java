package com.monarch.domain.candidate;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity 
@Table(name = "interview_detail")
@PrimaryKeyJoinColumn(name = "user_id")
public class Candidatedetail extends UserDetail {
	 @Column(name = "applied_previosly")
	    private String applied_previosly;
	   
	   
	    @Column(name = "wellness_work")
	    private String wellness_work;
	  
	  
	    public String getApplied_previosly() {
			return applied_previosly;
		}


		public void setApplied_previosly(String applied_previosly) {
			this.applied_previosly = applied_previosly;
		}

		public String getWellness_work() {
			return wellness_work;
		}


		public void setWellness_work(String wellness_work) {
			this.wellness_work = wellness_work;
		}


	
		@Override
		public void setNew_resume(String new_resume) {
			super.setNew_resume(new_resume);
		}
		
		@Override
		public void setUpload_resume(String upload_resume) {
			super.setUpload_resume(upload_resume);
		}


		@Override
	    public void setArea_intrested(String area_intrested) {
	        super.setArea_intrested(area_intrested); //To change body of generated methods, choose Tools | Templates.
	    }
	    

//	    public Set<QualificationDetail> getQualificationdetail() {
//	        return qualificationdetail;
//	    }
	//
//	    public void setQualificationdetail(Set<QualificationDetail> qualificationdetail) {
//	        this.qualificationdetail = qualificationdetail;
//	    }
	//
//	    public Set<TrainingDetail> getTrainingdetail() {
//	        return trainingdetail;
//	    }
	//
//	    public void setTrainingdetail(Set<TrainingDetail> trainingdetail) {
//	        this.trainingdetail = trainingdetail;
//	    }
	//
//	    public Set<LanguageDetail> getLanguagedetail() {
//	        return languagedetail;
//	    }
	//
//	    public void setLanguagedetail(Set<LanguageDetail> languagedetail) {
//	        this.languagedetail = languagedetail;
//	    }
	//
//	    public Set<ExpirenceDetail> getExpirencedetail() {
//	        return expirencedetail;
//	    }
	//
//	    public void setExpirencedetail(Set<ExpirenceDetail> expirencedetail) {
//	        this.expirencedetail = expirencedetail;
//	    }
	//
//	    public Set<SoftwareDetail> getSoftwaredetail() {
//	        return softwaredetail;
//	    }
	//
//	    public void setSoftwaredetail(Set<SoftwareDetail> softwaredetail) {
//	        this.softwaredetail = softwaredetail;
//	    }
	//
//	    public Set<ActivityDetail> getActivitydetail() {
//	        return activitydetail;
//	    }
	//
//	    public void setActivitydetail(Set<ActivityDetail> activitydetail) {
//	        this.activitydetail = activitydetail;
//	    }
	//
//	    public Set<MembershipDetail> getMembershipdetail() {
//	        return membershipdetail;
//	    }
	//
//	    public void setMembershipdetail(Set<MembershipDetail> membershipdetail) {
//	        this.membershipdetail = membershipdetail;
//	    }
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
	    public void setRemark_admin(String remark_admin) {
	        super.setRemark_admin(remark_admin); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public void setMark_admin(String mark_admin) {
	        super.setMark_admin(mark_admin); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public void setUploadad_ans(String uploadad_ans) {
	        super.setUploadad_ans(uploadad_ans); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public void setUpload_newans(String upload_newans) {
	        super.setUpload_newans(upload_newans); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public void setTotal_mark(String total_mark) {
	        super.setTotal_mark(total_mark); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public void setInterview_date(Date interview_date) {
	        super.setInterview_date(interview_date); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public void setSelected_not(String selected_not) {
	        super.setSelected_not(selected_not); //To change body of generated methods, choose Tools | Templates.
	    }

	    @Override
	    public void setPersentageans(String persentageans) {
	        super.setPersentageans(persentageans); //To change body of generated methods, choose Tools | Templates.
	    }
	    
	   @Override
	   public void setAppliedtype(String appliedtype){
	    super.setAppliedtype(appliedtype); 
	   } 
	   
	
	
	
	
	
	
}
