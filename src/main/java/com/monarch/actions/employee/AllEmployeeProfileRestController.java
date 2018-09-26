package com.monarch.actions.employee;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.service.candidate.EmployeeService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class AllEmployeeProfileRestController {
	
	
	@Autowired
	EmployeeService es;
	
	@RequestMapping("/AllEmployeeprofile")
	public HashMap<String,Object> AllEmployeeprofile(HttpServletRequest request)
	{
		try 
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String generalSearch=request.getParameter("query[generalSearch]");
	
			Page<EmployeeDetail> pd=es.getEmployeeRecords(pageno, perpage, sort, title, generalSearch);
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "**,user1[id],qualificationdetail[-userdetail],trainingdetail[-userdetail],languagedetail[-userdetail],expirencedetail[-userdetail],softwaredetail[-userdetail],activitydetail[-userdetail],membershipdetail[-userdetail]");
  
	        String usersdata=SquigglyUtils.stringify(objectMapper, pd.getContent());
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
//			metamap.put("sort","desc");
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",usersdata);

		   return hm;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
		
	}

}
