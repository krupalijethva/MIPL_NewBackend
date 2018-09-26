package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.TrainingDetail;
import com.monarch.repository.TrainingRepository;

@Service
public class TrainingServiceImpl implements TrainingService {
	
	@Autowired
	TrainingRepository tr;

	@Override
	public void save(TrainingDetail td) {
		tr.save(td);		
	}
	

}
