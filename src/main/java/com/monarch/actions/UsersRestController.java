package com.monarch.actions;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.User;
import com.monarch.service.UserService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class UsersRestController {
	
	@Autowired
	UserService us;
	
	@PostMapping("/getUsers")
	public HashMap<String,Object> getUserList(HttpServletRequest request)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			
			String generalSearch=request.getParameter("query[generalSearch]");
			Page<User> pd=us.getUsers(pageno, perpage, sort,title,generalSearch);
			List<User> ulist=pd.getContent();
	
	        ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "**,-scrumDetails,-teamScrumDetails,-leavedetail,-employeedetail,-salarydetail");
	          
	        String usersdata=SquigglyUtils.stringify(objectMapper, ulist);
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
		 catch (Exception e) {
	            System.out.println(e.getMessage());
	            return null;
	     }
	}

	
	@PutMapping("/deactivateUser")
	public void deactivateUser(@RequestParam(value="empid") String empid,@RequestParam(value="status") String status)
	{
		User u=us.getUserByEmpId(empid);
		
        if (status.trim().equals("Activate"))
        {
                 u.setUserStatus("Deactivate");
        }
        else if(status.trim().equals("Deactivate"))
        {
                u.setUserStatus("Activate");
        }
        try {
           User u1= us.save(u);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
}
