package com.monarch.actions.candidate;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.User;
import com.monarch.domain.candidate.ActivityDetail;
import com.monarch.domain.candidate.Candidatedetail;
import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.domain.candidate.ExpirenceDetail;
import com.monarch.domain.candidate.LanguageDetail;
import com.monarch.domain.candidate.MembershipDetail;
import com.monarch.domain.candidate.QualificationDetail;
import com.monarch.domain.candidate.SoftwareDetail;
import com.monarch.domain.candidate.TrainingDetail;
import com.monarch.service.UserService;
import com.monarch.service.candidate.ActivityService;
import com.monarch.service.candidate.EmployeeService;
import com.monarch.service.candidate.ExperienceService;
import com.monarch.service.candidate.InterviewService;
import com.monarch.service.candidate.LanguageService;
import com.monarch.service.candidate.MembershipService;
import com.monarch.service.candidate.QualificationService;
import com.monarch.service.candidate.SoftwareService;
import com.monarch.service.candidate.TrainingService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class MoveToEmployeeRestController {
	
	@Autowired
	EmployeeService es;
	@Autowired
	UserService us;
	@Autowired
	InterviewService is;
	@Autowired
	QualificationService qs;
	@Autowired
	TrainingService ts;
	@Autowired
	LanguageService ls;
	@Autowired
	ExperienceService experienceS;
	@Autowired
	SoftwareService ss;
	@Autowired
	ActivityService as;
	@Autowired
	MembershipService ms;
	
	@GetMapping("/moveToEmployee")
	public void moveToEmp(@RequestParam(value="cid") long cid,@RequestParam(value="username") String username)
	{
		try
		{
			Candidatedetail cd = new Candidatedetail();
	        EmployeeDetail employee = new EmployeeDetail();
	        
	        Candidatedetail candidate=is.getbyID(cid);
	        
	        List<User> userlist = us.getActiveUserList("Activate");
	        HashSet<User> user = new HashSet<User>(userlist);
	        
	        for(User useremp:user)
	        {
	        	 String empname = useremp.getUsername();
	        	 if (empname.equals(username))
	        	 {
	        		 long id1 = useremp.getId();
	          
	                 String userid = useremp.getEmployeeid();
	                 Integer uid = Integer.parseInt(userid);
	                 employee.setEmp_id(uid);
	                 employee.setUser1(useremp);
	                 employee.setBranch(candidate.getBranch());
	                 employee.setFirst_name(candidate.getFirst_name());
	                 employee.setMiddle_name(candidate.getMiddle_name());
	                 employee.setLast_name(candidate.getLast_name());
	                 employee.setEmail(candidate.getEmail());
	                 employee.setGender(candidate.getGender());
	                 employee.setMarital_status(candidate.getMarital_status());
	                 employee.setBirth_date(candidate.getBirth_date());
	                 employee.setContact_number(candidate.getContact_number());
	                 employee.setPresent_address(candidate.getPresent_address());
	                 employee.setPresent_city(candidate.getPresent_city());
	                 employee.setPresent_code(candidate.getPresent_code());
	                 employee.setPresent_state(candidate.getPresent_state());
	                 employee.setPresent_country(candidate.getPresent_country());
	                 employee.setPermanent_address(candidate.getPermanent_address());
	                 employee.setPermanent_city(candidate.getPermanent_city());
	                 employee.setPermanentt_code(candidate.getPresent_code());
	                 employee.setPermanent_state(candidate.getPermanent_state());
	                 employee.setPermanent_country(candidate.getPermanent_country());
	                 employee.setReference1(candidate.getReference1());
	                 employee.setReference2(candidate.getReference2());
	                 employee.setPassport_number(candidate.getPassport_number());
	                 employee.setAdhar_number(candidate.getAdhar_number());
	                 employee.setElection_number(candidate.getElection_number());
	                 employee.setDriving_number(candidate.getDriving_number());
	                 employee.setArea_intrested(candidate.getArea_intrested());
	                 employee.setSalary_pakage( candidate.getSalary_pakage());
	                 employee.setOther_information(candidate.getOther_information());
	                 employee.setMailing_address(candidate.getMailing_address());
	                 employee.setMajor_illness(candidate.getMajor_illness());
	                 employee.setPhoto(candidate.getPhoto());
	                 employee.setNewphoto(candidate.getNewphoto());
	                 employee.setRemark_admin(candidate.getRemark_admin());
	                 employee.setInterview_date(candidate.getInterview_date());
	                 employee.setMark_admin(candidate.getMark_admin());
	                 employee.setTotal_mark(candidate.getTotal_mark());
	                 employee.setPersentageans(candidate.getPersentageans());
	                 employee.setUploadad_ans(candidate.getUploadad_ans());
	                 employee.setSelected_not(candidate.getSelected_not());
	                 employee.setUpload_newans(candidate.getUpload_newans());
	                 employee.setUser1(useremp);
	                 employee.setApproved(true);
	                 employee.setUpload_resume(candidate.getUpload_resume());
	                 employee.setNew_resume(candidate.getNew_resume());
	                 employee.setUser1(useremp);
	           
	                 EmployeeDetail empDetail=es.save(employee);
	                 //Qualification Table
	                 for (QualificationDetail qualification : candidate.getQualificationdetail())
	                 {
	                     qualification.setQualification(qualification.getQualification());
	                     qualification.setSpeicalization(qualification.getSpeicalization());
	                     qualification.setSchool_collage(qualification.getSchool_collage());
	                     qualification.setBoard_uni(qualification.getBoard_uni());
	                     qualification.setPersentage(qualification.getPersentage());
	                     qualification.setStartdate(qualification.getStartdate());
	                     qualification.setEnddate(qualification.getEnddate());
	                     qualification.setUserdetail(employee);
	                     
	                     qs.save(qualification);
	                 }
	                 //Training Table
	                 for (TrainingDetail training : candidate.getTrainingdetail())
	                 {
	                	 training.setUserdetail(employee);
	                     training.setCourse_name(training.getCourse_name());
	                     training.setInstitute(training.getInstitute());
	                     training.setFrom_date(training.getFrom_date());
	                     training.setTo_date(training.getTo_date());
	                     training.setRemark(training.getRemark());
	                  
	                     ts.save(training);
	                 }
	                 //Language Table
	                 for (LanguageDetail lanuage : candidate.getLanguagedetail())
	                 {
	                	 lanuage.setUserdetail(employee);
	                     lanuage.setLanuguage(lanuage.getLanuguage());
	                     lanuage.setRead_level(lanuage.getRead_level());
	                     lanuage.setSpeak_level(lanuage.getSpeak_level());
	                     lanuage.setWrite_level(lanuage.getWrite_level());
	                     
	                     ls.save(lanuage);
	                 }
	                //  Expirence Table
	                 for (ExpirenceDetail expirence : candidate.getExpirencedetail()) 
	                 {
	                     expirence.setUserdetail(employee);
	                     expirence.setCompany_name(expirence.getCompany_name());
	                     expirence.setDesignation(expirence.getDesignation());
	                     expirence.setJoining_date(expirence.getJoining_date());
	                     expirence.setReliving_date(expirence.getReliving_date());
	                     expirence.setNature_duties(expirence.getNature_duties());
	                     expirence.setUserdetail(employee);
	                     
	                     experienceS.save(expirence);
	                 }
	                 //Software Table
	                 for (SoftwareDetail software : candidate.getSoftwaredetail()) 
	                 {
	                	 software.setUserdetail(employee); 
	                     software.setSoftware_name(software.getSoftware_name());
	                     software.setProficiency(software.getProficiency());
	                     software.setUserdetail(employee);
	                     
	                     ss.save(software);
	                 }
	                 //Active Table
	                 for (ActivityDetail activity : candidate.getActivitydetail()) 
	                 {
	                     activity.setUserdetail(employee);
	                     activity.setActivity_name(activity.getActivity_name());
	                     activity.setActivity_institute(activity.getActivity_institute());
	                     activity.setUserdetail(employee);
	                     activity.setYear(activity.getYear());
	                     activity.setPrize_won(activity.getPrize_won());
	                     
	                     as.save(activity);
	                 }
	                 //MemberShip Table
	                 for (MembershipDetail member : candidate.getMembershipdetail()) 
	                 {
	                     member.setUserdetail(employee);
	                     member.setMembership_name(member.getMembership_name());
	                     member.setExpiry_date(member.getExpiry_date());
	                     
	                     ms.save(member);
	                 }
		             is.delete(cid);
	        	 }
	        }
		}
	    catch(Exception ex)
	    {
	       ex.printStackTrace();
	    }
	}
}
