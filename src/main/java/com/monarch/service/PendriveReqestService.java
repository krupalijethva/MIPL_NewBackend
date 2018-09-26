package com.monarch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.PendriveRequestDetail;

public interface PendriveReqestService {
	
	public PendriveRequestDetail save(PendriveRequestDetail prd);
	public PendriveRequestDetail readInfo(long id);
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	public Page<PendriveRequestDetail> getPendriveReqDetails(int pageno, int perpage, String sort, String title,String Username,String status,String searchterm);
}
