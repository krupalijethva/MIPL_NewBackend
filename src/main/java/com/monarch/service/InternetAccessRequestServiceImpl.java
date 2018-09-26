package com.monarch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.InternetAccessRequestDetail;
import com.monarch.repository.InternetAccessRequestRepository;

@Service
public class InternetAccessRequestServiceImpl implements InternetAccessRequestService {

	@Autowired
	InternetAccessRequestRepository internetRepo;
	
	@Override
	public InternetAccessRequestDetail save(InternetAccessRequestDetail ird) {
		
		InternetAccessRequestDetail iard=internetRepo.save(ird);
		return iard;
	}

	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title) {
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
	public Page<InternetAccessRequestDetail> getInternetReqDetails(int pageno, int perpage, String sort, String title,
			String username,String status,String searchterm) {
		Pageable p=createPageRequest(pageno, perpage, sort,title);
		Page<InternetAccessRequestDetail> reqDetails=null;
		
		if(username == null)
		{
			
			if(status != null && searchterm != null)
    		{
    			reqDetails=internetRepo.findAllDetails(p,searchterm,status);
    		}
    		else if(status != null)
    		{
    			reqDetails=internetRepo.findAllByStatus(status, p);
    		}
    		else if(searchterm != null)
    		{
    			reqDetails=internetRepo.findAllDetails(p,searchterm);
    		}
    		else
    		{
    			reqDetails=internetRepo.findAll(p);
    		}
				
		}
		else
		{
				reqDetails=internetRepo.findAll(username, p);
		}
    	
    	
    	return reqDetails;
	}

	@Override
	public InternetAccessRequestDetail readInfo(long id) {
		
		InternetAccessRequestDetail internetObj=internetRepo.findOne(id);
		return internetObj;
	}

}
