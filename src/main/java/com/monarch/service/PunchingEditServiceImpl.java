package com.monarch.service;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.monarch.domain.PunchingDetail;
import com.monarch.domain.PunchingDetail.Status;
import com.monarch.domain.PunchingDetailEdit;
import com.monarch.repository.PunchingEditRepository;
import com.monarch.utils.DateUtil;

@Service("PunchingEditService")
public class PunchingEditServiceImpl implements PunchingEditService 
{

	@Autowired
	PunchingEditRepository pdr;	
	
	
		@Override
		public PunchingDetailEdit saveEditPunching(PunchingDetailEdit pde) 
		{
			PunchingDetailEdit pde1=pdr.save(pde);
			return pde1;
			
		}	 
	
		 @Override
		 public int getPendingRecords() 
		 {
	        PunchingDetail.Status punchStatus=(Status) PunchingDetail.Status.UNDER_REVIEW;
	        List<PunchingDetailEdit> punchingDetails = pdr.getPendingRecords(punchStatus);
	        
	        return punchingDetails.size();
		 }
	 
	
		 @Override
		 public PunchingDetailEdit read(String username)
		 {
	        Date workingDate = DateUtil.getWorkingDate();
	        PunchingDetailEdit punchingDetail =pdr.read(username, workingDate);
	        
	        return punchingDetail;
	     }

	     @Override
	     public List<PunchingDetailEdit> getLast7DaysDetails(String username) 
	     {
	        Calendar c = Calendar.getInstance();
	        c.add(Calendar.DAY_OF_YEAR, -7);
	        Date logindate = c.getTime();
	        List<PunchingDetailEdit> punchingDetails =pdr.getLast7DaysDetails(username, logindate);
	        
	        return punchingDetails;
	     }
 
	     @Override
	     public List<PunchingDetailEdit> readAll()
	     {
	    	 List<PunchingDetailEdit> pde=pdr.findAll();
	    	 
	    	 return pde;
	     }

	     @Override
	     public PunchingDetailEdit findById(long id)
	     {
	    	 PunchingDetailEdit pde= pdr.findOne(id);
	    	 return pde;
	     }
	     
	     //Modified Records Datatable
	     
	     public Page<PunchingDetailEdit> findAll(Pageable p,String generalSearch,String punchstatus)
	     {

	    	Page<PunchingDetailEdit> pd = null;
	 		DecimalFormat formatter = new DecimalFormat("00");

		 		Calendar c = Calendar.getInstance();
		        c.add(Calendar.DAY_OF_YEAR, -45);
		        Date logindate = c.getTime();
	          
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
			//						if(x[1].trim().length() == 1) {
			//							month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
			//						} else if(x[1].trim().length() == 2) {
			//							month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
			//						} else if(x[1].trim().length() == 3) {
			//							month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
			//						} 
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
								
								try {
									day = x[0].trim();
									month = x[1].trim();
									year = x[2].trim();
			//						if(x[1].trim().length() == 1) {
			//							month = "" + Arrays.asList(monthArray1).indexOf(x[1].trim());
			//						} else if(x[1].trim().length() == 2) {
			//							month = "" + Arrays.asList(monthArray2).indexOf(x[1].trim());
			//						} else if(x[1].trim().length() == 3) {
			//							month = "" + Arrays.asList(monthArray3).indexOf(x[1].trim());
			//						} 
									
								} catch (Exception e) {
									//date = generalSearch;
								}

						} 
						else 
						{
								day=generalSearch;
						}
					
					pd=pdr.punchingSearchAndStat(logindate, p, generalSearch, day, month, year, punchstatus);
				}
		        else if(generalSearch!=null) 
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
				
									try
									{
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
										
									} catch (Exception e) 
									{
										//date = generalSearch;
									}

		        		}
		        		else
		        		{
		        					day=generalSearch;
		        		}
		        		
		        	pd = pdr.punchingSearch(logindate, p, generalSearch, day,month,year);
			
			}
			else if(punchstatus!=null)
			{
				 pd=pdr.punchingRecord(logindate, p, punchstatus);
			}
			
			else 
			{
				 pd=pdr.punchingRecord(logindate,p);
			}
 		
	 		return pd;

	 	}

	 	// pagination related
	 	@Override
	 	public Pageable createPageRequest(int pageno, int perpage, String sort, String title) 
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
	 	public Page<PunchingDetailEdit> getModifiedRecords(int pageno, int perpage, String sort, String title,String generalSearch,String punchstatus)
	 	{

	 		Pageable p = createPageRequest(pageno, perpage, sort, title);

	 		Page<PunchingDetailEdit> pd = findAll(p, generalSearch,punchstatus);

	 		return pd;

	 	}
	 	
		 public void create(PunchingDetailEdit pde)    
		 {
			 pdr.save(pde);
		 }
	     
		
}
