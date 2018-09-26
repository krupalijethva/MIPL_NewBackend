package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.SoftwareDetail;
import com.monarch.repository.SoftwareRepository;

@Service
public class SoftwareServiceImpl implements SoftwareService {
	@Autowired
	SoftwareRepository sr;

	@Override
	public void save(SoftwareDetail sd) {
sr.save(sd);		
	}

}
