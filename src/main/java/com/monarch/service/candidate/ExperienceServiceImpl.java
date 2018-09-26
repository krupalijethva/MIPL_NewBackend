package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.ExpirenceDetail;
import com.monarch.repository.ExperienceRepository;

@Service
public class ExperienceServiceImpl implements ExperienceService {
	
	@Autowired
	ExperienceRepository er;

	@Override
	public void save(ExpirenceDetail ed) {
		er.save(ed);		
	}

}
