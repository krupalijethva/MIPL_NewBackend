package com.monarch.actions;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;

import com.monarch.domain.PunchingDetail;
import com.monarch.domain.User;
import com.monarch.repository.PunchingRepository;
import com.monarch.service.PunchingService;
import com.monarch.service.UserService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class LoginRestController {
	
	@Autowired
	PunchingRepository punching;
	
	@Autowired
	PunchingService punchingService ;
	
	@Autowired
	UserService userService;
	
	@PostMapping(value="/login")
	public HashMap<String, Object> getUsers(@RequestParam String username,@RequestParam String password,@RequestParam String action,HttpServletRequest request) throws UnknownHostException
	{
		boolean validuser=false;
		boolean isAdmin=false;
		String result =" ";
		String validUser1="";
		String status="";
		PunchingDetail pd=null;
		HashMap<String, Object> hm=new HashMap<String,Object>();
	
		try
		{
		        if (action.equals("validateLogin")) 
		        {        	
		        	validuser = userService.isValidUser(username, password);
		            validUser1=String.valueOf(validuser);
		            if (validuser)
		            {
		                    if (!(username.toLowerCase().equals("admin"))) 
		                    {
		                        String ipAddress = request.getRemoteAddr();
		                       
		               		    InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());
		                        String remoteHost = inetAddress.getHostName();
		                        
		                        result = punchingService.insertLoginTime(username, ipAddress, remoteHost);
		                        
		                        pd = punchingService.getCurrentDetails(username);
		             
		                        if (pd != null) 
		                        {
		                           status=status.valueOf(pd.getPunchStatus());
		                           
		                        } else  
		                        {
		                            status="NONE";   
		                        	
		                        }
		                
		                    }
		                    else if(username.toLowerCase().equals("admin"))
		                    {
		                    	isAdmin=true;
		                    }
		                   
	                        User u=userService.getUser(username);             		 
	               		 	ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "username,fullname");
	            
	                        String hi=SquigglyUtils.stringify(objectMapper, u);
	                        hm.put("validuser", validUser1);
	                        hm.put("status", status);
	                        hm.put("userdetails",hi); 
	                        hm.put("isAdmin",isAdmin); 
		            }   
		            else
		            {
		            	  hm.put("validuser", validUser1);
		            }
		        }
                return hm;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	
	
	
}	
	
	
 
