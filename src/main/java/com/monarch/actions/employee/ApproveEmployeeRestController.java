package com.monarch.actions.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.User;
import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.service.UserService;
import com.monarch.service.candidate.EmployeeService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ApproveEmployeeRestController 
{

	@Autowired
	EmployeeService es;
	
	@Autowired
	UserService us;
	
	@GetMapping("/approvetoEmployee")
	public void approveEmployee(@RequestParam(value="userid") long userid,@RequestParam(value="userid1") long userid1)
	{
		try
		{
			EmployeeDetail employee = new EmployeeDetail();
	        
	        List<User> userdata = us.readAllUsers();
	        for(User useremp:userdata)
	        {
	        	  Long id = useremp.getId();
	        	  if (id.equals(userid1))
	        	  {
	                  employee = es.readInfo(userid);
	                  employee.setApproved(false);
	                  //employee.setVarification(true);
	                  employee.setUser1(useremp);
	                  es.save(employee);
	              }	  
	        }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
    }
	
}
