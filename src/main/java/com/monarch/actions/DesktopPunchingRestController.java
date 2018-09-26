package com.monarch.actions;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.PunchingDetail;
import com.monarch.domain.User;
import com.monarch.service.PunchingService;
import com.monarch.utils.DateUtil;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class DesktopPunchingRestController {

	@Autowired
	PunchingService punchingService ;
	
	@PostMapping(value="/desktopPunching")
	public HashMap<String, Object> getUsers(@RequestParam String username,HttpServletRequest request) throws UnknownHostException
	{
		boolean punching=false;
		String result =" ";
		HashMap<String, Object> hm=new HashMap<String,Object>();
	
		try
		{
		        
		                        String ipAddress = request.getRemoteAddr();
		                       
		               		    InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());
		                        String remoteHost = inetAddress.getHostName();
		                        result = punchingService.insertLoginTime(username, ipAddress, remoteHost); 
		                        
		                        if(result.equals(""))
		                        {
		                        	punching=true;
		                        	
		                        			
		                        	hm.put("punching", punching);
		                        	hm.put("result", result);
		                        }
		                        else if(!result.isEmpty())
		                        {
		                        	punching=true;
		                        	
                        			
		                        	hm.put("punching", punching);
		                        	hm.put("result", result);
		                        }
		                        else
		                        {
		                        	punching=false;
		                        	hm.put("punching", punching);
		                        }
		                        
		                        return hm;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	
	
	@PostMapping("/desktopPunchingAction")
	public String punchingActions(@RequestParam String username,@RequestParam String action)
	{
		String result="";
		String status="";
		String breaktime="";
		
		try
		{
			if(action.equals("breakStart"))
			{
				 result= punchingService.insertBreakStartTime(username);
				 PunchingDetail pd = punchingService.getCurrentDetails(username);
	             if (pd != null) {
	             	
	                breaktime=pd.getBreakTime();
	                
	             } 
				 
				 return breaktime;
			}
			else if(action.equals("breakStop"))
			{
				 result= punchingService.insertBreakStopTime(username);
				 PunchingDetail pd = punchingService.getCurrentDetails(username);
	             
	             if (pd != null) {
	             	
	            	 breaktime=pd.getBreakTime();
	                
	             }
	             return breaktime;
			}
			else if(action.equals("logout"))
			{
				  result = punchingService.insertLogoutTime(username);
				  
				  System.out.println(result);
				  
				  
				return result;
			}
			else 
			{
				return result;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping("/getPunchingStatus")
	public HashMap<String, Object> getPunchingStatus(@RequestParam String username) throws UnknownHostException
	{
		boolean punching=false;
		HashMap<String, Object> hm=new HashMap<String,Object>();
		PunchingDetail pd=null;
		Time totalhours=null;
		
		try
		{
		        pd=punchingService.getCurrentDetails(username);
		        
		        if(pd != null)
		        {
		        	
		        	Time logintime=null;
		        	Time logouttime=null;
	
		        	if(pd.getLoginTime() != null)
		        	{
		        
		        		logintime=DateUtil.dateToTime(pd.getLoginTime());
		        	}
		        	else
		        	{
		        		logintime=null;
		        	}
		        	if(pd.getLogoutTime() != null)
		        	{
		        
		        		logouttime=DateUtil.dateToTime(pd.getLogoutTime());
		        		totalhours=pd.getTotalHours();
		        		
		        		
		        		
		        		
		        		
		        		
		        		
		        	}
		        	else
		        	{
		        		logouttime=null;
		        	}
		        
		        	hm.put("punching", true);
		        	
		        	
		        	hm.put("PunchingTime", logintime);
		        	
		        	hm.put("BreakTime", pd.getBreakTime());
		        	hm.put("PunchoutTime", logouttime);
		        	hm.put("totalhours",totalhours);
		        	
		        }
		        else
		        {
		        	hm.put("punching", false);
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
