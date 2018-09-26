package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.MembershipDetail;
import com.monarch.repository.MembershipRepository;

@Service
public class MembershipServiceImpl implements MembershipService {

@Autowired
MembershipRepository mr;

@Override
public void save(MembershipDetail md) {
	mr.save(md);
}

}

