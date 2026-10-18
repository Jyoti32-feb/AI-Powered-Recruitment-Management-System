package com.jyoti.ai_recruitment_management.repository;

import com.jyoti.ai_recruitment_management.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
