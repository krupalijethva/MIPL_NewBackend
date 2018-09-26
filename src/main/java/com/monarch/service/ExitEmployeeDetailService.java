package com.monarch.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.ExitEmployeeDetail;

public interface ExitEmployeeDetailService {
	
	public void save(ExitEmployeeDetail ee);
	 public Pageable createPageRequest(int pageno,int perpage,String sort,String title);   
	   public Page<ExitEmployeeDetail> getExitEmployeeRecord(int pageno,int perpage,String sort,String title,String generalSearch);

	  

}
