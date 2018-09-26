package com.monarch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.FolderAccessRequestDetail;
import com.monarch.domain.InternetAccessRequestDetail;
import com.monarch.repository.FolderAccessRequestRepository;

@Service
public class FolderAccessRequestServiceImpl implements FolderAccessRequestService {
	
	@Autowired
	FolderAccessRequestRepository folderRepo;

	@Override
	public FolderAccessRequestDetail save(FolderAccessRequestDetail fard) {
		FolderAccessRequestDetail folderObj=folderRepo.save(fard);
		return folderObj;
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
	public Page<FolderAccessRequestDetail> getFolderReqDetails(int pageno, int perpage, String sort, String title,String username,String status,String searchterm) {
		Pageable p=createPageRequest(pageno, perpage, sort,title);
		Page<FolderAccessRequestDetail> reqDetails=null;
		
		if(username == null)
		{
			
			if(status != null && searchterm != null)
    		{
    			reqDetails=folderRepo.findAllDetails(p,searchterm,status);
    		}
    		else if(status != null)
    		{
    			reqDetails=folderRepo.findAllByStatus(status, p);
    		}
    		else if(searchterm != null)
    		{
    			reqDetails=folderRepo.findAllDetails(p,searchterm);
    		}
    		else
    		{
			reqDetails=folderRepo.findAll(p);
    		}
		}
		else
		{
			reqDetails=folderRepo.findAll(username, p);
		}
    	return reqDetails;
	}



	@Override
	public FolderAccessRequestDetail readInfo(long id) {
		FolderAccessRequestDetail fard=folderRepo.findOne(id);
		return fard;
	}


}
