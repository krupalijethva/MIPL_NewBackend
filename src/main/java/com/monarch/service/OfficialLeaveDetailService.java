package com.monarch.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.OfficialLeaveDetail;

public interface OfficialLeaveDetailService {
	
	public List<OfficialLeaveDetail> readAll();
	
	public Page<OfficialLeaveDetail> GetOfficialLeveList(int pageno,int perpage,String sort, String title);
	
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	
	


}
