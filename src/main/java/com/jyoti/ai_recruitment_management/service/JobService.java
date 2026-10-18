package com.jyoti.ai_recruitment_management.service;

import com.jyoti.ai_recruitment_management.dto.JobRequest;
import com.jyoti.ai_recruitment_management.dto.JobResponse;
import com.jyoti.ai_recruitment_management.entity.Job;
import com.jyoti.ai_recruitment_management.entity.JobStatus;
import com.jyoti.ai_recruitment_management.entity.User;
import com.jyoti.ai_recruitment_management.exception.ResourceNotFoundException;
import com.jyoti.ai_recruitment_management.repository.JobRepository;
import com.jyoti.ai_recruitment_management.repository.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(
            JobRepository jobRepository,
            UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public JobResponse createJob(JobRequest request, String recruiterEmail) {
        if (request.getSalaryMin().compareTo(request.getSalaryMax()) > 0) {
            throw new IllegalArgumentException(
                    "Minimum salary cannot exceed maximum salary"
            );
        }
        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recruiter not found")
                );
        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setLocation(request.getLocation());
        job.setEmploymentType(request.getEmploymentType());
        job.setSalaryMin(request.getSalaryMin());
        job.setSalaryMax(request.getSalaryMax());

        job.setRecruiter(recruiter);

        Job savedJob = jobRepository.save(job);
        return  mapToResponse(savedJob);
    }

    private JobResponse mapToResponse(Job job) {
        return new JobResponse(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getRequiredSkills(),
                job.getLocation(),
                job.getEmploymentType(),
                job.getSalaryMin(),
                job.getSalaryMax(),
                job.getStatus(),
                job.getRecruiter().getId(),
                job.getRecruiter().getName()
        );
    }

    public List<JobResponse> getOpenJobs() {
        List<Job> jobs = jobRepository.findByStatus(JobStatus.OPEN);

        return jobs.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JobResponse getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + id
                        )
                );

        return mapToResponse(job);
    }

    public JobResponse updateJob(
            Long jobId,
            JobRequest request,
            String recruiterEmail) {

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + jobId
                        )
                );

        User recruiter = userRepository.findByEmail(recruiterEmail)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Recruiter not found")
                );

        if (!job.getRecruiter().getId().equals(recruiter.getId())) {
            throw new AccessDeniedException(
                    "You are not allowed to update this job"
            );
        }

        if (request.getSalaryMin().compareTo(request.getSalaryMax()) > 0) {
            throw new IllegalArgumentException(
                    "Minimum salary cannot exceed maximum salary"
            );
        }

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setRequiredSkills(request.getRequiredSkills());
        job.setLocation(request.getLocation());
        job.setEmploymentType(request.getEmploymentType());
        job.setSalaryMin(request.getSalaryMin());
        job.setSalaryMax(request.getSalaryMax());

        Job updatedJob = jobRepository.save(job);

        return mapToResponse(updatedJob);


    }
}