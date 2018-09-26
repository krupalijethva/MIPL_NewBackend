package com.monarch.actions;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.util.SquigglyUtils;
import com.monarch.domain.LeaveRecords;
import com.monarch.domain.OfficialLeaveDetail;
import com.monarch.domain.User;
import com.monarch.domain.UserLeaveDetail;
import com.monarch.service.LeaveService;

import com.monarch.service.OfficialLeaveDetailService;
import com.monarch.service.UserService;
import com.monarch.utils.DateUtil;


import static com.monarch.domain.LeaveRecords.Status.PENDING_ADMIN;
import static com.monarch.domain.LeaveRecords.Status.PENDING_TEAMLEAD;
import static com.monarch.domain.LeaveRecords.Status.REJECT;

@RestController
@CrossOrigin(origins="*",allowedHeaders="*")
public class ManageLeaveRestController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	LeaveService leaveService;
	
	@Autowired
	OfficialLeaveDetailService officialService;
	
	@RequestMapping(value="/manageLeave/{username}",method = RequestMethod.GET)
	public  HashMap<String, Object> getUserDetails(@PathVariable String username,HttpServletRequest request)
	{
		  User user = userService.getUser(username);
		  HashMap<String, Object> hm=new HashMap<String, Object>();
	        try 
	        {
	            List<User> list = userService.getActiveUserList("Activate"); 
	            HashSet<User> usersList = new HashSet<User>(list);
	            ArrayList<User> usersListWithoutDuplicates = new ArrayList<User>(usersList);
	            
	            Collections.sort(usersListWithoutDuplicates, new Comparator<User>() 
	            {
	                public int compare(User v1, User v2) 
	                {
	                    return v1.getFullname().compareTo(v2.getFullname());
	                }
	            }); 
	            
	            ObjectMapper objectMapper1 = Squiggly.init(new ObjectMapper(), "fullname");
	            ObjectMapper objectMapper2 = Squiggly.init(new ObjectMapper(), "**,-scrumDetails,-teamScrumDetails,-leavedetail,-employeedetail,-salarydetail");
	            
	            String usersdata=SquigglyUtils.stringify(objectMapper1, usersListWithoutDuplicates);
	            String users=SquigglyUtils.stringify(objectMapper2, user);
	            
	           hm.put("userList", usersdata);
	           hm.put("user", users);
	  	     return hm;  
	        } 
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        	return null;
	        }	 
	}
	
	@GetMapping("/getCredit/{username}")
	public String getCredit(@PathVariable String username)
	{
		User user = userService.getUser(username);
		long totalleave = 0;
		String creditStr="";
		 try 
		 {
	            LeaveRecords obj = new LeaveRecords();
	            UserLeaveDetail userleavedetail = user.getLeavedetail();
	            totalleave = (long) userleavedetail.getLeavecredit();
	            long usedleave = leaveService.getTotalCredit(username);

	            long credit = totalleave - usedleave;
	            creditStr= obj.LeaveCredit(credit);
	            return creditStr;
		 }
		 catch (Exception e)
		 {
	           e.printStackTrace();
	           return null;
	     }
	}
	
	@RequestMapping(value="/getLeaveRecords/{username}",method = RequestMethod.POST)
	public HashMap<String,Object> getLeaveRecords(HttpServletRequest request,@PathVariable String username)
	{
		try
		{
			User us=userService.getUser(username);
			long leaveID=us.getLeavedetail().getLeaveid();
			int pageno=Integer.parseInt((request.getParameter("pagination[page]")));
			int perpage=Integer.parseInt((request.getParameter("pagination[perpage]")));
			String sort=request.getParameter("sort[sort]");
			String title=request.getParameter("sort[field]");
			String generalSearch=request.getParameter("query[generalSearch]");
			String status=request.getParameter("query[leaveStatus]");
			System.out.println(generalSearch);
			System.out.println(status);
			 Page<LeaveRecords> ls=leaveService.getAllLeaveRecords(pageno, perpage, sort,title,leaveID,generalSearch,status);
			 List<LeaveRecords> leavelist=null;

			 leavelist=ls.getContent();
			 
			 ObjectMapper objectMapper = Squiggly.init(new ObjectMapper(), "-userleavedetail");
			 
	         System.out.println(SquigglyUtils.stringify(objectMapper, leavelist));
	         
	         String leavedata=SquigglyUtils.stringify(objectMapper, leavelist);
	         
			HashMap<String,Object> hm=new HashMap<String,Object>();
			HashMap<String,String> metamap=new HashMap<String,String>();
			
//			metamap.put("field", "idPunching");
			metamap.put("page",String.valueOf(pageno));
			metamap.put("pages",String.valueOf(ls.getTotalPages()));
			metamap.put("perpage",String.valueOf(perpage));
//			metamap.put("sort","desc");
			metamap.put("total",String.valueOf(ls.getTotalElements()));
			
			hm.put("meta",metamap);
			hm.put("data",leavedata);
			
			return hm;
		}
		catch (Exception e)
		 {
	           e.printStackTrace();
	           return null;
	     }
	}
	
	
	@RequestMapping(value="/insertLeave",method = RequestMethod.POST)
	public String insertLeave(@RequestBody User us,@RequestParam(value="teamlead") String teamlead,@RequestParam(value="durationval") String durationType,@RequestParam(value="fromdate") String fromdate1,@RequestParam(value="todate") String todate1,@RequestParam(value="fromdatetime") String fromdatetime,@RequestParam(value="todatetime") String todatetime,@RequestParam(value="reason") String reason,@RequestParam(value="username") String username) throws ParseException
	{
		LeaveRecords leaveObj = new LeaveRecords();
        UserLeaveDetail userleavedetail = userService.getUser(username).getLeavedetail();
            
        Date fromDate;
        Date toDate;
        long totalDuration = 0;
        String startDate;
        String endDate;
        String fromDayOfWeek = "";
        String toDayOfWeek = "";
        String leaveType="General Leave";
         try 
         {
            if (durationType.equals("FULLDAY"))
        	 {
            	Date fdate=new SimpleDateFormat("MM/dd/yyyy").parse(fromdate1);  
                Date tdate=new SimpleDateFormat("MM/dd/yyyy").parse(todate1);  
            	SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");  
         	    String fromdate= formatter.format(fdate);  
         	    String  todate=formatter.format(tdate);
         	    
                startDate = fromdate;
                endDate = todate;
                fromDate = DateUtil.stringToDate(startDate);
                toDate = DateUtil.stringToDate(endDate);
                int workingDays = DateUtil.getWorkingdays(startDate, endDate);
                Integer i = 0;
                Calendar cal = Calendar.getInstance();
                cal.setTime(fromDate);
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEEEE");
                fromDayOfWeek = dateFormat.format(cal.getTime());
            
                cal.add(Calendar.DATE, -1);
                while (cal.getTime().before(toDate)) 
                {
                    cal.add(Calendar.DATE, 1);
                    Date date1 = cal.getTime();
                    toDayOfWeek = dateFormat.format(cal.getTime());
                    SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
                    String date2 = DATE_FORMAT.format(date1);
                    List<OfficialLeaveDetail> leavelist = officialService.readAll();
                    HashSet<OfficialLeaveDetail> LeaveList = new HashSet<OfficialLeaveDetail>(leavelist);
                    ArrayList<OfficialLeaveDetail> leavelist1 = new ArrayList<OfficialLeaveDetail>(LeaveList);

                    for (OfficialLeaveDetail ofc : leavelist1) {
                        Date leavedate = ofc.getLeaveDate();
                        String date = DATE_FORMAT.format(leavedate);
                        //Date leavedate1 = DateUtil.stringToDate(leavedate);
                        if (date.equals(date2)) {
                            i = i + 1;
                        }
                    }
                }
                workingDays = workingDays - i;
                int leaveHours = (int) (workingDays * 8);
                String time = leaveHours + ":00:00";
                totalDuration = DateUtil.stringToTime(time);
                leaveObj.setDurationtype(LeaveRecords.LeaveDurationType.FULLDAY);
        	 } 
        	 else 
        	 {
            	 Date fdatetime=new SimpleDateFormat("dd-MM-yyyy HH:mm a").parse(fromdatetime);  
                 Date tdatetime=new SimpleDateFormat("dd-MM-yyyy HH:mm a").parse(todatetime);
                 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm a");  
        	     String fromdate= formatter.format(fdatetime);  
        	     String  todate=formatter.format(tdatetime);
                startDate =fromdate;
                endDate = todate;
                fromDate = DateUtil.stringToDateTime(startDate);
                toDate = DateUtil.stringToDateTime(endDate);
                totalDuration = toDate.getTime() - fromDate.getTime();
                leaveObj.setDurationtype(LeaveRecords.LeaveDurationType.PARTIAL_DAY);
            }
            //String comments = request.getParameter("reason");
            Date appliedTime = new Date();
            leaveObj.setTeamlead(teamlead);
            //leaveObj.setNotificationleader(notificationTo);
            leaveObj.setLeavetype(leaveType);
            leaveObj.setFromdate(fromDate);
            leaveObj.setTodate(toDate);
            leaveObj.setCommentsEmployee(reason);
            leaveObj.setDuration(totalDuration);
            leaveObj.setLeavestatus(LeaveRecords.Status.PENDING_TEAMLEAD);
            leaveObj.setAppliedtime(appliedTime);
            leaveObj.setUserleavedetail(userleavedetail);

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(fromDate);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(appliedTime);
            int diff = cal1.get(Calendar.DAY_OF_YEAR) - cal2.get(Calendar.DAY_OF_YEAR);
            // cal2.add(Calendar.DATE, 3);

            if (diff < 3) {
                leaveObj.setLeaveCategory("UnPlanned");
            } else {
                leaveObj.setLeaveCategory("Planned");
            }

            leaveService.create(leaveObj);

            User user = userService.getUserFromFullName(teamlead);
            String leader = user.getUsername();
            String teamlead1[] = teamlead.split("@");
            String teamleader = teamlead1[0];
            teamleader += "@monovative.com";

            User user1 = userService.getUser("harshap");
            String username1 = user1.getFullname();

            List<String> LeaveList = new ArrayList<String>();              
            LeaveList.add("harshj@monovative.com");
            LeaveList.add("maulikp@monovative.com");
            LeaveList.add("anantb@monovative.com");
         //   MailService.sendMail(teamleader, username1 + " has Applied for Leave", username1 + " has Applied Leave " + "\n\n" + "from " + "\n" + "\t" + startDate + "(" + fromDayOfWeek + ")" + "\n " + "to " + "\n" + "\t" + endDate + "(" + toDayOfWeek + ")" + "\n\n" + "Reason:" + comments + "\n\n\n\n\n\n\n" + "This is auto generated mail, please dont reply to this mail", LeaveList);
        
		
        }
        catch (Exception e) 
        {
		
        	e.printStackTrace();
		}
		
        return "";
	}
	
	
	@RequestMapping(value="/deleteLeave/{id}",method = RequestMethod.DELETE)
	public String getLeaveRecords(@PathVariable long id)
	{
		
		LeaveRecords leaveObj=leaveService.getLeave(id);
		LeaveRecords.Status s1=leaveObj.getLeavestatus();
		System.out.println(s1);
		
		try
		{
			 if (PENDING_TEAMLEAD == s1 || PENDING_ADMIN == s1 || REJECT == s1) {
	             leaveService.deleteLeave(leaveObj);
	         } else {
	
	             try {
	            	 LeaveRecords.Status status = LeaveRecords.Status.CANCEL;
	                 leaveObj.setLeavestatus(status);
	                 leaveService.create(leaveObj);
	
	             } catch (Exception e) {
	                 e.getMessage();
	             }
	
	         }
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return "";
	}
	

}
