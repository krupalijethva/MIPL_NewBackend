package com.monarch.actions.license;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.ExitEmployeeDetail;
import com.monarch.domain.LicenseDetail;
import com.monarch.service.LicenseDetailService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class LicenseRestController {

	@Autowired
	LicenseDetailService licenseService;
	
	
	
	@RequestMapping("/requestLicense")
	public HashMap<String, String> requestLicense(@RequestParam(value="username") String username,@RequestParam(value="regkey") String regkey)
	{
		HashMap<String, String> hm=new HashMap<>();
		
		LicenseDetail ld=new LicenseDetail();
		ld.setRegistrationkey(regkey);
        ld.setCompany("Monarch Innovation");
        Date dt = new Date();
        ld.setDownloaddate(dt);
        ld.setLanguage("English");
        ld.setEmail("info@monovative.com");
        ld.setQuantity(1);
        ld.setInternal(true);
        ld.setFirstname(username);
        
        long id=licenseService.save(ld);
        String licensekey1;
        licensekey1 = licenseService.generateLicenseKey(id);
		
        hm.put("licensekey", licensekey1);
        if (licensekey1.equals("")) {
            hm.put("msg", "Invalid Registration key");

        } else {
            hm.put("msg", "License Key Successfully Generated");
        }
		return hm;
	}
	
	@PostMapping("/getPendingLicenseDetails")
	public HashMap<String,Object> getLicenseDetails(HttpServletRequest request)
	{
		String licenseid=request.getParameter("query[licenseID]");
		System.out.println("license id:"+licenseid);
		
		try
		{
			Page<LicenseDetail> pd=null;
			
			int pageno=Integer.parseInt(request.getParameter("pagination[page]"));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			System.out.println(request.getParameter("query[generalSearch]"));
			String generalSearch=request.getParameter("query[generalSearch]");
			
		
				pd=licenseService.getPendingLicenseDetails(pageno, perpage, sort, title, generalSearch);
			
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
		catch (Exception e)
        {
        	e.printStackTrace();
        	return null;
        }	
	}
	
	@PostMapping("/getDetailsForChild")
	public HashMap<String,Object> getDetailsForChild(HttpServletRequest request,@RequestParam(value="licenseID") String licenseid)
	{
		System.out.println("license id:"+licenseid);
		
		try
		{
			Page<LicenseDetail> pd=null;
			
			int pageno=Integer.parseInt(request.getParameter("pagination[page]"));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			System.out.println(request.getParameter("query[generalSearch]"));
			String generalSearch=request.getParameter("query[generalSearch]");
			
				long id=Long.parseLong(licenseid);
				pd=licenseService.getPendingLicenseDetails(pageno, perpage, sort, title, generalSearch,id);
			
			 
			
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
		catch (Exception e)
        {
        	e.printStackTrace();
        	return null;
        }	
	}
	
	@PostMapping("/generateLicense")
	public HashMap<String,Object> generateLicense(HttpServletRequest request,@RequestParam(value="licenseID") String licenseid)
	{
		System.out.println("license id:"+licenseid);
		
		try
		{
			Page<LicenseDetail> pd=null;
			
			int pageno=Integer.parseInt(request.getParameter("pagination[page]"));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			System.out.println(request.getParameter("query[generalSearch]"));
			String generalSearch=request.getParameter("query[generalSearch]");
			
				long id=Long.parseLong(licenseid);
				pd=licenseService.getPendingLicenseDetails(pageno, perpage, sort, title, generalSearch,id);
			
			 
			
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
		catch (Exception e)
        {
        	e.printStackTrace();
        	return null;
        }	
	}
	
	@PostMapping("/getAllLicenseDetails")
	public HashMap<String,Object> getAllLicenseDetails(HttpServletRequest request)
	{
		String licenseid=request.getParameter("query[licenseID]");
		System.out.println("license id:"+licenseid);
		
		try
		{
			Page<LicenseDetail> pd=null;
			
			int pageno=Integer.parseInt(request.getParameter("pagination[page]"));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			System.out.println(request.getParameter("query[generalSearch]"));
			String generalSearch=request.getParameter("query[generalSearch]");
			
		
				pd=licenseService.getAllLicenseDetails(pageno, perpage, sort, title, generalSearch);
			
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
		catch (Exception e)
        {
        	e.printStackTrace();
        	return null;
        }	
	}
	
	@PostMapping("deleteLicense")
	public void deleteLicense(@RequestParam(value="licenseid") String licenseid)
	{
		if(!licenseid.equals(null) )
		{
			long id=Long.parseLong(licenseid);
			licenseService.delete(id);
		}  
	}
	
	
	
	@PostMapping("/readLicenseDetails")
	public LicenseDetail readLicenseDetails(@RequestParam(value="licenseid",required=false)String licenseid)
	{
		try
		{
			if(!licenseid.equals(null) )
			{
				long id=Long.parseLong(licenseid);
				LicenseDetail ld=licenseService.readInfo(id);
				return ld;
				
			}
			else
			{
				return null;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
		
	}
	@PostMapping("/generateLicenseKeywithobj")
	public HashMap<String, String> generateLicense(@RequestParam(value="licensetype") String licensetype, @RequestBody LicenseDetail ld)
	{ 
		System.out.println(licensetype);
		
		System.out.println(ld);
		
		HashMap<String, String> hm=new HashMap<>();
		long licenseID=0;
		String licensekey="";
		LicenseDetail ld1=null;
		if(ld.getIdLicenseDetail() == 0)
		{
			licenseID=licenseService.save(ld);
		}
		else
		{
			licenseID=ld.getIdLicenseDetail();
		}
		
		if(licensetype.equals("Time Limited License"))
		{
			licensekey=licenseService.generateLicenseKey(licenseID, ld.getValidtilldate());	
		}
		else
		{
			licensekey=licenseService.generateLicenseKey(licenseID);
		}
		ld1=licenseService.readInfo(licenseID);
		 hm.put("licensekey", licensekey);
		 hm.put("appname",ld1.getApplication());
	        
		return hm;	
	}
}
