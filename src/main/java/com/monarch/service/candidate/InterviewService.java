package com.monarch.service.candidate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.monarch.domain.candidate.Candidatedetail;

public interface InterviewService {

	public Candidatedetail save(Candidatedetail cd);
	
	public Candidatedetail getbyID(long id);
	
	public void delete(long id);
	
	public Pageable createPageRequest(int pageno, int perpage, String sort, String title);
	
	public Page<Candidatedetail> getCandidateRecords(int pageno, int perpage, String sort, String title,String generalSearch); 


	
}
