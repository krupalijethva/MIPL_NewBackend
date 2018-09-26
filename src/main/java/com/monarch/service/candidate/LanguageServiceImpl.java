package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.LanguageDetail;
import com.monarch.repository.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService {

	
	@Autowired
	LanguageRepository lr;

	@Override
	public void save(LanguageDetail ld) {
		lr.save(ld);		
	}
}
