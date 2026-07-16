package com.vai.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InterviewScheduleDto {

	private String interviewDateTime;
    private String interviewerName;
    private String notes;
    
}
