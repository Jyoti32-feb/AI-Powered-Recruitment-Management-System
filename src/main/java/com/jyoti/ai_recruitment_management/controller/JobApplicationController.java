
package com.jyoti.ai_recruitment_management.controller;

import com.jyoti.ai_recruitment_management.dto.JobApplicationRequest;
import com.jyoti.ai_recruitment_management.dto.JobApplicationResponse;
import com.jyoti.ai_recruitment_management.service.JobApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applications")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    public JobApplicationController(
            JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    @PostMapping
    public ResponseEntity<JobApplicationResponse> applyForJob(
            @Valid @RequestBody JobApplicationRequest request,
            Authentication authentication) {

        String userEmail = authentication.getName();

        JobApplicationResponse response =
                jobApplicationService.applyForJob(request, userEmail);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
