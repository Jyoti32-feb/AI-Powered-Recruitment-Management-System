
package com.jyoti.ai_recruitment_management.dto;

import jakarta.validation.constraints.NotNull;

public class JobApplicationRequest {

    @NotNull(message = "Job ID is required")
    private Long jobId;

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
