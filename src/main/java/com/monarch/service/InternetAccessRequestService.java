package com.monarch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.InternetAccessRequestDetail;


public interface InternetAccessRequestService {
	
	public InternetAccessRequestDetail save(InternetAccessRequestDetail ird);
	public InternetAccessRequestDetail readInfo(long id);
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	public Page<InternetAccessRequestDetail> getInternetReqDetails(int pageno, int perpage, String sort, String title,String Username,String status,String searchterm);

}
