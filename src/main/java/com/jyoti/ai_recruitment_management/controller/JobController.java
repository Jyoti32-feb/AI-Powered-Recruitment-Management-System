package com.jyoti.ai_recruitment_management.controller;

import com.jyoti.ai_recruitment_management.dto.JobRequest;
import com.jyoti.ai_recruitment_management.dto.JobResponse;
import com.jyoti.ai_recruitment_management.service.JobService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public ResponseEntity<JobResponse> createJob(
            @Valid @RequestBody JobRequest request,
            Authentication authentication) {

        String recruiterEmail = authentication.getName();

        JobResponse response =
                jobService.createJob(request, recruiterEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/open-jobs")
    public ResponseEntity<List<JobResponse>> getOpenJobs() {
        List<JobResponse> jobs = jobService.getOpenJobs();
        return ResponseEntity.ok(jobs);
    }
}