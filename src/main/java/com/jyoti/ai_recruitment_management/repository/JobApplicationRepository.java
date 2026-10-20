
package com.jyoti.ai_recruitment_management.repository;

import com.jyoti.ai_recruitment_management.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    boolean existsByCandidateProfileIdAndJobId(
            Long candidateProfileId,
            Long jobId
    );
}
