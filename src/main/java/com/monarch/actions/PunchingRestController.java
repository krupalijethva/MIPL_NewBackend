package com.monarch.actions;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.monarch.domain.PunchingDetail;
import com.monarch.domain.PunchingDetailEdit;
import com.monarch.service.PunchingEditService;
import com.monarch.service.PunchingService;


@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class PunchingRestController {
	
	
	@Autowired
	PunchingService punchingService;
	
	@Autowired
	PunchingEditService punchingEdit;
	
	
	@GetMapping("/getPunching/{username}")
	public List<PunchingDetail> getPunching(@PathVariable String username)
	{
		try
		{
			List<PunchingDetail> pd = punchingService.getLast30DaysDetails(username);
			return pd;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}		
	}
	
	@PostMapping("/getpunchingdetails/{username}")
	public HashMap<String,Object> getUsers(HttpServletRequest request,@PathVariable String username)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String punchstatus=request.getParameter("query[punchStatus]");
			System.out.println(request.getParameter("query[generalSearch]"));
			String generalSearch=request.getParameter("query[generalSearch]");
			
			Page<PunchingDetail> pd=punchingService.getAllPunchingRecords(pageno, perpage, sort,title,punchstatus,generalSearch,username);
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			metamap.put("field", "idPunching");
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
//			metamap.put("sort","desc");
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
	
	@GetMapping("/getPunchingDetail/{id}")
	public PunchingDetail getPunchingDetails(@PathVariable long id)
	{
		try
		{
			PunchingDetail pd = punchingService.getUserPunching(id);
			if(pd != null)
			{
				 return pd;
			}
			else
			{
				return null;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping(value="/deletepunch/{id}",method = RequestMethod.DELETE)
	public void deletePunch(@PathVariable long id)
	{
		punchingService.deleteRecord(id);
	}
	
	@PostMapping("/punching")
	public String breakStart(@RequestParam String username,@RequestParam String action)
	{
		String result="";
		String status="";
		try
		{
			if(action.equals("breakStart"))
			{
				 result= punchingService.insertBreakStartTime(username);
				 PunchingDetail pd = punchingService.getCurrentDetails(username);
	             if (pd != null) {
	             	
	                status=status.valueOf(pd.getPunchStatus());
	                
	             } else  
	             {
	                 status="NONE";   
	             	
	             }
				 
				 return status;
			}
			else if(action.equals("breakStop"))
			{
				 result= punchingService.insertBreakStopTime(username);
				 PunchingDetail pd = punchingService.getCurrentDetails(username);
	             
	             if (pd != null) {
	             	
	                status=status.valueOf(pd.getPunchStatus());
	                
	             } else  
	             {
	                 status="NONE";   
	             	
	             }
				 
				 return status;
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
	
	
	@PostMapping("/newPunching")
	public String newPunching(@RequestParam(value="username") String username,@RequestParam(value="teamlead") String teamlead,@RequestBody PunchingDetail pd,HttpServletRequest request) throws UnknownHostException
	{
		boolean isexist = punchingService.isPunching(username,pd.getLogindate());
        if (!isexist) 
        {
        	 PunchingDetail pd1 = new PunchingDetail();
             pd1.setUsername(username.toLowerCase());
             String ipAddress = request.getRemoteAddr();
             InetAddress inetAddress = InetAddress.getByName(request.getRemoteAddr());
             String remoteHost = inetAddress.getHostName();
             pd1.setIpAddress(ipAddress);
             pd1.setHostName(remoteHost);
             pd1.setLogindate(pd.getLogindate());
             pd1.setLoginTime(pd.getLoginTime());
             pd1.setLogoutTime(pd.getLogoutTime());
             pd1.setBreakTime(pd.getBreakTime());
             pd1.setComments(pd.getComments());
             pd1.calculateTotalHours();
             pd1.setPunchStatus(PunchingDetail.Status.UNDER_REVIEW);
             try {
                 punchingService.create(pd1);
             } catch (Exception e) {
                  System.out.println(e.getMessage());
             }
             try {
            	 
            	 Long id = punchingService.getLastID();
            	 System.out.println(id);
            	 
                 PunchingDetail pdnew = punchingService.getUserPunching(id);
                 PunchingDetailEdit pde = new PunchingDetailEdit(pdnew);
                 pde.setLoginTimeedit(pd.getLoginTime());
                 pde.setLogoutTimeedit(pd.getLogoutTime());
                 pde.setPunchStatus(PunchingDetail.Status.UNDER_REVIEW);
                 pde.setBreakTimeedit(pd.getBreakTime());
                 pde.setTeamlead(teamlead);
                 pde.setEmployeeComments(pd.getComments());
                 pde.calculateTotalHoursEdit();


                 punchingEdit.saveEditPunching(pde);

             } catch (Exception ex)
             {
            	 System.out.println(ex.getMessage());
             }    	
        }
//           
		return "";	
	}
	
	
	@PutMapping("/editPunching/{id}")
	public String editPunching(@PathVariable long id,@RequestBody PunchingDetailEdit pdedit)
	{
	       PunchingDetail pd = punchingService.getUserPunching(id);
	       if(pd !=null)
	       {
	           pd.setPunchStatus(PunchingDetail.Status.UNDER_REVIEW);
	           punchingService.create(pd);
	       }
          
           PunchingDetailEdit pde = new PunchingDetailEdit(pd);
           pde.setLoginTimeedit(pdedit.getLoginTime());
           pde.setLogoutTimeedit(pdedit.getLogoutTime());
           pde.setPunchStatus(PunchingDetail.Status.UNDER_REVIEW);
           pde.setBreakTimeedit(pdedit.getBreakTime().trim());
           pde.setTeamlead(pdedit.getTeamlead());
           pde.setEmployeeComments(pde.getUsername() + ":" + pdedit.getComments());
           pde.calculateTotalHoursEdit();
           punchingEdit.saveEditPunching(pde);

          return null;
	}
	
}
