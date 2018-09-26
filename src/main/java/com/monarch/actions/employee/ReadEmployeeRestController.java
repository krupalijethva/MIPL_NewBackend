package com.monarch.actions.employee;

import java.nio.file.Path;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.User;
import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.service.FileStorageService;
import com.monarch.service.UserService;
import com.monarch.service.candidate.EmployeeService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ReadEmployeeRestController {

	@Autowired
	UserService us;
	
	@Autowired
	EmployeeService es;
	
	@Autowired
	FileStorageService fss;
	 
	@GetMapping("/ReadEmployee")
	public HashMap<String,Object> readEmployee(@RequestParam(value="username") String username)
	{
		try
		{
			HashMap<String,Object> hm=new HashMap<String,Object>();
			User user = us.getUser(username);
			
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "**,user1[fullname],qualificationdetail[-userdetail],activitydetail[-userdetail],trainingdetail[-userdetail],languagedetail[-userdetail],expirencedetail[-userdetail],softwaredetail[-userdetail],membershipdetail[-userdetail]");
			EmployeeDetail employee=user.getEmployeedetail();
	        Object empData=SquigglyUtils.stringify(objectMapper, employee);

			hm.put("empData",empData);
			
			return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@GetMapping("/ReadEmployeeById")
	public HashMap<String,Object> readEmployeeById(@RequestParam(value="userid") long userid)
	{
		try
		{
			HashMap<String,Object> hm=new HashMap<String,Object>();
			
			EmployeeDetail employee=es.readInfo(userid);
			
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "**,user1[fullname],qualificationdetail[-userdetail],activitydetail[-userdetail],trainingdetail[-userdetail],languagedetail[-userdetail],expirencedetail[-userdetail],softwaredetail[-userdetail],membershipdetail[-userdetail]");

	        Object empData=SquigglyUtils.stringify(objectMapper, employee);
			hm.put("empData",empData);
			
			return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
}
