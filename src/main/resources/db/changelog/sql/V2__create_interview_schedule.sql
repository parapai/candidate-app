--liquibase formatted sql

--changeset rivai:1

CREATE TABLE interview_schedules (
    id BIGSERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    interview_date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    interviewer_name VARCHAR(100) NOT NULL,
    notes TEXT,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    created_by VARCHAR(150),
    updated_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(150),
    
    CONSTRAINT fk_candidate 
        FOREIGN KEY (candidate_id) 
        REFERENCES candidates(id) 
        ON DELETE CASCADE
);