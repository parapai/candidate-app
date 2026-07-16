package com.vai.test.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vai.test.dto.CandidateInterviewScheduleDto;
import com.vai.test.dto.CandidateReq;
import com.vai.test.dto.InterviewScheduleReq;
import com.vai.test.dto.UpdateReq;
import com.vai.test.model.Candidate;
import com.vai.test.model.InterviewSchedule;
import com.vai.test.service.CandidateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

	private final CandidateService candidateService;

	@GetMapping
	public ResponseEntity<?> getAllCandidate(@RequestParam(required = false) String search,
			@RequestParam(required = false) String status, @RequestParam(defaultValue = "createdAt") String sortBy,
			@RequestParam(defaultValue = "asc") String sortDirection, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int limit) {

		Page<Candidate> result = candidateService.getAllCandidate(search, status, sortBy, sortDirection, page, limit);

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("message", "Success");
		response.put("data", result.getContent());

		response.put("pagination",
				Map.of("page", result.getNumber() + 1, "limit", result.getSize(), "totalData",
						result.getTotalElements(), "totalPage", result.getTotalPages(), "hasNext", result.hasNext(),
						"hasPrevious", result.hasPrevious()));

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getById(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			Candidate candidate = candidateService.getCandidateById(id);
			response.put("success", true);
			response.put("data", candidate);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> create(@Valid @RequestBody CandidateReq request) {
		Map<String, Object> response = new HashMap<>();

		Candidate savedCandidate = candidateService.createCandidate(request);
		response.put("success", true);
		response.put("message", "Candidate added successfully");
		response.put("data", savedCandidate);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PatchMapping("/{id}/status")
	public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable("id") Long id,
			@Valid @RequestBody UpdateReq request) {

		Candidate updatedCandidate = candidateService.updateCandidateStatus(id, request.getStatus());

		return ResponseEntity.ok(
				Map.of("success", true, "message", "Candidate status updated successfully", "data", updatedCandidate));

	}

	@PostMapping("/{id}/interviews")
	public ResponseEntity<Map<String, Object>> createInterviewSchedule(@PathVariable("id") Long id,
			@Valid @RequestBody InterviewScheduleReq request) {
		Map<String, Object> response = new HashMap<>();

		InterviewSchedule savedSchedule = candidateService.createInterviewSchedule(id, request);
		response.put("success", true);
		response.put("message", "Interview schedule added successfully");
		response.put("data", savedSchedule);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping("/{id}/interviews")
	public ResponseEntity<Map<String, Object>> getInterviewsById(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			CandidateInterviewScheduleDto dto = candidateService.getInterviewScheduleByCandidateId(id);
			response.put("success", true);
			response.put("data", dto);
			return ResponseEntity.ok(response);
		} catch (RuntimeException e) {
			response.put("success", false);
			response.put("message", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
	}

}
