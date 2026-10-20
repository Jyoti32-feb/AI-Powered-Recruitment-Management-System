
package com.jyoti.ai_recruitment_management.service;

import com.jyoti.ai_recruitment_management.dto.JobApplicationRequest;
import com.jyoti.ai_recruitment_management.entity.*;
import com.jyoti.ai_recruitment_management.repository.JobApplicationRepository;
import com.jyoti.ai_recruitment_management.repository.CandidateProfileRepository;
import com.jyoti.ai_recruitment_management.repository.JobRepository;
import com.jyoti.ai_recruitment_management.repository.UserRepository;
import com.jyoti.ai_recruitment_management.dto.JobApplicationRequest;
import com.jyoti.ai_recruitment_management.dto.JobApplicationResponse;
import com.jyoti.ai_recruitment_management.exception.ResourceNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;
    private final CandidateProfileRepository candidateProfileRepository;
    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobApplicationService(
            JobApplicationRepository jobApplicationRepository,
            CandidateProfileRepository candidateProfileRepository,
            JobRepository jobRepository,
            UserRepository userRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
        this.candidateProfileRepository = candidateProfileRepository;
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public JobApplicationResponse applyForJob(
            JobApplicationRequest request,
            String userEmail) {


        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));


        if (user.getRole() != Role.CANDIDATE) {
            throw new AccessDeniedException(
                    "Only candidates can apply for jobs");
        }


        CandidateProfile profile = candidateProfileRepository
                .findByUserId(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Candidate profile not found"));


        Job job = jobRepository.findById(request.getJobId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));


        if (job.getStatus() != JobStatus.OPEN) {
            throw new IllegalArgumentException(
                    "Applications are allowed only for open jobs");
        }



        boolean alreadyApplied =
                jobApplicationRepository
                        .existsByCandidateProfileIdAndJobId(
                                profile.getId(),
                                job.getId()
                        );

        if (alreadyApplied) {
            throw new IllegalArgumentException(
                    "You have already applied for this job"
            );
        }


        JobApplication application = new JobApplication();
        application.setCandidateProfile(profile);
        application.setJob(job);
        application.setStatus(ApplicationStatus.APPLIED);


        JobApplication savedApplication =
                jobApplicationRepository.save(application);


        return new JobApplicationResponse(
                savedApplication.getId(),
                savedApplication.getCandidateProfile().getId(),
                savedApplication.getJob().getId(),
                savedApplication.getJob().getTitle(),
                savedApplication.getStatus(),
                savedApplication.getAppliedAt()
        );

    }

}
