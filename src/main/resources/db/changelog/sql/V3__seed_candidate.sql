--liquibase formatted sql

--changeset rivai:1

INSERT INTO public.candidates (id, "name", email, phone, applied_position, status, created_at, created_by, updated_at, updated_by) VALUES
(9, 'Siti Aminah', 'siti.aminah@email.com', '08129876543', 'Backend Developer', 'APPLIED', '2026-07-16 10:01:00.000', 'SYSTEM', '2026-07-16 10:01:00.000', NULL),
(10, 'Rian Hidayat', 'rian.h@email.com', '08571234567', 'Front End Developer', 'SCREENING', '2026-07-16 10:02:15.120', 'SYSTEM', '2026-07-16 10:02:15.120', NULL),
(11, 'Dewi Lestari', 'dewi.les@email.com', '08138888777', 'DevOps Engineer', 'APPLIED', '2026-07-16 10:05:30.450', 'SYSTEM', '2026-07-16 10:05:30.450', NULL),
(12, 'Aditya Pratama', 'adit.pratama@email.com', '08190987654', 'Backend Developer', 'INTERVIEW', '2026-07-16 10:07:45.000', 'SYSTEM', '2026-07-16 10:07:45.000', NULL),
(13, 'Rina Wijaya', 'rina.wjy@email.com', '08213456789', 'QA Engineer', 'REJECTED', '2026-07-16 10:10:12.880', 'SYSTEM', '2026-07-16 10:10:12.880', NULL),
(14, 'Fajar NugRAha', 'fajar.nug@email.com', '08781234999', 'IT Ops', 'APPLIED', '2026-07-16 10:12:00.115', 'SYSTEM', '2026-07-16 10:12:00.115', NULL),
(15, 'Santi Putri', 'santi.p@email.com', '08569999888', 'Front End Developer', 'APPLIED', '2026-07-16 10:15:22.340', 'SYSTEM', '2026-07-16 10:15:22.340', NULL),
(16, 'Eko Prasetyo', 'eko.pras@email.com', '08112233445', 'Backend Developer', 'APPROVED', '2026-07-16 10:18:50.000', 'SYSTEM', '2026-07-16 10:18:50.000', NULL),
(17, 'Mega Utami', 'mega.utami@email.com', '08125555444', 'Mobile Developer', 'SCREENING', '2026-07-16 10:20:05.670', 'SYSTEM', '2026-07-16 10:20:05.670', NULL),
(18, 'Hendra Wijaya', 'hendra.w@email.com', '08137777999', 'DevOps Engineer', 'REJECTED', '2026-07-16 10:22:40.190', 'SYSTEM', '2026-07-16 10:22:40.190', NULL),
(19, 'Dina Mariana', 'dina.m@email.com', '08521111222', 'QA Engineer', 'APPLIED', '2026-07-16 10:25:15.000', 'SYSTEM', '2026-07-16 10:25:15.000', NULL),
(20, 'Rizky Alamsyah', 'rizky.alam@email.com', '08778888333', 'Backend Developer', 'INTERVIEW', '2026-07-16 10:28:34.900', 'SYSTEM', '2026-07-16 10:28:34.900', NULL),
(21, 'Amanda Caroline', 'amanda.c@email.com', '08193333444', 'Front End Developer', 'APPLIED', '2026-07-16 10:30:00.000', 'SYSTEM', '2026-07-16 10:30:00.000', NULL),
(22, 'Taufik Hidayat', 'taufik.h@email.com', '08124444555', 'IT Ops', 'SCREENING', '2026-07-16 10:32:18.220', 'SYSTEM', '2026-07-16 10:32:18.220', NULL),
(23, 'Fitriani', 'fitri.ani@email.com', '08562222111', 'Data Analyst', 'APPLIED', '2026-07-16 10:35:50.445', 'SYSTEM', '2026-07-16 10:35:50.445', NULL),
(24, 'Bambang Pamungkas', 'bambang.p@email.com', '08139999000', 'Backend Developer', 'REJECTED', '2026-07-16 10:38:12.110', 'SYSTEM', '2026-07-16 10:38:12.110', NULL),
(25, 'Gita Gutawa', 'gita.g@email.com', '08785555666', 'Front End Developer', 'APPROVED', '2026-07-16 10:40:00.000', 'SYSTEM', '2026-07-16 10:40:00.000', NULL),
(26, 'Denny Caknan', 'denny.c@email.com', '08126666777', 'Mobile Developer', 'APPLIED', '2026-07-16 10:42:25.880', 'SYSTEM', '2026-07-16 10:42:25.880', NULL),
(27, 'Wulan Guritno', 'wulan.g@email.com', '08197777888', 'QA Engineer', 'SCREENING', '2026-07-16 10:44:10.050', 'SYSTEM', '2026-07-16 10:44:10.050', NULL),
(28, 'Andika Perkasa', 'andika.p@email.com', '08573333222', 'DevOps Engineer', 'INTERVIEW', '2026-07-16 10:46:01.320', 'SYSTEM', '2026-07-16 10:46:01.320', NULL);
