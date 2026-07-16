package com.vai.test.dto;

import java.time.ZonedDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewScheduleReq {

	@NotNull(message = "Interview date and time is required")
    @Future(message = "Interview date must be in the future")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Jakarta")
	private ZonedDateTime interviewDateTime;
    
	@NotBlank(message = "Interviewer name cannot be empty")
    @Size(min = 3, max = 100, message = "Interviewer name must be between 3 and 100 characters")
	private String interviewerName;
    @Size(max = 255, message = "Notes cannot exceed 255 characters")
    private String notes;
    
}
