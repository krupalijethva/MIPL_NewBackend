package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.EmployeeDetail;
import com.monarch.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	EmployeeRepository er;
	
	@Override
	public EmployeeDetail save(EmployeeDetail ed) {
		
		EmployeeDetail ed1=er.save(ed);
		return ed1;
	}

	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title) {
		String sort1 = "desc";
		String title1 = "user_id";

		if (sort == null || title == null) 
		{
			sort = sort1;
			title = title1;
		}
		Pageable p = new PageRequest(pageno - 1, perpage,Sort.Direction.fromString(sort),title);

		System.out.println(p);

		return p;
	}

	@Override
	public Page<EmployeeDetail> getEmployeeRecords(int pageno, int perpage, String sort, String title,String generalSearch) 
	{
		Pageable p = createPageRequest(pageno, perpage, sort, title);
		
		Page<EmployeeDetail> cd = null;
	
		if(generalSearch != null)
		{
			cd=er.getCandidateRecordsBySearch(p,generalSearch);

		}
	       
		else
		{
			cd=er.getCandidateRecords(p);
			
		}

		return cd;
	}

	@Override
	public EmployeeDetail readInfo(long id) {
		EmployeeDetail ed=er.findOne(id);
		return ed;
	}

}
