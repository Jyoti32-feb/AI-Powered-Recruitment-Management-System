
package com.jyoti.ai_recruitment_management.controller;

import com.jyoti.ai_recruitment_management.dto.CandidateProfileRequest;
import com.jyoti.ai_recruitment_management.dto.CandidateProfileResponse;
import com.jyoti.ai_recruitment_management.service.CandidateProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate/profile")
public class CandidateProfileController {

    private final CandidateProfileService candidateProfileService;

    public CandidateProfileController(
            CandidateProfileService candidateProfileService) {
        this.candidateProfileService = candidateProfileService;
    }

    @PostMapping
    public ResponseEntity<CandidateProfileResponse> createProfile(
            @Valid @RequestBody CandidateProfileRequest request,
            Authentication authentication) {

        String userEmail = authentication.getName();

        CandidateProfileResponse response =
                candidateProfileService.createProfile(request, userEmail);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<CandidateProfileResponse> getMyProfile(
            Authentication authentication) {


        String userEmail = authentication.getName();

        CandidateProfileResponse response =
                candidateProfileService.getMyProfile(userEmail);

        return ResponseEntity.ok(response);
    }

}
