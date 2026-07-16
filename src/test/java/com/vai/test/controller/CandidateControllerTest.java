package com.vai.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vai.test.model.Candidate;
import com.vai.test.model.InterviewSchedule;
import com.vai.test.service.CandidateService;

@WebMvcTest(CandidateController.class)
class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CandidateService candidateService;

    private Candidate sampleCandidate;

    @BeforeEach
    void setUp() {
    	objectMapper.registerModule(new JavaTimeModule());
        sampleCandidate = Candidate.builder()
                .id(1L)
                .name("Achmad")
                .email("achmad@example.com")
                .phone("08123456789")
                .appliedPosition("Backend Engineer")
                .status("APPLIED")
                .createdBy("SYSTEM")
                .build();
    }

    @Test
    void testGetAllCandidate_Success() throws Exception {
        List<Candidate> candidates = List.of(sampleCandidate);
        Page<Candidate> pageResult = new PageImpl<>(candidates, PageRequest.of(0, 10), 1);

        when(candidateService.getAllCandidate(any(), any(), anyString(), anyString(), anyInt(), anyInt()))
                .thenReturn(pageResult);

        mockMvc.perform(get("/api/candidates")
                        .param("search", "Achmad")
                        .param("page", "1")
                        .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Success"))
                .andExpect(jsonPath("$.data[0].name").value("Achmad"))
                .andExpect(jsonPath("$.pagination.totalData").value(1));
    }

    @Test
    void testGetById_Success() throws Exception {
        when(candidateService.getCandidateById(1L)).thenReturn(sampleCandidate);

        mockMvc.perform(get("/api/candidates/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Achmad"));
    }

    @Test
    void testGetById_NotFound() throws Exception {
        when(candidateService.getCandidateById(99L))
                .thenThrow(new RuntimeException("Candidate not found"));

        mockMvc.perform(get("/api/candidates/{id}", 99L))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Candidate not found"));
    }

    @Test
    void testCreateCandidate_Success() throws Exception {
        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("name", "Achmad");
        reqBody.put("email", "achmad@example.com");
        reqBody.put("phone", "08123456789");
        reqBody.put("appliedPosition", "Backend Engineer");

        when(candidateService.createCandidate(any())).thenReturn(sampleCandidate);

        mockMvc.perform(post("/api/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqBody)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Candidate added successfully"))
                .andExpect(jsonPath("$.data.id").value(1));
    }

    @Test
    void testUpdateStatus_Success() throws Exception {
        Candidate updatedCandidate = sampleCandidate;
        updatedCandidate.setStatus("INTERVIEW");

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("status", "INTERVIEW");

        when(candidateService.updateCandidateStatus(eq(1L), eq("INTERVIEW"))).thenReturn(updatedCandidate);

        mockMvc.perform(patch("/api/candidates/{id}/status", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Candidate status updated successfully"))
                .andExpect(jsonPath("$.data.status").value("INTERVIEW"));
    }

    @Test
    void testCreateInterviewSchedule_Success() throws Exception {
        InterviewSchedule mockSchedule = InterviewSchedule.builder()
                .id(999L)
                .interviewerName("User Interviewer")
                .notes("Technical Test Review")
                .build();

        Map<String, Object> reqBody = new HashMap<>();
        reqBody.put("interviewerName", "User Interviewer");
        reqBody.put("interviewDateTime", "2026-07-20T10:00:00");
        reqBody.put("notes", "Technical Test Review");

        when(candidateService.createInterviewSchedule(anyLong(), any())).thenReturn(mockSchedule);

        mockMvc.perform(post("/api/candidates/{id}/interviews", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(reqBody)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("Interview schedule added successfully"))
                .andExpect(jsonPath("$.data.id").value(999));
    }
}