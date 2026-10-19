
package com.jyoti.ai_recruitment_management.dto;

public class CandidateProfileResponse {

    private Long id;
    private Long userId;
    private String name;
    private String email;
    private String phoneNumber;
    private String skills;
    private Integer yearsOfExperience;
    private String careerPreference;

    public CandidateProfileResponse(
            Long id,
            Long userId,
            String name,
            String email,
            String phoneNumber,
            String skills,
            Integer yearsOfExperience,
            String careerPreference) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
        this.yearsOfExperience = yearsOfExperience;
        this.careerPreference = careerPreference;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSkills() {
        return skills;
    }

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public String getCareerPreference() {
        return careerPreference;
    }
}
