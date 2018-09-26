package com.monarch.service;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.monarch.domain.LeaveRecords;
import com.monarch.domain.UserLeaveDetail;
import com.monarch.repository.LeaveRepository;

@Service
public class LeaveServiceImpl implements LeaveService
{

	
	@Autowired
	LeaveRepository leaverepo;
	
	@Autowired
	UserService userService;

	@Override
	public void create(LeaveRecords ls)
	{

		leaverepo.save(ls);
		
	}
	
	public LeaveRecords getLeave(long id) 
	{
		
		return leaverepo.findOne(id);
		
	}
	
	public void deleteLeave(LeaveRecords ls)
	{
		
		leaverepo.delete(ls);
	}
	
	@Override
	public long getTotalCredit(String username) 
	{
	
		
        UserLeaveDetail userleavedetail = userService.getUser(username).getLeavedetail();
        
        long leaveId = userleavedetail.getLeaveid();
        
        long duration = leaverepo.getDuration(leaveId);
        
        long totalLeaveCredit = 0;
        if (duration > 0) 
        {
        	Object ob = leaverepo.LeaveCredit(leaveId);
            if (ob == null)
            {
            	totalLeaveCredit = userleavedetail.getTotalleave();
            }
            else
            {
            	totalLeaveCredit = (long)ob;
            }
        }
        return totalLeaveCredit;
	
	}

	@Override
    public int getWorkingdays(String startDate, String endDate)
	{

        Calendar fromDate = stringToCalDate(startDate);
        Calendar toDate = stringToCalDate(endDate);

        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();

        startCal.setTime(fromDate.getTime());
        endCal.setTime(toDate.getTime());
        int workDays = 0;

        //If working dates are same,then checking what is the day on that date.
        if (startCal.getTimeInMillis() == endCal.getTimeInMillis())
        {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
            {
                ++workDays;
                return workDays;
            }
        }

        /*If start date is coming after end date, Then shuffling Dates and storing dates 
		by incrementing upto end date in do-while part.*/
        
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) 
        {
            startCal.setTime(fromDate.getTime());
            endCal.setTime(toDate.getTime());
        }

        do {
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
            {
                ++workDays;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        } while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

        return workDays;
    }

    public Date stringToDate(String date)
    {
        try 
        {
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); // MM/dd/yyyy
            return df.parse(date);
            
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Date stringToDateTime(String datetime) 
    {

        try 
        {
            //SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm a");  //hh:mm a 13:00:00 AM 24hours format             
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm a");  // HH 12 hours
            return df.parse(datetime);

        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public long stringToTime(String timeStr)
    {
        long time = 0;

        try {
	            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	
	            String inputString = timeStr; //"01:30:00";
	
	            Date date = sdf.parse(inputString);// .parse("1970-01-01" + inputString);
	            time = date.getTime();

        	}
        catch (Exception e) 
	        {
	            	System.out.println(e.getMessage());
	        }
        return time;
    }

    public String timeToString(long time)
    {
        String timeStr = null;

        long totalSecs = time / 1000;
        long hours = (totalSecs / 3600);
        long mins = (totalSecs / 60) % 60;
        long secs = totalSecs % 60;

        if (mins > 0) 
        {
            timeStr = hours + ":" + mins + ":00";
        }
        else
        {
            timeStr = hours + ":00:00";
        }

        return timeStr;
    }

    public Calendar longToCalDateTime(long time) 
    {
        Calendar cal = Calendar.getInstance();
        time -= TimeZone.getDefault().getRawOffset();
        Date date = new Date(time);
        cal.setTime(date);

        return cal;
    }

    public Calendar stringToCalDate(String date) 
    {

    	
        try 
        {
            SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");   //MM/dd/yyyy

            Calendar cal = Calendar.getInstance();
            cal.setTime(format.parse(date));

            return cal;
        } 
        catch (Exception e) 
        {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    
    @Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title)
    {
    	String sort1="desc";
    	String title1="appliedtime";
    	if(sort == null && title == null)
    	{
    		sort=sort1;
    		title=title1;
    	}
    	
    	Pageable p=new PageRequest(pageno-1,perpage,Sort.Direction.fromString(sort),title);
        
        return p;	
	}


	@Override
	public Page<LeaveRecords> getAllLeaveRecords(int pageno, int perpage, String sort, String title,long leaveid,String generalSearch,String status) 
	{
		Pageable p=createPageRequest(pageno, perpage, sort,title);
		Page<LeaveRecords> leaveRecords=null;
		
		if(generalSearch != null && status != null)
		{
			leaveRecords=leaverepo.findAllById(leaveid,p,status,generalSearch);
		}
		else if(generalSearch != null)
		{
			leaveRecords=leaverepo.findAllById(leaveid,generalSearch,p);
		}
		else if(status != null)
		{
			leaveRecords=leaverepo.findAllById(leaveid,p,status);
		}
		else 
		{
			leaveRecords=leaverepo.findAllById(leaveid, p);
		}
		return leaveRecords;
	}

	 @Override
		public Pageable createPageRequest1(int pageno, int perpage, String sort, String title)
	    {
	    	String sort1="desc";
	    	String title1="appliedtime";
	    	if(sort == null && title == null)
	    	{
	    		sort=sort1;
	    		title=title1;
	    	}
	    	
	    	Pageable p=new PageRequest(pageno-1,perpage,Sort.Direction.fromString(sort),title);
	        
	        return p;	
		}
	@Override
	public Page<LeaveRecords> getLeaveRecords(int pageno, int perpage, String sort, String title,String punchstatus,String generalSearch)
	{
		Pageable p=createPageRequest1(pageno, perpage, sort,title);
		
		

		Page<LeaveRecords> pd = null;
		
	        if(generalSearch != null && punchstatus != null)
			{			
					pd=leaverepo.getLeaveApprovalRecord(p, generalSearch,punchstatus);
			}
	        else if(generalSearch!=null) 
	        {
	        	
	        		System.out.println(generalSearch);
	        		pd = leaverepo.getLApprovalRecord(p,generalSearch);
	        }
		else if(punchstatus!=null)
		{
			 		pd=leaverepo.getLeaveApprovalRecord(p, punchstatus);
		}
		
		else 
		{
			 		pd=leaverepo.getLeaveApprovalRecord(p);
		}

		return pd;

	}

	public Page<LeaveRecords> getLeaveRecords(int pageno, int perpage, String sort, String title,String punchstatus,String generalSearch,String leader)
	{
		Pageable p=createPageRequest1(pageno, perpage, sort,title);
		
		Page<LeaveRecords> pd = null;
		String day = "";
		String month = "";
		String year = "";
		String x[];
	      
	        if(generalSearch != null && punchstatus != null)
			{
					if (StringUtils.countOccurrencesOf(generalSearch, "-") == 1)
					{
							x = generalSearch.split("-");
							day = x[0].trim();
							try 
							{
								 month = x[1].trim();
								 
							} catch (Exception e) 
							{
							
							}
	
					} 
					else if (StringUtils.countOccurrencesOf(generalSearch, "-") == 2)
					{
							
							x = generalSearch.split("-");
							try
							{
								day = x[0].trim();
								month = x[1].trim();
								year = x[2].trim();
								
							} catch (Exception e)
							{
								
							}
			
					}
					else 
					{
							day=generalSearch;
					}
				
					pd=leaverepo.getLeaveRecordLeader(p, generalSearch, day, month, year, punchstatus,leader);
			}
	        else if(generalSearch!=null) 
	        {
	        		if (StringUtils.countOccurrencesOf(generalSearch, "-") == 1)
	        		{
							x = generalSearch.split("-");
							day = x[0].trim();
							try 
							{
									month = x[1].trim();
							} catch (Exception e)
							{
						
							}

	        		} 
	        		else if (StringUtils.countOccurrencesOf(generalSearch, "-") == 2)
	        		{
	        				x = generalSearch.split("-");
			
							try 
							{
								day = x[0].trim();
								month = x[1].trim();
								year = x[2].trim();
								
							} catch (Exception e) 
							{
								
							}

	        		} 
	        		else 
	        		{
	        					day=generalSearch;
	        		}
	        		pd = leaverepo.getLeaveRecordLeader( p, generalSearch, day,month,year,leader);
		
		
		}
		else if(punchstatus!=null)
		{
			 		pd=leaverepo.getLeaveRecordLeader(leader,p, punchstatus);
		}
		
		else 
		{
			 		pd=leaverepo.getLeaveRecordLeader(leader,p);
		}

		return pd;

		
	}
	
	
	
	
	
	
/*
    @Override
    public List<LeaveRecords> readTeamMemberLeave(String leaderName) {
       
        return dao.readTeamMemberLeave(leaderName);
    }

    @Override
    public List<LeaveRecords> readAllLeave() {

        return leaverepo.readAllLeave();
    }
/*
    @Override
    public List<LeaveRecords> readApprovedLeave(String projectController) {
        
        return leaverepo.readApprovedLeave(projectController);
    }

    @Override
    public List<LeaveRecords> monthlyLeaveRecords(Date startDate, Date endDate) {
        LeaveDao dao = (LeaveDao) getGenericDao();
        return dao.monthlyLeaveRecords(startDate, endDate);
    }

    @Override
    public long getTotalCredit(String username) {
        LeaveDao dao = (LeaveDao) getGenericDao();
        return dao.getTotalCredit(username);
    }
    
*/

	@Override
	public List<LeaveRecords> readTeamMemberLeave(String leaderName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LeaveRecords> readApprovedLeave(String projectController) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<LeaveRecords> monthlyLeaveRecords(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<LeaveRecords> readAllLeaveSearchbyId(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	



	
	
	
}
