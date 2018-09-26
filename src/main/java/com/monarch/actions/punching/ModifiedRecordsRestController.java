package com.monarch.actions.punching;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.PunchingDetailEdit;

import com.monarch.domain.PunchingDetail;
import com.monarch.service.PunchingEditService;
import com.monarch.service.PunchingService;
import com.monarch.utils.DateUtil;


@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ModifiedRecordsRestController {
	
	
	
	@Autowired
	PunchingEditService pds;
	
	@Autowired
	PunchingService ps;
	
	@PostMapping("/getModifiedRecords")
	public HashMap<String,Object> getModifiedRecords(@RequestParam(value = "pagination[page]", required = false) int pageno,
			@RequestParam(value = "pagination[perpage]", required = false) int perpage,
			@RequestParam(value = "sort[sort]", required = false) String sort,
			@RequestParam(value = "sort[field]", required = false) String title,
			@RequestParam(value = "query[generalSearch]", required = false) String generalSearch,@RequestParam(value = "query[punchStatus]", required = false) String punchstatus)
	{
		try
		{	
			Page<PunchingDetailEdit> pd=pds.getModifiedRecords(pageno, perpage, sort,title,generalSearch,punchstatus);			
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
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}

	@GetMapping("/recordAction")
	public void recordActions(@RequestParam(value = "id")  long id,@RequestParam(value = "action") String action,@RequestParam(value = "comments") String comments)
	{
		 try {
			 	PunchingDetailEdit ped =pds.findById(id);

	            if (action.equals("approve"))
	            {
	               // ped.setPunchStatus(PunchingDetail.Status.COMPLETE);
	                	 ped.setPunchStatus(PunchingDetail.Status.MODIFIED);
	            } 
	            else if (action.equals("deny"))
	            {
	                ped.setPunchStatus(PunchingDetail.Status.DENIED);
	            }
	            ped.setComments(ped.getTeamlead() + ": " + comments);
	            pds.create(ped);

	       
	            PunchingDetail pd =ps.getPunchingDetails(ped.getUsername(), ped.getLogindate());

	            if (action.equals("approve")) {
	                pd.setLoginTime(ped.getLoginTimeedit());
	                pd.setLogoutTime(ped.getLogoutTimeedit());
	                pd.setBreakTime(ped.getBreakTimeedit());
	                pd.setTotalHours(DateUtil.dateToTime(ped.getTotalHoursedit()));
					
					 //TEMP Add Status when Approve 
	                pd.setPunchStatus(ped.getPunchStatus());
	            }
	            pd.setComments(ped.getComments());
	            pd.setCommentsEmployee(ped.getEmployeeComments());
	            pd.setPunchStatus(ped.getPunchStatus());

	            ps.create(pd);

	        } 
		 	catch (Exception ex) 
		 	{
	           ex.getMessage();
	        }		
		
	}
	
	@GetMapping("/getParticularRecord/{id}")
	public ResponseEntity<PunchingDetailEdit> getParticularRecord(@PathVariable long id)
	{
		PunchingDetailEdit pde=null;
		try
		{
			pde=pds.findById(id);
			
			if(pde != null)
			{
				return new ResponseEntity<PunchingDetailEdit>(pde, HttpStatus.OK);
				
			}
			return new ResponseEntity<PunchingDetailEdit>(HttpStatus.NO_CONTENT);
			
		}
		catch (Exception e) {
			return new ResponseEntity<PunchingDetailEdit>(HttpStatus.NO_CONTENT);
		}
		
	}
		
}
