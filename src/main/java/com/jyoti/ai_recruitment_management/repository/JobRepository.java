package com.jyoti.ai_recruitment_management.repository;

import com.jyoti.ai_recruitment_management.entity.Job;
import com.jyoti.ai_recruitment_management.entity.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByStatus(JobStatus status);
}
