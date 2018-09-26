package com.monarch.actions.request;


import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.FolderAccessRequestDetail;
import com.monarch.domain.InternetAccessRequestDetail;
import com.monarch.domain.PendriveRequestDetail;
import com.monarch.domain.PunchingDetail;
import com.monarch.domain.StationeryItemRequestDetail;
import com.monarch.domain.User;
import com.monarch.service.FolderAccessRequestService;
import com.monarch.service.InternetAccessRequestService;
import com.monarch.service.PendriveReqestService;
import com.monarch.service.StationeryItemRequestService;
import com.monarch.service.UserService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class AccessRequestRestController {

	@Autowired
	PendriveReqestService pendriveService;
	
	@Autowired
	InternetAccessRequestService intertnetService;
	
	@Autowired
	FolderAccessRequestService folderService;
	
	@Autowired
	StationeryItemRequestService itemService;
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/pendriveRequest")
	public void requestPendrive(@RequestParam(value="username") String username,@RequestParam(value="description") String description)
	{
		System.out.println(username);
		System.out.println(description);
		
		try
		{
			User u=userService.getUserFromFullName(username);
			
			String username1=u.getUsername();
	        String username2[] = username1.split("@");
	        String username3 = username2[0];
	        Date date =new Date();
	        
			PendriveRequestDetail prd=new PendriveRequestDetail();
			prd.setUsername(username3);
			prd.setRequestdate(date);
			prd.setDescription(description);
			
			prd.setStatus(PendriveRequestDetail.Status.Pending);
			
			pendriveService.save(prd);
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	@RequestMapping("getPendriveDetails")
	public HashMap<String,Object> getPendriveDetails(HttpServletRequest request,@RequestParam(value="username",required=false) String username)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String status=request.getParameter("query[status]");
			String searchterm=request.getParameter("query[generalSearch]");
						 
			Page<PendriveRequestDetail> pd=pendriveService.getPendriveReqDetails(pageno, perpage, sort,title,username,status,searchterm);
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",pd.getContent());

		   return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("/internetAccessRequest")
	public void requestInternet(@RequestParam(value="username") String username,@RequestParam(value="description") String description,@RequestParam(value="duration") String duration)
	{
		System.out.println(username);
		System.out.println(description);
		
		try
		{
			User u=userService.getUserFromFullName(username);
			
			String username1=u.getUsername();
	        String username2[] = username1.split("@");
	        String username3 = username2[0];
	        Date date =new Date();
	        
			InternetAccessRequestDetail ird=new InternetAccessRequestDetail();
			ird.setUsername(username3);
			ird.setRequestdate(date);
			ird.setDescription(description);
			ird.setDuration(duration);
			ird.setStatus(InternetAccessRequestDetail.Status.Pending);
			
			intertnetService.save(ird);
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	@RequestMapping("getInternetReqDetails")
	public HashMap<String,Object> getInternetReqDetails(HttpServletRequest request,@RequestParam(value="username",required=false) String username)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String status=request.getParameter("query[status]");
			String searchterm=request.getParameter("query[generalSearch]");
						 
			Page<InternetAccessRequestDetail> pd=intertnetService.getInternetReqDetails(pageno, perpage, sort,title,username,status,searchterm);
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",pd.getContent());

		   return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@RequestMapping("/folderAccessRequest")
	public void requestFolder(@RequestParam(value="username") String username,@RequestParam(value="description") String description,@RequestParam(value="permission") String permission)
	{
		System.out.println(username);
		System.out.println(description);
		
		try
		{
			User u=userService.getUserFromFullName(username);
			
			String username1=u.getUsername();
	        String username2[] = username1.split("@");
	        String username3 = username2[0];
	        Date date =new Date();
	        
			FolderAccessRequestDetail frd=new FolderAccessRequestDetail();
			frd.setUsername(username3);
			frd.setRequestdate(date);
			frd.setDescription(description);
			frd.setAccesstype(permission);
			frd.setStatus(FolderAccessRequestDetail.Status.Pending);
			
			folderService.save(frd);
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	@RequestMapping("getFolderReqDetails")
	public HashMap<String,Object> getFolderReqDetails(HttpServletRequest request,@RequestParam(value="username",required=false) String username)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String status=request.getParameter("query[status]");
			String searchterm=request.getParameter("query[generalSearch]");
						 
			Page<FolderAccessRequestDetail> pd=folderService.getFolderReqDetails(pageno, perpage, sort,title,username,status,searchterm);
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",pd.getContent());

		   return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@RequestMapping("/stationeryItemRequest")
	public void requestStationeryItem(@RequestParam(value="username") String username,@RequestParam(value="description") String description,@RequestParam(value="item") String item,@RequestParam(value="quantity") int quantity)
	{
		System.out.println(username);
		System.out.println(description);
		
		try
		{
			User u=userService.getUserFromFullName(username);
			
			String username1=u.getUsername();
	        String username2[] = username1.split("@");
	        String username3 = username2[0];
	        Date date =new Date();
	        
			StationeryItemRequestDetail sird=new StationeryItemRequestDetail();
			sird.setUsername(username3);
			sird.setRequestdate(date);
			sird.setDescription(description);
			sird.setNoofquantity(quantity);
			sird.setSelectitem(item);
			
			sird.setStatus(StationeryItemRequestDetail.Status.Pending);
			
			itemService.save(sird);
		}
		catch (Exception e) {
			 e.printStackTrace();
		}
	}
	
	@RequestMapping("/getStationeryItemReqDetails")
	public HashMap<String,Object> getItemReqDetails(HttpServletRequest request,@RequestParam(value="username",required=false) String username)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String status=request.getParameter("query[status]");
			String searchterm=request.getParameter("query[generalSearch]");
						 
			Page<StationeryItemRequestDetail> pd=itemService.getItemReqDetails(pageno, perpage, sort,title,username,status,searchterm);
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",pd.getContent());

		   return hm;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
}
