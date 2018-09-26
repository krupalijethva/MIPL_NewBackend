package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.Candidatedetail;
import com.monarch.repository.CandidateDetailRepository;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	CandidateDetailRepository cdr;
	
	@Override
	public Candidatedetail save(Candidatedetail cd) 
	{
		Candidatedetail cd1=cdr.save(cd);	
		return cd1;
	}
	
	public Candidatedetail getbyID(long id)
	{
		Candidatedetail cd=cdr.findOne(id);
		return cd;
	}

	public Pageable createPageRequest(int pageno, int perpage, String sort, String title)
	{
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
	
	public Page<Candidatedetail> getCandidateRecords(int pageno, int perpage, String sort, String title,String generalSearch) 
	{
		
		Pageable p = createPageRequest(pageno, perpage, sort, title);
		
		Page<Candidatedetail> cd = null;
	
		if(generalSearch != null)
		{
			cd=cdr.getCandidateRecordsBySearch(p,generalSearch);

		}
	       
		else
		{
			cd=cdr.getCandidateRecords(p);
			
		}

		return cd;
	}

	@Override
	public void delete(long id) {


		cdr.delete(id);
		
	}
}
