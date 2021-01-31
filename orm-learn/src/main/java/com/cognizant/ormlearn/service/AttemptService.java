package com.cognizant.ormlearn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.repository.*;
import com.cognizant.ormlearn.model.Attempt;

@Service
public class AttemptService {
	@Autowired
	AttemptRepository attemptRepository;

	@Transactional
	public Attempt getAttempt(int userId, int attemptId) {
		return attemptRepository.getAttempt(userId,attemptId);
	}
}