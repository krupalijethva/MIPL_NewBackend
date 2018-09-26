package com.monarch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.OfficialLeaveDetail;
import com.monarch.repository.OfficialLeaveRepository;

@Service
public class OfficialLeaveDetailServiceImpl implements OfficialLeaveDetailService{

	
	
	@Autowired
	OfficialLeaveRepository olr;

	
	public List<OfficialLeaveDetail> readAll()
	{
		return olr.findAll();
	}
	
	@Override
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title)
	{
		
		
		
		String sort1 = "asc";
		String title1 = "leaveDate";

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
	public Page<OfficialLeaveDetail> GetOfficialLeveList(int pageno,int perpage,String sort, String title)
	{

		Pageable p = createPageRequest(pageno, perpage, sort, title);
	
		Page<OfficialLeaveDetail> pd = null;

		pd=olr.getOfficialList(p);
		
		return pd;

	}


}
