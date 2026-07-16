package com.vai.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.vai.test.model.InterviewSchedule;

public interface InterviewScheduleRepository
		extends JpaRepository<InterviewSchedule, Long>, JpaSpecificationExecutor<InterviewSchedule> {
	
	
	Optional<InterviewSchedule> findByCandidateId(Long candidateId);
}
