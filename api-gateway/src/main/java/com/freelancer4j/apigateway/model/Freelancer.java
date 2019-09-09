package com.freelancer4j.apigateway.model;

import java.util.Set;

public class Freelancer {

    private Long freelancerId;
    private String firstName;
    private String lastName;
    private String email;
	private Set<FreelancerSkill> skills;

	public Freelancer() {
    }
	
	public Freelancer(Long freelancerId) {
		this.freelancerId = freelancerId;
    }

    public Freelancer(Long freelancerId, String firstName, String lastName, String email) {
        this.freelancerId = freelancerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Freelancer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Set<FreelancerSkill> getSkills() {
		return skills;
	}

	public void setSkills(Set<FreelancerSkill> skills) {
		this.skills = skills;
	}

    @Override
    public String toString() {
        return "Freelancer [FreelancerId=" + freelancerId + ", First name=" + firstName + ", Last name=" + lastName + ", email address=" + email + "]";
    }

}
