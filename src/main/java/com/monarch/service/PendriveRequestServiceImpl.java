package com.monarch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.LeaveRecords;
import com.monarch.domain.PendriveRequestDetail;
import com.monarch.repository.PendriveRequestRepository;

@Service
public class PendriveRequestServiceImpl implements PendriveReqestService {

	
	@Autowired
	PendriveRequestRepository pendriveRepo;
	
	@Override
	public PendriveRequestDetail save(PendriveRequestDetail prd) {
		PendriveRequestDetail pendriveObj=pendriveRepo.save(prd);
		return pendriveObj;
	}
	
	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title)
    {
    	String sort1="desc";
    	String title1="requestdate";
    	if(sort == null && title == null)
    	{
    		sort=sort1;
    		title=title1;
    	}
    	
    	Pageable p=new PageRequest(pageno-1,perpage,Sort.Direction.fromString(sort),title);
        
        return p;	
	}


	@Override
	public Page<PendriveRequestDetail> getPendriveReqDetails(int pageno, int perpage, String sort, String title,String username,String status,String searchterm) 
	{
		Pageable p=createPageRequest(pageno, perpage, sort,title);
    
    	Page<PendriveRequestDetail> reqDetails=null;
    	
    	if(username == null)
    	{
    		if(status != null && searchterm != null)
    		{
    			reqDetails=pendriveRepo.findAllDetails(p,searchterm,status);
    		}
    		else if(status != null)
    		{
    			reqDetails=pendriveRepo.findAllByStatus(status, p);
    		}
    		else if(searchterm != null)
    		{
    			reqDetails=pendriveRepo.findAllDetails(p,searchterm);
    		}
    		else
    		{
    			reqDetails=pendriveRepo.findAll(p);
    		}
    		
    	}
    	else
    	{
    		reqDetails=pendriveRepo.findAll(username, p);
    	}
    	return reqDetails;
	}

	@Override
	public PendriveRequestDetail readInfo(long id) {

		PendriveRequestDetail pendriveObj=pendriveRepo.findOne(id);
		return pendriveObj;
	}

	
	

}
