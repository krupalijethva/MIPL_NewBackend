/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.monarch.service;

import com.monarch.domain.OfficialLeaveDetail;


import com.monarch.domain.PunchingDetail;

import com.monarch.domain.User;
import com.monarch.domain.UserLeaveDetail;
import com.monarch.repository.LeaveRepository;
import com.monarch.repository.OfficialLeaveRepository;
import com.monarch.repository.PunchingRepository;
import com.monarch.utils.DateUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import static com.monarch.utils.DateUtil.stringToCalDate;


@Service("PunchingService")

public class PunchingServiceImpl implements PunchingService {

	@Autowired
	PunchingRepository punching;
	
	@Autowired
	UserService us;

	@Autowired
	OfficialLeaveDetailService offc;
	
	@Autowired
	LeaveRepository lr;
	
	@Autowired
	OfficialLeaveRepository olr;
	
	@Override
	public String insertLoginTime(String username, String ipAddress, String hostName)
	{
		
		PunchingDetail pd1 = read(username);
		if (pd1 != null)
		{
			return "";
		}

		PunchingDetail pd = new PunchingDetail();
		// TEMP Add Username in Lowercase if user Login with Uppercase
		pd.setUsername(username.toLowerCase());

		// TEMP
		pd.setIpAddress(ipAddress);
		pd.setHostName(hostName);
		Date date1 = new Date();
		pd.setLoginTime(date1);
		pd.setPunchStatus(PunchingDetail.Status.LOGGEDIN);

		
		try
		{
			this.create(pd);
			
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
		
		SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
		return formater.format(date1);
	}

	@Override
	public void create(PunchingDetail pd)
	{
		System.out.println("create");
		System.out.println(pd);
		PunchingDetail pd1 = punching.save(pd);
		System.out.println(pd1);
	}

	@Override
	public List<PunchingDetail> getLast30DaysDetails(String username)
	{
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -30);
		Date logindate = c.getTime();
		System.out.println(logindate);
		List<PunchingDetail> punchingDetails = punching.getLast30DaysDetails(username, logindate);
		return punchingDetails;
	}

	@Override
	public PunchingDetail read(String username)
	{
		System.out.println("in read");
		Date workingDate = DateUtil.getWorkingDate();
		System.out.println(username);
		System.out.println("in read working date" + workingDate);
		PunchingDetail punchingDetail1 = punching.read(username, workingDate);

		return punchingDetail1;
	}

	@Override
	public PunchingDetail getCurrentDetails(String username)
	{
		System.out.println("in getcurrentdetails");
		PunchingDetail punchingdetails = this.read(username);
		return punchingdetails;

	}

	@Override
	public PunchingDetail getPunchingDetails(String username, Date date)
	{
		
		PunchingDetail punchingDetail1 = punching.read(username, date);
		return punchingDetail1;

	}

	@Override
	public String insertBreakStartTime(String username)
	{

		return insertBreakTime(username, "OUT");

	}

	@Override
	public String insertBreakStopTime(String username)
	{

		return insertBreakTime(username, "IN");
	}

	public String insertBreakTime(String username, String inOut) 
	{

		PunchingDetail pd = this.read(username);

		System.out.println("........." + pd.getUsername());
		String breakTimeStr = pd.getBreakTime();

		if (inOut.equals("IN")) 
		{
			if (pd.getPunchStatus() != PunchingDetail.Status.BREAKOUT)
			{
				return "";
			}
		} 
		else if (inOut.equals("OUT")) 
		{
			if (pd.getPunchStatus() != PunchingDetail.Status.LOGGEDIN)
			{
				return "";
			}
		}

		if (breakTimeStr == null)
		{
			
			breakTimeStr = "";
		}

		SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
		breakTimeStr += formater.format(new Date()) + "-(" + inOut + "), ";

		if (inOut.equals("IN"))
		{
			pd.setPunchStatus(PunchingDetail.Status.LOGGEDIN);
		} 
		else if (inOut.equals("OUT")) 
		{
			pd.setPunchStatus(PunchingDetail.Status.BREAKOUT);
		}

		pd.setBreakTime(breakTimeStr);

		try 
		{
			this.create(pd);
			
		} 
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println();
		return breakTimeStr;
	}

	@Override
	public String insertLogoutTime(String username)
	{

		// Date workingDate = DateUtil.getWorkingDate();
		PunchingDetail pd = this.read(username);
		Date date1 = new Date();
		System.out.println(date1);
		pd.setLogoutTime(date1);
		pd.calculateTotalHours();

		long totalHrs = pd.getTotalHours().getTime() + TimeZone.getDefault().getRawOffset();
		if (totalHrs < 8 * 60 * 60 * 1000) 
		{
			pd.setPunchStatus(PunchingDetail.Status.INCOMPLETE);
			List<String> cclist = new ArrayList<String>();
			username = username + "@monovative.com";
			// MailService.sendMail(username, "you have incomplete time", "you have Not
			// Complete 8 Hours" + "\n\n\n\n\n\n\n" + "This is auto generated mail, please
			// dont reply to this mail", cclist);
		} 
		else
		{
			pd.setPunchStatus(PunchingDetail.Status.COMPLETE);
		}

		try 
		{
			this.create(pd);
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}

		SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
		return formater.format(date1);
	}

	public PunchingDetail getUserPunching(long id)
	{
		PunchingDetail pd = punching.findOne(id);
		return pd;
	}

	@Override
	public boolean isPunching(String username, Date punchdate) 
	{
		System.out.println(username);
		System.out.println(punchdate);
		PunchingDetail pd = punching.isPunching(username, punchdate);

		if (pd == null)
		{
			return false;
		}
		return true;

	}

	@Override
	public long getLastID()
	{
		long id = punching.getLastID();
		return id;

	}

	@Override
	public Page<PunchingDetail> findAll(Pageable p, String punchstatus, String generalSearch,String username) 
	{

		Page<PunchingDetail> pd = null;
		DecimalFormat formatter = new DecimalFormat("00");
//		String monthArray3[] = { "0", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nev",
//				"dec" };
//		String monthArray2[] = { "0", "ja", "fe", "ma", "ap", "ma", "ju", "ju", "au", "se", "oc", "ne", "de" };
//		String monthArray1[] = { "0", "j", "f", "m", "a", "m", "j", "j", "a", "s", "o", "n", "d" };

		String day = "";
		String month = "";
		String year = "";
		String x[];

		 	Calendar c = Calendar.getInstance();
	        c.add(Calendar.DAY_OF_YEAR, -30);
	        Date logindate = c.getTime();

		
		if(generalSearch!=null) 
		{
			
			if (StringUtils.countOccurrencesOf(generalSearch, "-") == 1) 
			{
			
			
				x = generalSearch.split("-");
				day = x[0].trim();
				try {
	//				if(x[1].trim().length() == 1) {
	//					month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
	//				} else if(x[1].trim().length() == 2) {
	//					month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
	//				} else if(x[1].trim().length() == 3) {
	//					month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
	//				} 
					month = x[1].trim();
				} catch (Exception e) {
					// /
				}

			} 
			else if (StringUtils.countOccurrencesOf(generalSearch, "-") == 2)
			{
				x = generalSearch.split("-");
				
				try {
					day = x[0].trim();
					month = x[1].trim();
					year = x[2].trim();
	//				if(x[1].trim().length() == 1) {
	//					month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
	//				} else if(x[1].trim().length() == 2) {
	//					month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
	//				} else if(x[1].trim().length() == 3) {
	//					month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
	//				} 
					
				} catch (Exception e) {
					//date = generalSearch;
				}

			} 
			else
			{
				day = generalSearch;
		
			}
		
		//System.err.println(Username+"-"+ p+"-"+generalSearch+"-"+day+"-"+month+"-"+year);
		pd = punching.findBySearchTerm(username, p, generalSearch, day,month,year);
		
		
		}
		else if(punchstatus!=null)
		{
			pd=punching.findBySearchTerm(username, p,punchstatus);
		}
		else 
		{
			pd=punching.findBySearchTerm(username, p,logindate);
		}

//		 if(punchstatus==null)
//		 {
//		 pd=punching.findBySearchTerm(Username, p);
//		 }
//		 else
//		 {
//		 pd=punching.findBySearchTerm(Username, p,punchstatus);
//		 }
//		

		List<PunchingDetail> listpd = pd.getContent();
		System.out.println("length:" + listpd.size());
		for (PunchingDetail punchingDetail : listpd) 
		{

			System.out.println(punchingDetail.getUsername());
			System.out.println(punchingDetail.getLogindate());
			System.out.println(punchingDetail.getComments());
			System.out.println(punchingDetail.getCommentsEmployee());	
		}

		System.out.println(pd.getTotalElements());
		System.out.println(pd.getTotalPages());
		System.out.println(pd.getContent());
		System.out.println(pd);
		return pd;

	}

	// pagination related
	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title, String punchstatus)
	{
		String sort1 = "desc";
		String title1 = "logindate";

		if (sort == null || title == null) 
		{
			sort = sort1;
			title = title1;
		}
		Pageable p = new PageRequest(pageno - 1, perpage,Sort.Direction.fromString(sort),title);

		System.out.println(p);

		return p;
	}

	@Override
	public Page<PunchingDetail> getAllPunchingRecords(int pageno, int perpage, String sort, String title,String punchstatus, String generalSearch,String username)
	{

		Pageable p = createPageRequest(pageno, perpage, sort, title, punchstatus);

		Page<PunchingDetail> pd = findAll(p, punchstatus, generalSearch,username);

		return pd;

	}

	@Override
	public void deleteRecord(long id)
	{
		PunchingDetail pd = getUserPunching(id);
		if (pd != null) 
		{
			punching.delete(pd);
		}

	}


	@Override
	public Page<PunchingDetail> getAdminPunchingRecords(int pageno, int perpage, String sort, String title,String punchstatus, String generalSearch) 
	{
		
		Pageable p = createPageRequest(pageno, perpage, sort, title, punchstatus);
		
		Page<PunchingDetail> pd = null;
		DecimalFormat formatter = new DecimalFormat("00");
//		String monthArray3[] = { "0", "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nev",
//				"dec" };
//		String monthArray2[] = { "0", "ja", "fe", "ma", "ap", "ma", "ju", "ju", "au", "se", "oc", "ne", "de" };
//		String monthArray1[] = { "0", "j", "f", "m", "a", "m", "j", "j", "a", "s", "o", "n", "d" };

		String day = "";
		String month = "";
		String year = "";
		String x[];

		 	Calendar c = Calendar.getInstance();
	        c.add(Calendar.DAY_OF_YEAR, -30);
	        Date logindate = c.getTime();

	      
	        if(generalSearch != null && punchstatus != null)
			{
				if (StringUtils.countOccurrencesOf(generalSearch, "-") == 1)
				{
				
						x = generalSearch.split("-");
						day = x[0].trim();
						try {
		//					if(x[1].trim().length() == 1) {
		//						month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
		//					} else if(x[1].trim().length() == 2) {
		//						month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
		//					} else if(x[1].trim().length() == 3) {
		//						month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
		//					} 
							month = x[1].trim();
						} catch (Exception e) {
							// /
						}

				} 
				else if (StringUtils.countOccurrencesOf(generalSearch, "-") == 2)
				{
				
						x = generalSearch.split("-");
						
						try {
							day = x[0].trim();
							month = x[1].trim();
							year = x[2].trim();
		//					if(x[1].trim().length() == 1) {
		//						month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
		//					} else if(x[1].trim().length() == 2) {
		//						month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
		//					} else if(x[1].trim().length() == 3) {
		//						month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
		//					} 
							
						} catch (Exception e) {
							//date = generalSearch;
						}

				} 
				else
				{
						day=generalSearch;
				}
				
				pd=punching.punchingSearchAndStat(logindate, p, generalSearch, day, month, year, punchstatus);
			}
	        else if(generalSearch!=null) 
	        {
		        if (StringUtils.countOccurrencesOf(generalSearch, "-") == 1)
		        {
		
					x = generalSearch.split("-");
					day = x[0].trim();
					try 
					{
		//				if(x[1].trim().length() == 1) {
		//					month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
		//				} else if(x[1].trim().length() == 2) {
		//					month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
		//				} else if(x[1].trim().length() == 3) {
		//					month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
		//				} 
						month = x[1].trim();
					} 
					catch (Exception e) 
					{
						// /
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
		//				if(x[1].trim().length() == 1) {
		//					month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
		//				} else if(x[1].trim().length() == 2) {
		//					month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
		//				} else if(x[1].trim().length() == 3) {
		//					month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
		//				} 
						
					} 
					catch (Exception e) 
					{
						//date = generalSearch;
					}

	        	} 
		        else 
		        {
		        		day=generalSearch;
		        }
		        
		     pd = punching.punchingSearch(logindate, p, generalSearch, day,month,year);

		}
		else if(punchstatus!=null)
		{
			 pd=punching.punchingRecordWithStatus(logindate, p, punchstatus);
		}
		
		else
		{
			 pd=punching.punchingRecord(logindate,p);
		}

		List<PunchingDetail> listpd = pd.getContent();
		System.out.println("length:" + listpd.size());
		for (PunchingDetail punchingDetail : listpd)
		{

			System.out.println(punchingDetail.getUsername());
			System.out.println(punchingDetail.getLogindate());
		}

		System.out.println(pd.getTotalElements());
		System.out.println(pd.getTotalPages());
		System.out.println(pd.getContent());
		System.out.println(pd);
		
		return pd;

		
	}

	//Admin Methods
	
	@Override
	public void generatePunchingReports(String filePath, Date fromDate, Date toDate)
	{
		
		
		  File f = new File(filePath);
	        if (f.exists()) 
	        {
	            f.delete();
	        }


	        List<User> userList = us.getActiveUserList("Activate");
	        HashSet<User> userList1 = new HashSet<User>(userList);
	        ArrayList<User> userlist = new ArrayList<User>(userList1);
	        Collections.sort(userlist, new Comparator<User>()
	        {
	            public int compare(User v1, User v2) 
	            {
	                return v1.getUsername().compareTo(v2.getUsername());
	            }
	        });

	        FileWriter fw = null;
	        List<Date> uniqueDateRecords = new ArrayList<Date>();
	        int totalCount;
	        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	        String fromdate1 = df.format(fromDate);
	        String todate1 = df.format(toDate);

	        //int workingDay = DateUtil.getWorkingdays(fromdate1, todate1);
	        //Remove Official Days From Working Days
	        //fromDate = DateUtil.stringToDate(fromdate1);
	        //toDate = DateUtil.stringToDate(todate1);
	        int workingDays = DateUtil.getWorkingdays(fromdate1, todate1);
	        Integer i = 0;
	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(fromDate);
	        cal1.add(Calendar.DATE, -1);
	        while (cal1.getTime().before(toDate)) 
	        {
	            cal1.add(Calendar.DATE, 1);
	            Date date1 = cal1.getTime();
	            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MMM-yyyy");
	            String date2 = DATE_FORMAT.format(date1);

	      
	            List<OfficialLeaveDetail> leavelist = offc.readAll();
	            HashSet<OfficialLeaveDetail> LeaveList = new HashSet<OfficialLeaveDetail>(leavelist);
	            ArrayList<OfficialLeaveDetail> leavelist1 = new ArrayList<OfficialLeaveDetail>(LeaveList);

	            for (OfficialLeaveDetail ofc : leavelist1)
	            {
	                Date leavedate = ofc.getLeaveDate();
	                String date = DATE_FORMAT.format(leavedate);
	                //Date leavedate1 = DateUtil.stringToDate(leavedate);
	                Calendar fromDate1 = stringToCalDate(date);
	                if (fromDate1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) 
	                {
	                    if (date.equals(date2))
	                    {
	                        i = i + 1;
	                    }
	                }

	            }

	        }
	        workingDays = workingDays - i;
	        String dayStr = Integer.toString(workingDays);
	      
	        List<PunchingDetail> punchingDetails = punching.getPunchingRecords(fromDate, toDate);
	        for (PunchingDetail pds : punchingDetails)
	        {
	            Date logindate = pds.getLogindate();
	            //System.out.println(logindate);
	            if (!uniqueDateRecords.contains(logindate)) 
	            {
	                uniqueDateRecords.add(logindate);
	            }
	        }

	        try
	        {
	            fw = new FileWriter(new File(filePath), true);
	            fw.append("Working Days");
	            fw.append("");
	            fw.append(',');

	            for (User user1 : userlist) 
	            {
	                fw.append(',');
	                fw.append(dayStr);

	            }

	            List<Integer> totalPresentDayslist = new ArrayList<Integer>();
	            fw.append('\n');
	            fw.append("Days Present");
	            fw.append("");
	            fw.append(',');
	            for (User user1 : userlist) {
	                int presentdays = 0;
	                String username = user1.getUsername();
	                String username1[] = username.split("@");
	                String username2 = username1[0];
	                //String hql2 = "SELECT SUM(pd.totalHours) FROM PunchingDetail AS pd WHERE pd.username=:username" ;
	                long time = 0;
	                for (Date logindate : uniqueDateRecords)
	                {
	                    Time totalhours = punching.getRecordByHours(username2, logindate);

	                    if (totalhours != null) 
	                    {
	                        try 
	                        {
	                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	                            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	                            String inputString = totalhours.toString(); //"01:30:00";
	                            Date date = sdf.parse(inputString);// .parse("1970-01-01" + inputString);
	                            time = date.getTime() + time;

	                            String logindate1 = df.format(logindate);
	                            Calendar cal = DateUtil.stringToCalDate(logindate1);
	                            //TEMP
	                            //if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
	                            ++presentdays;
	                            // }

	                        } 
	                        catch (Exception e) 
	                        {
	                            System.out.println(e.getMessage());
	                        }

	                    }
	                }

	                fw.append(',');
	                String presentStr = Integer.toString(presentdays);
	                totalPresentDayslist.add(presentdays);
	                fw.append(presentStr);

	            }
	            fw.append('\n');
	            fw.append("Absent/Leave");
	            fw.append("");
	            fw.append(',');

	            int absentdays1 = 0;
	            for (int j = 0; j < totalPresentDayslist.size(); j++) 
	            {
	                absentdays1 = workingDays - totalPresentDayslist.get(j);
	                System.out.println("");
	                fw.append(',');
	                String absentStr = Integer.toString(absentdays1);
	                fw.append(absentStr);

	            }

	            fw.append('\n');
	            fw.append(' ');
	            fw.append("\n");
	            fw.append("Date/Name");
	            fw.append(',');
	            fw.append("Day");
	            fw.append(',');

	            for (User user1 : userlist)
	            {
	                String username11 = user1.getUsername();
	                String username1[] = username11.split("@");
	                String username2 = username1[0];
	                fw.append(username2);
	                fw.append(',');
	            }

	            for (Date logindate : uniqueDateRecords)
	            {
	                fw.append('\n');
	                DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
	                String strDate = dateFormat.format(logindate);

	                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
	                String day = outFormat.format(logindate);
	                // System.out.println(day);

	                fw.append(strDate);
	                fw.append(',');
	                fw.append(day);

	                for (User user1 : userlist)
	                {
	                    String username = user1.getUsername();
	                    String username1[] = username.split("@");
	                    String username2 = username1[0];

	                    fw.append(',');
	                    
	                 
	                    Time totalhours = punching.getRecordByHours(username2, logindate);


	                    if (totalhours == null) 
	                    {
	                        fw.append("");
	                    } 
	                    else 
	                    {
	                        String timeStr = totalhours.toString();
	                        fw.append(timeStr);
	                    }
	                }
	            }

	            fw.append("\n");
	            fw.append(" ");
	            fw.append("\n");
	            fw.append("Grand Total");
	            fw.append(',');
	            fw.append("");
	            for (User user1 : userlist) 
	            {
	                int absentdays = 0;
	                String username = user1.getUsername();
	                String username1[] = username.split("@");
	                String username2 = username1[0];
	                //String hql2 = "SELECT SUM(pd.totalHours) FROM PunchingDetail AS pd WHERE pd.username=:username" ;
	                long time = 0;
	                for (Date logindate : uniqueDateRecords) 
	                {
	                    Time totalhours = punching.getRecordByHours(username2, logindate);

	                    if (totalhours != null) {
	                        try 
	                        {
	                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	                            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	                            String inputString = totalhours.toString(); //"01:30:00";
	                            Date date = sdf.parse(inputString);// .parse("1970-01-01" + inputString);
	                            time = date.getTime() + time;

	                        }
	                        catch (Exception e)
	                        {
	                            System.out.println(e.getMessage());
	                        }

	                    }
	                }
	                String timeStr = null;
	                long totalSecs = time / 1000;
	                long hours = (totalSecs / 3600);
	                long mins = (totalSecs / 60) % 60;
	                long secs = totalSecs % 60;
	                timeStr = hours + ":" + mins + ":00";

	                fw.append(',');
	                fw.append(timeStr);
	            }

	            fw.append('\n');
	            fw.append('\n');
	            fw.append('\n');
	            fw.append("Working Days Doesn't Include Public Holidays");
	            //}
	        } 
	        catch (Exception e) 
	        {
	            System.out.println(e.getMessage());
	        } 
	        finally 
	        {
	            try 
	            {
	                fw.close();
	            } 
	            catch (IOException ex)
	            {
	                //Logger.getLogger(PunchingDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }

	}
	
	@Override
	public void generateMonthlyReport(String filepath1, int month)
	{
		File f = new File(filepath1);
        if (f.exists())
        {
            f.delete();
        }


        List<User> userList = us.getActiveUserList("Activate");
        HashSet<User> userList1 = new HashSet<User>(userList);
        ArrayList<User> userlist = new ArrayList<User>(userList1);
        Collections.sort(userlist, new Comparator<User>() 
        {
            public int compare(User v1, User v2) 
            {
                return v1.getUsername().compareTo(v2.getUsername());
            }
        });

        FileWriter fw = null;

        try {

            fw = new FileWriter(filepath1, true);
            fw.append("Serial No.");
            fw.append(',');
//            fw.append(',');
            fw.append("Name");
            fw.append(',');
            fw.append(',');
            fw.append("Total Working Days");
            fw.append(',');
            fw.append(',');
            fw.append("LWP");
            fw.append(',');
//            fw.append(',');
            fw.append("Basic");
            fw.append(',');
//            fw.append(',');
            fw.append("HRA");
            fw.append(',');
//            fw.append(',');
            fw.append("Conveyance");
            fw.append(',');
            fw.append(',');
            fw.append("Spl_Allow");
            fw.append(',');
            fw.append(',');
            fw.append("Oth_Allow");
            fw.append(',');
            fw.append(',');
            fw.append("Gross");
            fw.append(',');
//            fw.append(',');
            fw.append("Pf_Employer");
            fw.append(',');
            fw.append(',');
            fw.append("Esi_Employer");
            fw.append(',');
            fw.append(',');
            fw.append("Bonus");
            fw.append(',');
//            fw.append(',');
            fw.append("Gratuity");
            fw.append(',');
//            fw.append(',');
            fw.append("EmployerContri");
            fw.append(',');
            fw.append(',');
            fw.append("Pf_Employee");
            fw.append(',');
            fw.append(',');
            fw.append("Esi_Employee");
            fw.append(',');
            fw.append(',');
            fw.append("Prof_Tax");
            fw.append(',');
//            fw.append(',');
            fw.append("EmployeeContri");
            fw.append(',');
            fw.append(',');
            fw.append("Net");
            fw.append(',');
//            fw.append(',');
            fw.append("CTC");

            //Start print serial no.
            int serial = 1;

            //Start of userlist loop
            for (User user1 : userlist)
            {
                String username = user1.getUsername();
                String username1[] = username.split("@");
                String username2 = username1[0];
               
                
                if(username2.equals("admin"))
                {
                	
                }
                else
                {
                
                String serialStr = Integer.toString(serial);
                fw.append("\n");
                fw.append("\n");
                fw.append(serialStr);

                fw.append(",");
                fw.append(username2);

                int months = getTotalWorkingdays(month);
                String monthStr = Integer.toString(months);

                //print totalworking days
                fw.append(",");
                fw.append(",");
                fw.append(monthStr);

                //code for print month wise lwp
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

                if (month == Calendar.JANUARY || month == Calendar.MARCH || month == Calendar.MAY || month == Calendar.JULY || month == Calendar.AUGUST || month == Calendar.OCTOBER || month == Calendar.DECEMBER) 
                {

                    calendar.set(year, month, 1);
                    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int count = 0;
                    int lwp = 0;
                    String lwpStr="";
                    for (int day = 0; day < daysInMonth; day++) 
                    {

                        calendar.set(Calendar.DAY_OF_MONTH, day + 1);
                        String dates = df.format(calendar.getTime());

                        List<String> myList = new ArrayList<String>(Arrays.asList(dates));

                        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek == Calendar.SUNDAY)
                        {
                            count++;
                            String sunday = df.format(calendar.getTime());

                            List<String> myList1 = new ArrayList<String>(Arrays.asList(sunday));
                            myList.removeAll(myList1);

                        }

//                        for (int a = 0; a <= userlist.size(); a++) {
                        for (int a = 0; a <= daysInMonth; a++) 
                        {

                            if (dayOfWeek == Calendar.SATURDAY)
                            {
                                calendar.set(Calendar.WEEK_OF_MONTH, 2);
                                String sat = df.format(calendar.getTime());
                                //System.out.println("Saturday:-" + sat);
                                List<String> myList2 = new ArrayList<String>(Arrays.asList(sat));
                                // System.out.println("SAT: " + myList2);
                                myList.removeAll(myList2);

                            }
                        }

//                        for (int b = 0; b <= userlist.size(); b++) {
                        for (int b = 0; b <= daysInMonth; b++) 
                        {
                            if (dayOfWeek == Calendar.SATURDAY) 
                            {
                                calendar.set(Calendar.WEEK_OF_MONTH, 4);
                                String sat1 = df.format(calendar.getTime());
                                // System.out.println("Saturday:-" + sat1);
                                List<String> myList1 = new ArrayList<String>(Arrays.asList(sat1));
                                myList.removeAll(myList1);

                            }
                        }

//                        for (int j = 0; j <= userlist.size(); j++) {
                        for (int j = 0; j <= daysInMonth; j++) 
                        {
           
                            List<OfficialLeaveDetail> l = getMonthHolidayDate(month);

                            for (int i = 0; i < l.size(); i++) 
                            {
                                String l1 = df.format(l.get(i));
                                // System.out.println("Holiday:-"+l1);
                                List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                                myList.removeAll(mylist1);

                            }

                        }
                     
                        UserLeaveDetail userleavedetail = us.getUser(username2).getLeavedetail();
                        if(userleavedetail == null)
                        {
                        	lwp=0;
                        }
                        else 
                        {
                        	long leaveId = userleavedetail.getLeaveid();
                            
                            
                            List date1 = lr.getFromToDate(month + 1, leaveId);

                            Iterator it = date1.iterator();

                            while (it.hasNext())
                            {
                                Object[] myResult = (Object[]) it.next();

                                Date fromdate = (Date) myResult[0];
                                Date todate = (Date) myResult[1];

                                List<Date> dates1 = new ArrayList<Date>();

                                long interval = 24 * 1000 * 60 * 60;
                                long endTime = todate.getTime();
                                long curTime = fromdate.getTime();
                                while (curTime <= endTime)
                                {
                                    dates1.add(new Date(curTime));
                                    curTime += interval;
                                }
                                for (int i = 0; i < dates1.size(); i++) 
                                {
                                    Date lDate = (Date) dates1.get(i);
                                    // DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                                    String leavedate = df.format(lDate);
                                    List<String> leavedateList = new ArrayList<String>(Arrays.asList(leavedate));

                                    myList.removeAll(leavedateList);

                                }

                                // System.out.println("From Date Record:-" + fromdate + "========" + "To date:-" + todate);
                            }

                            List<PunchingDetail> logindate = punching.getLoginDateList(username2,month + 1);

                            for (int i = 0; i < logindate.size(); i++) {
                                String l1 = df.format(logindate.get(i));
                                List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                                //System.out.println("List of LoginDate:-"+mylist1);
                                myList.removeAll(mylist1);

                            }

                            if (!myList.isEmpty()) 
                            {
                                lwp++;
                            }

                        }
                                                //System.out.println(myList);
                    }
                    System.out.println(lwp);
                    lwpStr = Integer.toString(lwp);

                    fw.append(",");
                    fw.append(",");
//                    fw.append(",");
                    fw.append(lwpStr);

                } else if (month == Calendar.APRIL || month == Calendar.JUNE || month == Calendar.SEPTEMBER || month == Calendar.NOVEMBER)
                {

                    calendar.set(year, month, 1);
                    int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    int count = 0;
                    int lwp = 0;

                    for (int day = 0; day < daysInMonth; day++) {

                        calendar.set(Calendar.DAY_OF_MONTH, day + 1);
                        String dates = df.format(calendar.getTime());

                        List<String> myList = new ArrayList<String>(Arrays.asList(dates));
                        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                        if (dayOfWeek == Calendar.SUNDAY)
                        {
                            count++;
                            String sunday = df.format(calendar.getTime());

                            List<String> myList1 = new ArrayList<String>(Arrays.asList(sunday));
                            myList.removeAll(myList1);
                        }
//                        for (int a = 0; a <= userlist.size(); a++) {
                        for (int a = 0; a <= daysInMonth; a++)
                        {
                            if (dayOfWeek == Calendar.SATURDAY)
                            {
                                calendar.set(Calendar.WEEK_OF_MONTH, 2);
                                String sat = df.format(calendar.getTime());
                                //System.out.println("Saturday:-" + sat);
                                List<String> myList2 = new ArrayList<String>(Arrays.asList(sat));
                                // System.out.println("SAT: " + myList2);
                                myList.removeAll(myList2);
                            }
                        }

//                        for (int b = 0; b <= userlist.size(); b++) {
                        for (int b = 0; b <= daysInMonth; b++)
                        {
                            if (dayOfWeek == Calendar.SATURDAY)
                            {
                                calendar.set(Calendar.WEEK_OF_MONTH, 4);
                                String sat1 = df.format(calendar.getTime());
                                // System.out.println("Saturday:-" + sat1);
                                List<String> myList1 = new ArrayList<String>(Arrays.asList(sat1));
                                myList.removeAll(myList1);
                            }
                        }

//                        for (int j = 0; j <= userlist.size(); j++) {
                        for (int j = 0; j <= daysInMonth; j++)
                        {
                    
                            List<OfficialLeaveDetail> l = getMonthHolidayDate(month);

                            for (int i = 0; i < l.size(); i++) 
                            {
                                String l1 = df.format(l.get(i));
                                // System.out.println("Holiday:-"+l1);
                                List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                                myList.removeAll(mylist1);
                            }

                        }
      
                        UserLeaveDetail userleavedetail = us.getUser(username2).getLeavedetail();
                        long leaveId = userleavedetail.getLeaveid();


                        List date1 = lr.getFromToDate(month + 1, leaveId);

                        Iterator it = date1.iterator();

                        while (it.hasNext())
                        {
                            Object[] myResult = (Object[]) it.next();

                            Date fromdate = (Date) myResult[0];
                            Date todate = (Date) myResult[1];

                            List<Date> dates1 = new ArrayList<Date>();

                            long interval = 24 * 1000 * 60 * 60;
                            long endTime = todate.getTime();
                            long curTime = fromdate.getTime();
                            while (curTime <= endTime) 
                            {
                                dates1.add(new Date(curTime));
                                curTime += interval;
                            }
                            for (int i = 0; i < dates1.size(); i++)
                            {
                                Date lDate = (Date) dates1.get(i);
                                // DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                                String leavedate = df.format(lDate);
                                List<String> leavedateList = new ArrayList<String>(Arrays.asList(leavedate));

                                myList.removeAll(leavedateList);

                                //System.out.println("LeaveList" + leavedateList);
                                // return leavedateList;
                            }

                            // System.out.println("From Date Record:-" + fromdate + "========" + "To date:-" + todate);
                        }

                        
                        List<PunchingDetail> logindate = punching.getLoginDateList(username2, month+1);

                        for (int i = 0; i < logindate.size(); i++)
                        {
                            String l1 = df.format(logindate.get(i));
                            List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                            //System.out.println("List of LoginDate:-"+mylist1);
                            myList.removeAll(mylist1);
                        }

                        if (!myList.isEmpty())
                        {
                            lwp++;
                        }
                    }
                    //System.out.println(lwp);
                    //System.out.println("Logindate:-"+logindate);
                    //System.out.println(myList);
                    String lwpStr = Integer.toString(lwp);

                    fw.append(",");
                    fw.append(",");
//                    fw.append(",");
                    fw.append(lwpStr);

//            }
                } 
                else if (month == Calendar.FEBRUARY)
                {

                    if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) 
                    {

                        calendar.set(year, month, 1);
                        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                        int count = 0;
                        int lwp = 0;

                        for (int day = 0; day < daysInMonth; day++) 
                        {

                            calendar.set(Calendar.DAY_OF_MONTH, day + 1);
                            String dates = df.format(calendar.getTime());

                            List<String> myList = new ArrayList<String>(Arrays.asList(dates));
                            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek == Calendar.SUNDAY) 
                            {
                                count++;
                                String sunday = df.format(calendar.getTime());

                                List<String> myList1 = new ArrayList<String>(Arrays.asList(sunday));
                                myList.removeAll(myList1);
                            }
//                            for (int a = 0; a <= userlist.size(); a++) {
                            for (int a = 0; a <= daysInMonth; a++) 
                            {
                                if (dayOfWeek == Calendar.SATURDAY)
                                {
                                    calendar.set(Calendar.WEEK_OF_MONTH, 2);
                                    String sat = df.format(calendar.getTime());
                                    //System.out.println("Saturday:-" + sat);
                                    List<String> myList2 = new ArrayList<String>(Arrays.asList(sat));
                                    // System.out.println("SAT: " + myList2);
                                    myList.removeAll(myList2);
                                }
                            }

//                            for (int b = 0; b <= userlist.size(); b++) {
                            for (int b = 0; b <= daysInMonth; b++) 
                            {
                                if (dayOfWeek == Calendar.SATURDAY) 
                                {
                                    calendar.set(Calendar.WEEK_OF_MONTH, 4);
                                    String sat1 = df.format(calendar.getTime());
                                    // System.out.println("Saturday:-" + sat1);
                                    List<String> myList1 = new ArrayList<String>(Arrays.asList(sat1));
                                    myList.removeAll(myList1);
                                }
                            }

//                            for (int j = 0; j <= userlist.size(); j++) {
                            for (int j = 0; j <= daysInMonth; j++) 
                            {
                             
                                List<OfficialLeaveDetail> l = getMonthHolidayDate(month);

                                for (int i = 0; i < l.size(); i++) 
                                {
                                    String l1 = df.format(l.get(i));
                                    // System.out.println("Holiday:-"+l1);
                                    List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                                    myList.removeAll(mylist1);
                                }

                            }
                            UserLeaveDetail userleavedetail = us.getUser(username2).getLeavedetail();
                            long leaveId = userleavedetail.getLeaveid();

                            List date1 =lr.getFromToDate(month+1,leaveId);

                            Iterator it = date1.iterator();

                            while (it.hasNext()) 
                            {
                                Object[] myResult = (Object[]) it.next();

                                Date fromdate = (Date) myResult[0];
                                Date todate = (Date) myResult[1];

                                List<Date> dates1 = new ArrayList<Date>();

                                long interval = 24 * 1000 * 60 * 60;
                                long endTime = todate.getTime();
                                long curTime = fromdate.getTime();
                                while (curTime <= endTime)
                                {
                                    dates1.add(new Date(curTime));
                                    curTime += interval;
                                }
                                for (int i = 0; i < dates1.size(); i++)
                                {
                                    Date lDate = (Date) dates1.get(i);
                                    // DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                                    String leavedate = df.format(lDate);
                                    List<String> leavedateList = new ArrayList<String>(Arrays.asList(leavedate));

                                    myList.removeAll(leavedateList);

                                    //System.out.println("LeaveList" + leavedateList);
                                    // return leavedateList;
                                }

                                // System.out.println("From Date Record:-" + fromdate + "========" + "To date:-" + todate);
                            }

                            List<PunchingDetail> logindate = punching.getLoginDateList(username2, month+1);

                            for (int i = 0; i < logindate.size(); i++)
                            {
                                String l1 = df.format(logindate.get(i));
                                List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                                //System.out.println("List of LoginDate:-"+mylist1);
                                myList.removeAll(mylist1);
                            }
                            if (!myList.isEmpty()) 
                            {
                                lwp++;
                            }
                        }
                        String lwpStr = Integer.toString(lwp);

                        fw.append(",");
                        fw.append(",");
//                        fw.append(",");
                        fw.append(lwpStr);

//                }
                    } 
                    else 
                    {
                        calendar.set(year, month, 1);
                        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                        int count = 0;
                        int lwp = 0;

                        for (int day = 0; day < daysInMonth; day++)
                        {

                            calendar.set(Calendar.DAY_OF_MONTH, day + 1);
                            String dates = df.format(calendar.getTime());

                            List<String> myList = new ArrayList<String>(Arrays.asList(dates));
                            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                            if (dayOfWeek == Calendar.SUNDAY) 
                            {
                                count++;
                                String sunday = df.format(calendar.getTime());

                                List<String> myList1 = new ArrayList<String>(Arrays.asList(sunday));
                                myList.removeAll(myList1);
                            }
//                            for (int a = 0; a <= userlist.size(); a++) {
                            for (int a = 0; a <= daysInMonth; a++)
                            {
                                if (dayOfWeek == Calendar.SATURDAY)
                                {
                                    calendar.set(Calendar.WEEK_OF_MONTH, 2);
                                    String sat = df.format(calendar.getTime());
                                    //System.out.println("Saturday:-" + sat);
                                    List<String> myList2 = new ArrayList<String>(Arrays.asList(sat));
                                    // System.out.println("SAT: " + myList2);
                                    myList.removeAll(myList2);
                                }
                            }

//                            for (int b = 0; b <= userlist.size(); b++) {
                            for (int b = 0; b <= daysInMonth; b++) 
                            {
                                if (dayOfWeek == Calendar.SATURDAY) 
                                {
                                    calendar.set(Calendar.WEEK_OF_MONTH, 4);
                                    String sat1 = df.format(calendar.getTime());
                                    // System.out.println("Saturday:-" + sat1);
                                    List<String> myList1 = new ArrayList<String>(Arrays.asList(sat1));
                                    myList.removeAll(myList1);
                                }
                            }

//                            for (int j = 0; j <= userlist.size(); j++) {
                            for (int j = 0; j <= daysInMonth; j++)
                            {
                                
                                List<OfficialLeaveDetail> l = getMonthHolidayDate(month);

                                for (int i = 0; i < l.size(); i++) 
                                {
                                    String l1 = df.format(l.get(i));
                                    // System.out.println("Holiday:-"+l1);
                                    List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                                    myList.removeAll(mylist1);
                                }

                            }
                           
                            UserLeaveDetail userleavedetail = us.getUser(username2).getLeavedetail();
                            long leaveId = userleavedetail.getLeaveid();

                            List date1 = lr.getFromToDate(month+1, leaveId);

                            Iterator it = date1.iterator();

                            while (it.hasNext())
                            {
                                Object[] myResult = (Object[]) it.next();

                                Date fromdate = (Date) myResult[0];
                                Date todate = (Date) myResult[1];

                                List<Date> dates1 = new ArrayList<Date>();

                                long interval = 24 * 1000 * 60 * 60;
                                long endTime = todate.getTime();
                                long curTime = fromdate.getTime();
                                while (curTime <= endTime) 
                                {
                                    dates1.add(new Date(curTime));
                                    curTime += interval;
                                }
                                for (int i = 0; i < dates1.size(); i++)
                                {
                                    Date lDate = (Date) dates1.get(i);
                                    // DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                                    String leavedate = df.format(lDate);
                                    List<String> leavedateList = new ArrayList<String>(Arrays.asList(leavedate));

                                    myList.removeAll(leavedateList);

                                    //System.out.println("LeaveList" + leavedateList);
                                    // return leavedateList;
                                }

                                // System.out.println("From Date Record:-" + fromdate + "========" + "To date:-" + todate);
                            }

                            List<PunchingDetail> logindate = punching.getLoginDateList(username2,month+1);

                            for (int i = 0; i < logindate.size(); i++) 
                            {
                                String l1 = df.format(logindate.get(i));
                                List<String> mylist1 = new ArrayList<String>(Arrays.asList(l1));
                                //System.out.println("List of LoginDate:-"+mylist1);
                                myList.removeAll(mylist1);
                            }

                            if (!myList.isEmpty())
                            {
                                lwp++;
                            }
                        }

                        String lwpStr = Integer.toString(lwp);

                        fw.append(",");
                        fw.append(",");
//                        fw.append(",");
                        fw.append(lwpStr);

                    }
                    //end of else loop

                }
//                //end of else if feb loop 
                //End of month wise lwp 
                serial++;
                
                } 
            }

            //end of userlist loop
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        } 
        finally
        {
            try 
            {
                fw.close();
            } 
            catch (IOException ex)
            {
                ex.getMessage();
            }
        }
	}
	
	 public int getTotalWorkingdays(int month)
	 {

	        Calendar calendar = Calendar.getInstance();
	        int year = calendar.get(Calendar.YEAR);
	        // System.out.println(year);

	        if (month == Calendar.JANUARY || month == Calendar.MARCH || month == Calendar.MAY || month == Calendar.JULY || month == Calendar.AUGUST || month == Calendar.OCTOBER || month == Calendar.DECEMBER) 
	        {

	            calendar.set(year, month, 1);
	            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	            //System.out.println("Total days:-" + days);

	            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	            int count = 0;
	            for (int day = 1; day <= daysInMonth; day++) 
	            {
	                calendar.set(year, month, day);
	                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	                if (dayOfWeek == Calendar.SUNDAY) 
	                {

	                    count++;
	                    // Or do whatever you need to with the result.

	                    System.out.println();

	                }
	            }
	            // System.out.println("Sunday:-" + count);

	            int sat = 2;

	            //System.out.println("Saturday:-" + sat);
	            
	            int holidays = getMonthHoliday(month);

	            //System.out.println("Hoiliday:-" + holidays);
	            int workday = days - count - sat - holidays;

	            //System.out.println("Total Working days:-" + workday);
	            return workday;

	        }
	        else if (month == Calendar.APRIL || month == Calendar.JUNE || month == Calendar.SEPTEMBER || month == Calendar.NOVEMBER)
	        {

	            calendar.set(year, month, 1);
	            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	            //System.out.println("Total Days:-" + days);

	            int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	            int count = 0;
	            for (int day = 1; day <= daysInMonth; day++)
	            {
	                calendar.set(year, month, day);
	                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	                if (dayOfWeek == Calendar.SUNDAY) {
	                    count++;
	                    // Or do whatever you need to with the result.
	                }
	            }
	            //System.out.println("Sunday:-" + count);

	            int sat = 2;

	            //System.out.println("Saturday:-" + sat);
	            
	            int holidays = getMonthHoliday(month);

	            //System.out.println("Hoiliday:-" + holidays);
	            int workday = days - count - sat - holidays;
	            //System.out.println("Total workingdays:-" + workday);

	            return workday;

	        } 
	        else if (month == Calendar.FEBRUARY)
	        {

	            if ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0)) 
	            {

	                calendar.set(year, month, 1);
	                int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	                //System.out.println("Total days:-" + days);
	                //System.out.println("It is leap year");

	                int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	                int count = 0;
	                for (int day = 1; day <= daysInMonth; day++) 
	                {
	                    calendar.set(year, month, day);
	                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	                    if (dayOfWeek == Calendar.SUNDAY) 
	                    {
	                        count++;
	                        
	                        // Or do whatever you need to with the result.
	                    }
	                }
	                //System.out.println("Sunday:-" + count);

	                int sat = 2;

	                //System.out.println("Saturday:-" + sat);
	            
	                int holidays = getMonthHoliday(month);

	                //System.out.println("Hoiliday:-" + holidays);
	                int workday = days - count - sat - holidays;
	                //System.out.println("Total working days:-" + workday);

	                return workday;

	            } 
	            else 
	            {

	                calendar.set(year, month, 1);
	                int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	                //System.out.println("Total days:-" + days);
	                //System.out.println("Not a leap year");

	                int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	                int count = 0;
	                for (int day = 1; day <= daysInMonth; day++)
	                {
	                    calendar.set(year, month, day);
	                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	                    if (dayOfWeek == Calendar.SUNDAY)
	                    {
	                        count++;
	                        // Or do whatever you need to with the result.
	                    }
	                }

	                //System.out.println("Sunday:-" + count);
	                int sat = 2;
	                //System.out.println("Saturday:-" + sat);

	                int holidays = getMonthHoliday(month);

	                //System.out.println("Hoiliday:-" + holidays);
	                int workday = days - count - sat - holidays;
	                //System.out.println("Total working days:-" + (workday));

	                return workday;
	            }
	        }

	        // End Code for finding total working days month wise
	        return 0;
	    }
	
	 
	 public int getMonthHoliday(int month)
	 {
	        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

	        int holidaylist = 0;
	        try 
	        {
	          
	            List<OfficialLeaveDetail> leavelist = olr.getMonthHoliday(month+1);

	            return leavelist.size();

	        } 
	        catch (Exception e) 
	        {
	            System.out.println(e.getMessage());
	        }

	        return holidaylist;

	    }

	 public List<OfficialLeaveDetail> getMonthHolidayDate(int month) 
	 {

	        List<OfficialLeaveDetail> date = null;

	        try 
	        {
	          
	            List<OfficialLeaveDetail> holileavelist = olr.getMonthHolidayDate(month+1);
	            return holileavelist;
	          

	        } 
	        catch (Exception e)
	        {
	            System.out.println(e.getMessage());
	        }

	        return date;
	    }

	 //general methods 
}
