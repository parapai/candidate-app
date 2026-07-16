--liquibase formatted sql

--changeset rivai:1

CREATE TABLE IF NOT EXISTS interview_schedules (
    id BIGSERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    interviewer_name VARCHAR(100),
    interview_datetime TIMESTAMP,
    notes TEXT,

    CONSTRAINT fk_candidate
        FOREIGN KEY(candidate_id)
        REFERENCES candidates(id)
);