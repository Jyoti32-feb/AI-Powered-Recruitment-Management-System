package com.jyoti.ai_recruitment_management.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class JobRequest {

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Job description is required")
    @Size(max = 3000, message = "Description cannot exceed 3000 characters")
    private String description;

    @NotBlank(message = "Required skills are required")
    private String requiredSkills;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Employment type is required")
    private String employmentType;

    @NotNull(message = "Minimum salary is required")
    @DecimalMin(value = "0.0", message = "Minimum salary cannot be negative")
    private BigDecimal salaryMin;

    @NotNull(message = "Maximum salary is required")
    @DecimalMin(value = "0.0", message = "Maximum salary cannot be negative")
    private BigDecimal salaryMax;

    public JobRequest() {
    }

    public @NotBlank(message = "Job title is required") String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank(message = "Job title is required") String title) {
        this.title = title;
    }

    public @NotBlank(message = "Job description is required") @Size(max = 3000, message = "Description cannot exceed 3000 characters") String getDescription() {
        return description;
    }

    public void setDescription(@NotBlank(message = "Job description is required") @Size(max = 3000, message = "Description cannot exceed 3000 characters") String description) {
        this.description = description;
    }

    public @NotBlank(message = "Required skills are required") String getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(@NotBlank(message = "Required skills are required") String requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public @NotBlank(message = "Location is required") String getLocation() {
        return location;
    }

    public void setLocation(@NotBlank(message = "Location is required") String location) {
        this.location = location;
    }

    public @NotBlank(message = "Employment type is required") String getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(@NotBlank(message = "Employment type is required") String employmentType) {
        this.employmentType = employmentType;
    }

    public @NotNull(message = "Minimum salary is required") @DecimalMin(value = "0.0", message = "Minimum salary cannot be negative") BigDecimal getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(@NotNull(message = "Minimum salary is required") @DecimalMin(value = "0.0", message = "Minimum salary cannot be negative") BigDecimal salaryMin) {
        this.salaryMin = salaryMin;
    }

    public @NotNull(message = "Maximum salary is required") @DecimalMin(value = "0.0", message = "Maximum salary cannot be negative") BigDecimal getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(@NotNull(message = "Maximum salary is required") @DecimalMin(value = "0.0", message = "Maximum salary cannot be negative") BigDecimal salaryMax) {
        this.salaryMax = salaryMax;
    }




}