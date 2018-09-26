package com.monarch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.UserLeaveDetail;
import com.monarch.repository.UserLeaveDetailRepository;


@Service
public class UserLeaveDetailServiceImpl implements UserLeaveDetailService {
	
	
	@Autowired
	UserLeaveDetailRepository userdetailRepo;

	@Override
	public void save(UserLeaveDetail ul) {
		userdetailRepo.save(ul);
		
	}
	


}
