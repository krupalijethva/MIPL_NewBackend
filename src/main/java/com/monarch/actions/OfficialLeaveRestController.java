package com.monarch.actions;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monarch.domain.OfficialLeaveDetail;
import com.monarch.service.OfficialLeaveDetailService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class OfficialLeaveRestController{
	
	@Autowired
	OfficialLeaveDetailService ods;
	
	@PostMapping("/getOfficialLeave")
	public HashMap<String,Object> getUsers(HttpServletRequest request)
	{
		try
		{
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			
			Page<OfficialLeaveDetail> pd=ods.GetOfficialLeveList(pageno, perpage, sort, title);
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
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
}
