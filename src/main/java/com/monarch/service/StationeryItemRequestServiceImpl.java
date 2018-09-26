package com.monarch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.StationeryItemRequestDetail;
import com.monarch.repository.StationeryItemRequestRepository;

@Service
public class StationeryItemRequestServiceImpl implements StationeryItemRequestService {

	@Autowired
	StationeryItemRequestRepository itemrepo;
	
	@Override
	public StationeryItemRequestDetail save(StationeryItemRequestDetail sird) {

		StationeryItemRequestDetail sird1=itemrepo.save(sird);
		
		return sird1;
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
	public Page<StationeryItemRequestDetail> getItemReqDetails(int pageno, int perpage, String sort, String title,String username,String status,String searchterm) 
	{
		Pageable p=createPageRequest(pageno, perpage, sort,title);
		Page<StationeryItemRequestDetail> reqDetails=null;
		
		if(username == null)
		{
			
			if(status != null && searchterm != null)
    		{
    			reqDetails=itemrepo.findAllDetails(p,searchterm,status);
    		}
    		else if(status != null)
    		{
    			reqDetails=itemrepo.findAllByStatus(status, p);
    		}
    		else if(searchterm != null)
    		{
    			reqDetails=itemrepo.findAllDetails(p,searchterm);
    		}
    		else
    		{
    			reqDetails=itemrepo.findAll(p);
    		}
			
		}
		else
		{
			reqDetails=itemrepo.findAll(username, p);
		}
    	
    	
    	return reqDetails;
	}

	@Override
	public StationeryItemRequestDetail readInfo(long id) {
		StationeryItemRequestDetail sird=itemrepo.findOne(id);
		return sird;
	}

}
