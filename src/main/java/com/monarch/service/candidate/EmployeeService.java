package com.monarch.service.candidate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.candidate.EmployeeDetail;

public interface EmployeeService {

	public EmployeeDetail save(EmployeeDetail ed);
	
	public EmployeeDetail readInfo(long id);
	
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	
	public Page<EmployeeDetail> getEmployeeRecords(int pageno, int perpage, String sort, String title,String generalSearch); 

	
}
