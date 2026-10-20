
package com.jyoti.ai_recruitment_management.dto;

import com.jyoti.ai_recruitment_management.entity.ApplicationStatus;
import java.time.LocalDateTime;

public class JobApplicationResponse {

    private Long id;
    private Long candidateProfileId;
    private Long jobId;
    private String jobTitle;
    private ApplicationStatus status;
    private LocalDateTime appliedAt;

    public JobApplicationResponse(
            Long id,
            Long candidateProfileId,
            Long jobId,
            String jobTitle,
            ApplicationStatus status,
            LocalDateTime appliedAt) {
        this.id = id;
        this.candidateProfileId = candidateProfileId;
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.status = status;
        this.appliedAt = appliedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getCandidateProfileId() {
        return candidateProfileId;
    }

    public Long getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }
}
