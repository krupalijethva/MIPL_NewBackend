package com.monarch.service.candidate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monarch.domain.candidate.QualificationDetail;
import com.monarch.repository.QualificationRepository;

@Service
public class QualificationServiceImpl implements QualificationService {
	
	@Autowired
	QualificationRepository qr;

	@Override
	public void save(QualificationDetail qd) {
		qr.save(qd);
	}
	

}
