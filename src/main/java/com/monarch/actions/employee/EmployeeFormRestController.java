package com.monarch.actions.employee;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.candidate.ActivityDetail;
import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.domain.candidate.ExpirenceDetail;
import com.monarch.domain.candidate.LanguageDetail;
import com.monarch.domain.candidate.MembershipDetail;
import com.monarch.domain.candidate.QualificationDetail;
import com.monarch.domain.candidate.SoftwareDetail;
import com.monarch.domain.candidate.TrainingDetail;
import com.monarch.service.FileStorageService;
import com.monarch.service.candidate.ActivityService;
import com.monarch.service.candidate.EmployeeService;
import com.monarch.service.candidate.ExperienceService;
import com.monarch.service.candidate.LanguageService;
import com.monarch.service.candidate.MembershipService;
import com.monarch.service.candidate.QualificationService;
import com.monarch.service.candidate.SoftwareService;
import com.monarch.service.candidate.TrainingService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class EmployeeFormRestController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ActivityService activityServive;
	
	@Autowired
	ExperienceService experienceService;
	
	@Autowired
	LanguageService langService;
	
	@Autowired
	MembershipService memberService;
	
	@Autowired
	QualificationService qualiService;
	
	@Autowired
	SoftwareService softwareService;
	
	@Autowired 
	TrainingService trainingService;
	
	@Autowired
	FileStorageService fss;
	
	
	@PostMapping("/EmployeeForm")
	public void updateEmployeeDetail(@RequestParam(value="user_id") long user_id,@RequestParam(value="changeResumename") String changeResumename,@RequestParam(value="changePhotoname") String changePhotoname,@RequestBody EmployeeDetail emp) throws IOException
	{
		try 
		{
			EmployeeDetail ed=employeeService.readInfo(user_id);
			
			if(!changePhotoname.isEmpty())
			{
				if(!ed.getNewphoto().isEmpty())
				{
					String extension=FilenameUtils.getExtension(ed.getNewphoto());
					String fname=FilenameUtils.getBaseName(ed.getNewphoto());
					String tempFilename=fname+"_temp"+"."+extension;
					
					boolean rename=fss.renameFile(ed.getNewphoto(),tempFilename);
					if(rename == true)
					{
						String extension2=FilenameUtils.getExtension(ed.getNewphoto());
						String newFilename2=ed.getUser_id()+"_photo"+"."+extension2;
						String Name=fss.editPermanentFile(changePhotoname,newFilename2,tempFilename);
						if(!Name.isEmpty())
						{
							
						}
						else
						{
							ed.setPhoto(changePhotoname);
							ed.setNew_resume(newFilename2);
						}
					}
				}
				else
				{
					String extension=FilenameUtils.getExtension(changePhotoname);
					String newFilename=ed.getUser_id()+"_photo"+"."+extension;
					String Name=fss.storePermanentFile(changePhotoname,newFilename);
					if(!Name.isEmpty())
					{
						ed.setPhoto(changePhotoname);
						ed.setNewphoto(newFilename);
					}
				}
			}
			if(!changeResumename.isEmpty())
			{
				if(!ed.getNew_resume().isEmpty())
				{
					String extension=FilenameUtils.getExtension(ed.getNew_resume());
					String fname=FilenameUtils.getBaseName(ed.getNew_resume());
					String tempFilename=fname+"_temp"+"."+extension;
					boolean rename=fss.renameFile(ed.getNew_resume(),tempFilename);
					if(rename == true)
					{
						String extension1=FilenameUtils.getExtension(changeResumename);
						String newFilename1=ed.getUser_id()+"_resume"+"."+extension1;
						String Name=fss.editPermanentFile(changeResumename,newFilename1,tempFilename);
						if(!Name.isEmpty())
						{
							ed.setUpload_resume(changeResumename);
							ed.setNew_resume(newFilename1);
						}
					}	
				}
				else {
					String extension=FilenameUtils.getExtension(changeResumename);
					String newFilename=ed.getUser_id()+"_resume"+"."+extension;
					String Name=fss.storePermanentFile(changeResumename,newFilename);
					
					if(!Name.isEmpty())
					{
						ed.setUpload_resume(changeResumename);
						ed.setNew_resume(newFilename);
					}
					
				}
				
			}
			
			ed.setDesig(emp.getDesig());
			ed.setBranch(emp.getBranch());
			ed.setFirst_name(emp.getFirst_name());
			ed.setMiddle_name(emp.getMiddle_name());
			ed.setLast_name(emp.getLast_name());
			ed.setEmail(emp.getEmail());
			ed.setGender(emp.getGender());
			ed.setMarital_status(emp.getMarital_status());
			ed.setBirth_date(emp.getBirth_date());
			ed.setJoining_date(emp.getJoining_date());
			ed.setContact_number(emp.getContact_number());
			ed.setPresent_address(emp.getPresent_address());
			ed.setPresent_city(emp.getPresent_city());
			ed.setPresent_code(emp.getPresent_code());
			ed.setPresent_state(emp.getPresent_state());
			ed.setAddress_same(emp.getAddress_same());
			ed.setPermanent_address(emp.getPermanent_address());
			ed.setPermanent_city(emp.getPermanent_city());
			ed.setPermanentt_code(emp.getPermanentt_code());
			ed.setPermanent_state(emp.getPermanent_state());
			ed.setPermanent_country(emp.getPermanent_country());
			ed.setMailing_address(emp.getMailing_address());
			ed.setPassport_number(emp.getPassport_number());
			ed.setAdhar_number(emp.getAdhar_number());
			ed.setPan_number(emp.getPan_number());
			ed.setElection_number(emp.getElection_number());
			ed.setDriving_number(emp.getDriving_number());
			ed.setBank_name(emp.getBank_name());
			ed.setBankbranch_name(emp.getBankbranch_name());
			ed.setAccount_number(emp.getAccount_number());
			ed.setIfsc_code(emp.getIfsc_code());
			ed.setHeight(emp.getHeight());
			ed.setWeight(emp.getWeight());
			ed.setPower_glass(emp.getPower_glass());
			ed.setNature_illness(emp.getNature_illness());
			ed.setPhisical_disability(emp.getPhisical_disability());
			ed.setBlood_group(emp.getBlood_group());
			ed.setDoctor_name(emp.getDoctor_name());
			ed.setDoctor_number(emp.getDoctor_number());
			ed.setOther_information(emp.getOther_information());
			ed.setReference1(emp.getReference1());
			ed.setReference2(emp.getReference2());
			ed.setSalary_pakage(emp.getSalary_pakage());
			ed.setAlternate_contact(emp.getAlternate_contact());
			ed.setSsc_marksheet(emp.getSsc_marksheet());
			ed.setHsc_marksheet(emp.getHsc_marksheet());
			ed.setLeaving_certi(emp.getLeaving_certi());
			ed.setLastsem_marksheet(emp.getLastsem_marksheet());
			ed.setDeg_cirty(emp.getDeg_cirty());
			ed.setProof_1(emp.getProof_1());
			ed.setProof_2(emp.getProof_2());
			ed.setProof_3(emp.getProof_3());
			ed.setVarification(true);
			ed.setApproved(false);
			
			EmployeeDetail empd=employeeService.save(ed);
			
			for(QualificationDetail qd:emp.getQualificationdetail())
			{
				qd.setUserdetail(ed);
				qualiService.save(qd);
			}
			for(LanguageDetail ld:emp.getLanguagedetail())
			{
				ld.setUserdetail(ed);
				langService.save(ld);
			}
			
			for(ExpirenceDetail exp:emp.getExpirencedetail())
			{
				exp.setUserdetail(ed);
				experienceService.save(exp);
				
			}
			for( SoftwareDetail sd:emp.getSoftwaredetail())
			{
				sd.setUserdetail(ed);
				softwareService.save(sd);
				
			}
			for(ActivityDetail ad:emp.getActivitydetail())
			{
				ad.setUserdetail(ed);
				activityServive.save(ad);
				
			}
			for(MembershipDetail md: emp.getMembershipdetail())
			{
				md.setUserdetail(ed);
				memberService.save(md);
				
			}
			for(TrainingDetail td:emp.getTrainingdetail())
			{
				td.setUserdetail(ed);
				trainingService.save(td);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
