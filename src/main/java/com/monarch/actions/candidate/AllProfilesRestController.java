package com.monarch.actions.candidate;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.User;
import com.monarch.domain.candidate.Candidatedetail;
import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.service.FileStorageService;
import com.monarch.service.UserService;
import com.monarch.service.candidate.InterviewService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class AllProfilesRestController {
	
	@Autowired
	InterviewService is;
	
	@Autowired
	FileStorageService fss;
	
	@Autowired
	UserService us;
	
	@RequestMapping("/getAllProfiles")
	public HashMap<String,Object> GetAllProfiles(HttpServletRequest request)
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
			 
			Page<Candidatedetail> pd=is.getCandidateRecords(pageno, perpage, sort, title, generalSearch);
			
			
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
//			metamap.put("sort","desc");
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",pd.getContent());

		   return hm;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}
	@GetMapping("/getCandidaProfile")
	public Candidatedetail GetCandidateProfile(@RequestParam(value="id") long id)
	{
		try
		{
			Candidatedetail cd=is.getbyID(id);
			if(cd != null)
			{
				return cd;
			}
			else
			{
				return null;
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@GetMapping("/getFiles")
	public ResponseEntity<byte[]> getResume(@RequestParam(value="filename") String filename)
	{
		Path filepath=fss.getFiles(filename);
		String filepath1=filepath.toString();	
        try
        {  
	        byte[] array = Files.readAllBytes(new File(filepath1).toPath());
            System.out.println(array);
            return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + new File(filepath1).getName() + "\"")
					.body(array);	
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
    		return ResponseEntity.status(404).body(null);	
        }
     }
	
	@GetMapping("/getUsernameList")
	public HashMap<String, Object> getUsernameList() 
	{
		try 
		{
			HashMap<String, Object> hm=new HashMap<String, Object>();
	         List<String> usernamelist1 = new ArrayList<String>();
	         HashSet<String> usernamelist = new HashSet<String>(usernamelist1);
	         //List<User> user = userService.readAll();
	         List<User> user = us.getActiveUserList("Activate");
	         for (Iterator iterator = user.iterator(); iterator.hasNext();) {
	             User useremp = (User) iterator.next();
	             EmployeeDetail emp = useremp.getEmployeedetail();
	             if (emp != null) {
	                 String uname = useremp.getUsername();
	             } else {
	                 String uname = useremp.getUsername();
	                 usernamelist.add(uname);
	             }
	         }
	         hm.put("userList", usernamelist);
			return hm;
		}
		catch(Exception e)
        {
        	System.out.println(e.getMessage());
    		return null;	
        }  
	}
} 
