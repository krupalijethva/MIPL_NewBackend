package com.monarch.actions.leave;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.User;
import com.monarch.domain.UserLeaveDetail;
import com.monarch.service.UserLeaveDetailService;
import com.monarch.service.UserService;
import com.monarch.utils.DateUtil;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class AssignLeaveRestController {
	
	
	@Autowired
	UserService us;
	
	@Autowired
	UserLeaveDetailService ls;
	
	@PostMapping("/assignLeave")
	public ResponseEntity<HashMap<String,Object>> getLeaveRecords(HttpServletRequest request) throws Exception 
	{
		try
		{
			Page<User> pd=null;
			JSONArray leavelist = new JSONArray();
			
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String generalSearch=request.getParameter("query[generalSearch]");
			
	        pd=us.getActiveUsers(pageno, perpage, sort,title,generalSearch);
	        
	        List<User> ulist=pd.getContent();
	        ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "**,leavedetail{-user,-leaveRecords}");
	        String hi=SquigglyUtils.stringify(objectMapper, ulist);
	        
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
//			metamap.put("sort","desc");
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			hm.put("meta",metamap);
			hm.put("data",hi);
			
			System.out.println(pd.getContent());
			
			if(hm.isEmpty())
			{
				return new ResponseEntity<HashMap<String,Object>>(HttpStatus.NO_CONTENT);
			}
			else
			{
				return new ResponseEntity<HashMap<String,Object>>(hm, HttpStatus.OK);
			}

		}
		catch (Exception e) {
			return new ResponseEntity<HashMap<String,Object>>(HttpStatus.NO_CONTENT);
		}
	}
	
	
	@GetMapping("/assignLeaveInsert")
	public void assignLeaveInsert(HttpServletRequest request,@RequestParam(value="id") long id,@RequestParam(value="leaveCategory") String leaveCategory)
	{
		
		 try {
	       	    User user = new User();
	            UserLeaveDetail userLeaveDetail = new UserLeaveDetail();
	            
	            Integer eligibleLeave = Integer.parseInt(leaveCategory);;

	            user = us.readinfo(id);

	            int leaveHours = (int) (eligibleLeave * 8);
	            String time = leaveHours + ":00:00";
	            long totalhours = DateUtil.stringToTime(time);

	            if (user.getLeavedetail() != null) {
	                userLeaveDetail = user.getLeavedetail();
	            }

	            userLeaveDetail.setLeavecredit(totalhours);
	            userLeaveDetail.setTotalleave(eligibleLeave);
	            userLeaveDetail.setOpeningbalance(0);
	            userLeaveDetail.setJoiningMonth(0);
	            userLeaveDetail.setUser(user);

	            if (user.getLeavedetail() == null) {
	                ls.save(userLeaveDetail);
	            } else {
	                ls.save(userLeaveDetail);
	            }

	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
	

}
