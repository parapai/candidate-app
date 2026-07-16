package com.vai.test.dto;

import com.vai.test.model.CandidateStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaginationRes { 
	
	private int page;
private int limit;
private long totalData;
private int totalPage;
private boolean hasNext;
private boolean hasPrevious;}
