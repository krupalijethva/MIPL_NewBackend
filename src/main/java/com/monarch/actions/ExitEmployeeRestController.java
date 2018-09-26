package com.monarch.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.ExitEmployeeDetail;
import com.monarch.domain.User;
import com.monarch.service.ExitEmployeeDetailService;
import com.monarch.service.UserService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ExitEmployeeRestController 
{

	@Autowired
	UserService us;
	
	@Autowired
	ExitEmployeeDetailService ees;
	
	
	@RequestMapping(value="/getActiveUserList",method = RequestMethod.GET)
	public  HashMap<String, Object> getUserDetails()
	{
		  	HashMap<String, Object> hm=new HashMap<String, Object>();
	        try 
	        {
	            List<User> list = us.getActiveUserList("Activate");
	            HashSet<User> usersList = new HashSet<User>(list);
	            ArrayList<User> usersListWithoutDuplicates = new ArrayList<User>(usersList);
	            
	            Collections.sort(usersListWithoutDuplicates, new Comparator<User>() 
	            {
	                public int compare(User v1, User v2) 
	                {
	                    return v1.getFullname().compareTo(v2.getFullname());
	                }
	            }); 
	            
	            ObjectMapper objectMapper1 = Squiggly.init(new ObjectMapper(), "username");
	            String usersdata=SquigglyUtils.stringify(objectMapper1, usersListWithoutDuplicates);
	            hm.put("userList", usersdata);

	            return hm; 
	        } 
	        catch (Exception e)
	        {
            		e.printStackTrace();
            		return null;
	        }	
	}
	
	@PostMapping("/ExitEmployeeDetails")
	public void exitEmployeeDetailsInsert(@RequestBody ExitEmployeeDetail eed)
	{
		try
		{
			if(eed != null)
			{
				ees.save(eed);
			}
			
		}
		catch (Exception e)
        {
        	e.printStackTrace();
        }		
	}
	
	@PostMapping("/getExitEmployeeDetails")
	public HashMap<String,Object> getUsers(HttpServletRequest request)
	{
		try
		{
			int pageno=Integer.parseInt(request.getParameter("pagination[page]"));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			System.out.println(request.getParameter("query[generalSearch]"));
			String generalSearch=request.getParameter("query[generalSearch]");
			
			Page<ExitEmployeeDetail> pd=ees.getExitEmployeeRecord(pageno, perpage, sort,title,generalSearch);
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("field", "username");
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",pd.getContent());

		   return hm;
		}
		catch (Exception e)
        {
        	e.printStackTrace();
        	return null;
        }		
		
	}

	
	
}
