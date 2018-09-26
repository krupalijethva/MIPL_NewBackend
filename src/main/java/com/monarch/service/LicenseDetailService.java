package com.monarch.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.LicenseDetail;

public interface LicenseDetailService {

	public long save(LicenseDetail ld);
	
	public LicenseDetail readInfo(long id);
	
	public String generateLicenseKey(long id, Date date1);
	
	public void delete(long id);
	
	 public String generateLicenseKey(long id);
	 
	 public Pageable createPageRequest(int pageno,int perpage,String sort,String title);   
	 public Page<LicenseDetail> getPendingLicenseDetails(int pageno,int perpage,String sort,String title,String generalSearch);
	 public Page<LicenseDetail> getPendingLicenseDetails(int pageno,int perpage,String sort,String title,String generalSearch,long licenseid);
	 
	 public Pageable createPageRequest2(int pageno,int perpage,String sort,String title);   
	 public Page<LicenseDetail> getAllLicenseDetails(int pageno,int perpage,String sort,String title,String generalSearch);
	  
	
}
