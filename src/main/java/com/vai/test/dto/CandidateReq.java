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
public class CandidateReq {

	
	@NotNull(message = "Nama cannot be null")
	@NotEmpty(message = "Name cannot be empty")
    private String name;
	private String email;
	private String phone;
	@NotEmpty(message = "Position cannot be empty")
	private String appliedPosition;
	
	 @Schema(description = "Candidate Status")
	 private CandidateStatus status;
    
}
