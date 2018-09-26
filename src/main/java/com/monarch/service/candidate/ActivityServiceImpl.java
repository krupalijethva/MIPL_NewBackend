package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.ActivityDetail;
import com.monarch.repository.ActivityRepository;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	ActivityRepository ar;

	@Override
	public void save(ActivityDetail ad) {

		ar.save(ad);
	}
	
}
