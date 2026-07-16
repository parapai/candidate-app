package com.vai.test.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vai.test.dto.CandidateInterviewScheduleDto;
import com.vai.test.dto.CandidateReq;
import com.vai.test.dto.InterviewScheduleDto;
import com.vai.test.dto.InterviewScheduleReq;
import com.vai.test.exception.BadRequestException;
import com.vai.test.exception.DataNotFoundException;
import com.vai.test.model.Candidate;
import com.vai.test.model.CandidateStatus;
import com.vai.test.model.InterviewSchedule;
import com.vai.test.repository.CandidateRepository;
import com.vai.test.repository.InterviewScheduleRepository;
import com.vai.test.util.ToolsUtils;

import jakarta.persistence.criteria.Predicate;

@Service
public class CandidateService {

	@Autowired
	private  CandidateRepository candidateRepository;
	
	@Autowired
	private  InterviewScheduleRepository interviewScheduleRepository;

	public Page<Candidate> getAllCandidate(
	        String search,
	        String status,
	        String sortBy,
	        String sortDirection,
	        int page,
	        int limit) {

	    Sort sort = Sort.by(
	            "desc".equalsIgnoreCase(sortDirection)
	                    ? Sort.Direction.DESC
	                    : Sort.Direction.ASC,
	            sortBy == null || sortBy.isBlank() ? "id" : sortBy
	    );

	    Pageable pageable = PageRequest.of(page - 1, limit, sort);

	    Specification<Candidate> spec = (root, query, criteriaBuilder) -> {
	        List<Predicate> predicates = new ArrayList<>();

	        if (search != null && !search.isBlank()) {
	            String keyword = "%" + search.toLowerCase() + "%";

	            predicates.add(
	                criteriaBuilder.or(
	                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), keyword),
	                    criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), keyword)
	                )
	            );
	        }

	        if (status != null && !status.isBlank()) {
	            predicates.add(criteriaBuilder.equal(root.get("status"), status));
	        }

	        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	    };

	    return candidateRepository.findAll(spec, pageable);
	}

    public Candidate getCandidateById(Long id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Candidate with ID " + id + " not found"));
    }

    public Candidate createCandidate(CandidateReq request) {
    	
    	 Candidate candidate = Candidate.builder()
    			 .id(ToolsUtils.generateUuidToLong())
                 .name(request.getName())
                 .email(request.getEmail())
                 .phone(request.getPhone())
                 .appliedPosition(request.getAppliedPosition())
                 .status(CandidateStatus.APPLIED.name())
                 .createdBy("SYSTEM")
                 .build();
    	
        
        return candidateRepository.save(candidate);
    }

    public Candidate updateCandidateStatus(Long id, String newStatusStr)  {
        Candidate existingCandidate = getCandidateById(id);
        
        CandidateStatus newStatus;
        try {
            newStatus = CandidateStatus.valueOf(newStatusStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Status " +newStatusStr+" not valid.");
        }

        CandidateStatus oldStatus = CandidateStatus.valueOf(existingCandidate.getStatus());

        if (newStatus != CandidateStatus.REJECTED && newStatus.ordinal() < oldStatus.ordinal()) {
        	throw new BadRequestException( 
                "Status cannot move from " + oldStatus + " to " + newStatus
            );
        }

        existingCandidate.setStatus(newStatus.name());
        return candidateRepository.save(existingCandidate);
    }
    
    public InterviewSchedule createInterviewSchedule(Long id, InterviewScheduleReq request) {

        Candidate existingCandidate = getCandidateById(id);
        

        Optional<InterviewSchedule> existingScheduleOpt = interviewScheduleRepository.findByCandidateId(existingCandidate.getId());
        
        InterviewSchedule schedule;
        
        if (existingScheduleOpt.isPresent()) {
            schedule = existingScheduleOpt.get();
            schedule.setInterviewDateTime(request.getInterviewDateTime());
            schedule.setInterviewerName(request.getInterviewerName());
            schedule.setNotes(request.getNotes());
            schedule.setUpdatedBy("SYSTEM");
        } else {
            schedule = InterviewSchedule.builder()
                    .id(ToolsUtils.generateUuidToLong())
                    .candidate(existingCandidate)
                    .interviewDateTime(request.getInterviewDateTime())
                    .interviewerName(request.getInterviewerName())
                    .notes(request.getNotes())
                    .createdBy("SYSTEM")
                    .build();
        }
        
        return interviewScheduleRepository.save(schedule);
    }
    
    
    public CandidateInterviewScheduleDto getInterviewScheduleByCandidateId(Long id) {
    	CandidateInterviewScheduleDto dtoRes = new CandidateInterviewScheduleDto();
    	Candidate candidate =  candidateRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Candidate with ID " + id + " not found"));
    	dtoRes.setName(candidate.getName());
    	dtoRes.setEmail(candidate.getEmail());
    	dtoRes.setPhone(candidate.getPhone());
    	dtoRes.setAppliedPosition(candidate.getAppliedPosition());
    	dtoRes.setStatus(candidate.getStatus());
    	
    	Optional<InterviewSchedule> existingScheduleOpt = interviewScheduleRepository.findByCandidateId(candidate.getId());
    	InterviewScheduleDto scheduleDto = new InterviewScheduleDto();
    	InterviewSchedule ent = existingScheduleOpt.get();
    	scheduleDto.setInterviewerName(ent.getInterviewerName());
    	scheduleDto.setNotes(ent.getNotes());
    	scheduleDto.setInterviewDateTime(ToolsUtils.formatToString(ent.getInterviewDateTime()));
    	dtoRes.setInterviewSchedule(scheduleDto);
    	
    	return dtoRes;
    }

}
