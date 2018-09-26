package com.monarch.actions.candidate;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.candidate.ActivityDetail;
import com.monarch.domain.candidate.Candidatedetail;
import com.monarch.domain.candidate.ExpirenceDetail;
import com.monarch.domain.candidate.LanguageDetail;
import com.monarch.domain.candidate.MembershipDetail;
import com.monarch.domain.candidate.QualificationDetail;
import com.monarch.domain.candidate.SoftwareDetail;
import com.monarch.domain.candidate.TrainingDetail;
import com.monarch.service.FileStorageService;
import com.monarch.service.candidate.ActivityService;
import com.monarch.service.candidate.ExperienceService;
import com.monarch.service.candidate.InterviewService;
import com.monarch.service.candidate.LanguageService;
import com.monarch.service.candidate.MembershipService;
import com.monarch.service.candidate.QualificationService;
import com.monarch.service.candidate.SoftwareService;
import com.monarch.service.candidate.TrainingService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class InterviewFormRestController {
	
	
	@Autowired
	InterviewService is;

	@Autowired
	ActivityService as;
	
	@Autowired
	ExperienceService es;
	
	@Autowired
	LanguageService ls;
	
	@Autowired
	MembershipService ms;
	
	@Autowired
	QualificationService qs;
	
	@Autowired
	SoftwareService ss;
	
	@Autowired 
	TrainingService ts;
	
	@Autowired
	FileStorageService fss;
	
	@PostMapping("/InterviewForm")
	public Candidatedetail insertCandidateDetail(@RequestBody Candidatedetail cd) throws IOException
	{
		try
		{
			Candidatedetail cd1=is.save(cd);
			
			for(QualificationDetail qd:cd.getQualificationdetail())
			{
				qd.setUserdetail(cd);
				qs.save(qd);
			}
			for(LanguageDetail ld:cd.getLanguagedetail())
			{
				ld.setUserdetail(cd);
				ls.save(ld);
			}
			
			for(ExpirenceDetail ed:cd.getExpirencedetail())
			{
				ed.setUserdetail(cd);
				es.save(ed);
				
			}
			for( SoftwareDetail sd:cd.getSoftwaredetail())
			{
				sd.setUserdetail(cd);
				ss.save(sd);
				
			}
			for(ActivityDetail ad:cd.getActivitydetail())
			{
				ad.setUserdetail(cd);
				as.save(ad);
				
			}
			for(MembershipDetail md: cd.getMembershipdetail())
			{
				md.setUserdetail(cd);
				ms.save(md);
				
			}
			for(TrainingDetail td:cd.getTrainingdetail())
			{
				td.setUserdetail(cd);
				ts.save(td);
			}
		
			long id=cd1.getUser_id();
			if(!cd1.getUpload_resume().isEmpty())
			{
			
				String extension=FilenameUtils.getExtension(cd1.getUpload_resume());
				String newFilename=id+"_resume"+"."+extension;
				String Name=fss.storePermanentFile(cd1.getUpload_resume(),newFilename);
				
				if(!Name.isEmpty())
				{
					cd1.setNew_resume(Name);
				}
				
			}
			if(!cd1.getPhoto().isEmpty())
			{
				String extension=FilenameUtils.getExtension(cd1.getPhoto());
				String newFilename=id+"_photo"+"."+extension;
				String Name=fss.storePermanentFile(cd1.getPhoto(),newFilename);
				if(!Name.isEmpty())
				{
					cd1.setNewphoto(Name);
				}
			}
			
			Candidatedetail cd2=is.save(cd1);
			if(cd2 != null)
			{
				return cd2;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
}
