package com.monarch.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.monarch.domain.ExitEmployeeDetail;
import com.monarch.repository.ExitEmployeeRepository;

@Service
public class ExitEmployeeDetailServiceImpl implements ExitEmployeeDetailService  {
	
	@Autowired
	ExitEmployeeRepository eer;

	@Override
	public void save(ExitEmployeeDetail ee)
	{
		eer.save(ee);
		
	}

	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title) {
		String sort1 = "desc";
		String title1 = "applieddate";

		if (sort == null || title == null) 
		{
			sort = sort1;
			title = title1;
		}
		Pageable p = new PageRequest(pageno - 1, perpage,Sort.Direction.fromString(sort1),title1);

		System.out.println(p);

		return p;
		}

	@Override
	public Page<ExitEmployeeDetail> getExitEmployeeRecord(int pageno, int perpage, String sort, String title,String generalSearch) {
		Pageable p = createPageRequest(pageno, perpage, sort, title);

		
		Page<ExitEmployeeDetail> eed = null;
		
		if(generalSearch!=null) 
		{
			eed = eer.findBySearchTerm(p, generalSearch);
		
		}
	
		else 
		{
			eed=eer.findBySearchTerm(p);
		}

		return eed;

	}

	

	
	

}
