
package com.jyoti.ai_recruitment_management.service;

import com.jyoti.ai_recruitment_management.dto.CandidateProfileRequest;
import com.jyoti.ai_recruitment_management.dto.CandidateProfileResponse;
import com.jyoti.ai_recruitment_management.entity.CandidateProfile;
import com.jyoti.ai_recruitment_management.entity.Role;
import com.jyoti.ai_recruitment_management.entity.User;
import com.jyoti.ai_recruitment_management.exception.ResourceNotFoundException;
import com.jyoti.ai_recruitment_management.repository.CandidateProfileRepository;
import com.jyoti.ai_recruitment_management.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
public class CandidateProfileService {

    private final CandidateProfileRepository candidateProfileRepository;
    private final UserRepository userRepository;

    public CandidateProfileService(
            CandidateProfileRepository candidateProfileRepository,
            UserRepository userRepository) {
        this.candidateProfileRepository = candidateProfileRepository;
        this.userRepository = userRepository;
    }


    public CandidateProfileResponse createProfile(
            CandidateProfileRequest request,
            String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        if (user.getRole() != Role.CANDIDATE) {
            throw new AccessDeniedException(
                    "Only candidates can create candidate profiles"
            );
        }

        if (candidateProfileRepository.existsByUserId(user.getId())) {
            throw new IllegalArgumentException(
                    "Candidate profile already exists"
            );
        }

        CandidateProfile profile = new CandidateProfile();
        profile.setUser(user);
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setSkills(request.getSkills());
        profile.setYearsOfExperience(request.getYearsOfExperience());
        profile.setCareerPreference(request.getCareerPreference());

        CandidateProfile savedProfile = candidateProfileRepository.save(profile);
        return mapToResponse(savedProfile);
    }


    private CandidateProfileResponse mapToResponse(CandidateProfile profile) {
        return new CandidateProfileResponse(
                profile.getId(),
                profile.getUser().getId(),
                profile.getUser().getName(),
                profile.getUser().getEmail(),
                profile.getPhoneNumber(),
                profile.getSkills(),
                profile.getYearsOfExperience(),
                profile.getCareerPreference()
        );
    }


}
