--liquibase formatted sql

--changeset rivai:1

INSERT INTO public.interview_schedules 
(id, candidate_id, interview_date_time, interviewer_name, notes, created_at, created_by, updated_at, updated_by) VALUES
(1, 12, '2026-07-20 10:00:00.000 +07:00', 'Hendra (Lead Backend)', 'Technical Interview - Live Coding Java & Spring Boot', '2026-07-16 10:55:00.000', 'SYSTEM', '2026-07-16 10:55:00.000', NULL),
(2, 20, '2026-07-21 13:30:00.000 +07:00', 'Rian (Senior Engineer)', 'Technical Interview - System Design & Database', '2026-07-16 10:55:15.000', 'SYSTEM', '2026-07-16 10:55:15.000', NULL),
(3, 28, '2026-07-22 15:00:00.000 +07:00', 'Dewi (HR Manager)', 'HR Interview & Behavioral Assessment', '2026-07-16 10:55:30.000', 'SYSTEM', '2026-07-16 10:55:30.000', NULL);