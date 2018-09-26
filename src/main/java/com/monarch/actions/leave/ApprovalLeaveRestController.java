package com.monarch.actions.leave;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.LeaveRecords;
import com.monarch.domain.User;
import com.monarch.domain.UserLeaveDetail;
import com.monarch.service.LeaveService;
import com.monarch.service.UserLeaveDetailService;
import com.monarch.service.UserService;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ApprovalLeaveRestController 
{

	@Autowired
	LeaveService ls;
	
	@Autowired
	UserService us;
	
	@Autowired
	UserLeaveDetailService udls;
	
	@PostMapping("/approvalLeaveRecords/{username}")
	public HashMap<String,Object> getLeaveRecords(HttpServletRequest request,@PathVariable String username)
	{
		try
		{
			Page<LeaveRecords> pd=null;
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String leaveStatus=request.getParameter("query[leaveStatus]");
			System.out.println(request.getParameter("query[generalSearch]"));
			String generalSearch=request.getParameter("query[generalSearch]");
			 List<LeaveRecords> leavelist=null;
			 User user = us.getUser(username);
			 if (username.toLowerCase().equals("admin")) {
				 pd=ls.getLeaveRecords(pageno, perpage, sort,title,leaveStatus,generalSearch);
	         } else {
	             pd = ls.getLeaveRecords(pageno, perpage, sort,title,leaveStatus,generalSearch,user.getFullname());
	         }

			 leavelist=pd.getContent();
			 ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "**,userleavedetail[**,-leaveRecords,user[**,-leavedetail,-scrumDetails,-teamScrumDetails,-teamScrumDetails,-salarydetail]]");
	         System.out.println(SquigglyUtils.stringify(objectMapper, leavelist));
	         
	         String leaveData=SquigglyUtils.stringify(objectMapper, leavelist);
			 
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(pd.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
//			metamap.put("sort","desc");
			metamap.put("total",String.valueOf(pd.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",leaveData);

		   return hm;

		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
				
	}
	
	@GetMapping("/approveLeave")
	public void aprroveLeave(@RequestParam(value = "id")  long id,@RequestParam(value = "action") String action,@RequestParam(value = "comments") String comments,@RequestParam(value = "username") String username,@RequestParam(value = "name") String name)
	{
        try {

            LeaveRecords leave =ls.getLeave(id);
            UserLeaveDetail userleavedetail = leave.getUserleavedetail();
            User user = us.getUser(username);
            User user1 = us.getUserFromFullName(name);
            String username1 = user.getFullname();
            String username2 = user1.getUsername();
            String username3[] = username2.split("@");
            String teamleader = username3[0];
            teamleader += "@monovative.com";

            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
            Date fromDate = leave.getFromdate();
            String fromDate1 = DATE_FORMAT.format(fromDate);
            Date todate = leave.getTodate();
            String toDate1 = DATE_FORMAT.format(todate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(fromDate);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
            String fromDayOfWeek = dateFormat.format(cal.getTime());
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(todate);
            String toDayOfWeek = dateFormat.format(cal1.getTime());

            List<String> LeaveList = new ArrayList<String>();
            LeaveList.add("harshj@monovative.com");
            LeaveList.add("maulikp@monovative.com");
            LeaveList.add("anantb@monovative.com");
            // LeaveList.add("divyar@monovative.com");

            if (action.equals("approve")) 
            {
//                MailService.sendMail(teamleader, username1 + " has Approved Leave", "Leave Approved by " + username1 + "\n\n" + "from " + "\n" + "\t" + fromDate1 + "(" + fromDayOfWeek + ")" + "\n " + "to " + "\n" + "\t" + toDate1 + "(" + toDayOfWeek + ")" + "\n\n" + "Comments:" + comments + "\n\n\n\n\n\n\n" + "This is auto generated mail, please don't reply to this mail", LeaveList);
                if (username.toLowerCase().equals("admin")) {
                    String fullname = user.getFullname();
                    leave.setCommentsProjectControler(comments);
                    leave.setLeavestatus(LeaveRecords.Status.APPROVED);

//                    MailService.sendMail(fullname, username1 + " has Approved Leave", "Leave Approved by " + username1 + "\n\n" + "from " + "\n" + "\t" + fromDate1 + "(" + fromDayOfWeek + ")" + "\n " + "to " + "\n" + "\t" + toDate1 + "(" + toDayOfWeek + ")" + "\n\n" + "Comments:" + comments + "\n\n\n\n\n\n\n" + "This is auto generated mail, please don't reply to this mail", LeaveList);
                } 
                else
                {
                    leave.setLeavestatus(LeaveRecords.Status.PENDING_ADMIN);
                    leave.setCommentsTeamLead(comments);
                }
                ls.create(leave);
                udls.save(userleavedetail);

            } 
            else if (action.equals("deny")) 
            {

//                MailService.sendMail(teamleader, username1 + " has Denied Leave", "Leave Denied by " + username1 + "\n\n" + "from " + "\n" + "\t" + fromDate1 + "(" + fromDayOfWeek + ")" + "\n " + "to " + "\n" + "\t" + toDate1 + "(" + toDayOfWeek + ")" + "\n\n" + "Comments:" + comments + "\n\n\n\n\n\n\n" + "This is auto generated mail, please don't reply to this mail", LeaveList);
                leave.setLeavestatus(LeaveRecords.Status.REJECT);

                if (username.toLowerCase().equals("admin")) {
                    String fullname = user.getFullname();
                    leave.setCommentsProjectControler(comments);
//                    MailService.sendMail(fullname, username1 + " has Approved Leave", "Leave Approved by " + username1 + "\n\n" + "from " + "\n" + "\t" + fromDate1 + "(" + fromDayOfWeek + ")" + "\n " + "to " + "\n" + "\t" + toDate1 + "(" + toDayOfWeek + ")" + "\n\n" + "Comments:" + comments + "\n\n\n\n\n\n\n" + "This is auto generated mail, please don't reply to this mail", LeaveList);
                } else {
                    leave.setCommentsTeamLead(comments);
                }

                ls.create(leave);
                udls.save(userleavedetail);

            } 

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

	}
	
	@GetMapping("/leaveRecords/{id}")
	public ResponseEntity<Object> getParticularRecord(@PathVariable long id)
	{
		try
		{
			LeaveRecords leave=ls.getLeave(id);
			
			ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "**,userleavedetail[**,-leaveRecords,user[**,-leavedetail,-scrumDetails,-teamScrumDetails,-teamScrumDetails,-salarydetail]]");
	      
	        Object leaveData=SquigglyUtils.stringify(objectMapper, leave);
			if(ls == null)
			{
				return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
				
			}
			else
			{
				return new ResponseEntity<Object>(leaveData, HttpStatus.OK);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
		}
		
		
	}
	
	
}
