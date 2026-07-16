package com.vai.test.dto;

import com.vai.test.model.CandidateStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateInterviewScheduleDto {

	
    private String name;
	private String email;
	private String phone;
	private String appliedPosition;
	private String status;
	
	private InterviewScheduleDto interviewSchedule;
    
}
