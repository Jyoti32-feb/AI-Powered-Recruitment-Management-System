package com.jyoti.ai_recruitment_management.dto;

import com.jyoti.ai_recruitment_management.entity.JobStatus;
import java.math.BigDecimal;

public class JobResponse {

    private Long id;
    private String title;
    private String description;
    private String requiredSkills;
    private String location;
    private String employmentType;
    private BigDecimal salaryMin;
    private BigDecimal salaryMax;
    private JobStatus status;
    private Long recruiterId;
    private String recruiterName;

    public JobResponse(
            Long id,
            String title,
            String description,
            String requiredSkills,
            String location,
            String employmentType,
            BigDecimal salaryMin,
            BigDecimal salaryMax,
            JobStatus status,
            Long recruiterId,
            String recruiterName) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.location = location;
        this.employmentType = employmentType;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.status = status;
        this.recruiterId = recruiterId;
        this.recruiterName = recruiterName;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getRequiredSkills() {
        return requiredSkills;
    }

    public String getLocation() {
        return location;
    }

    public String getEmploymentType() {
        return employmentType;
    }

    public BigDecimal getSalaryMin() {
        return salaryMin;
    }

    public BigDecimal getSalaryMax() {
        return salaryMax;
    }

    public JobStatus getStatus() {
        return status;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }

    public String getRecruiterName() {
        return recruiterName;
    }
}