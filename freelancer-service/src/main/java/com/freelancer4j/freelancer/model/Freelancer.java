package com.freelancer4j.freelancer.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "freelancer")
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long freelancerId;
    @NotNull
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @NotNull
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "freelancer_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Set<FreelancerSkill> skills;

	public Freelancer() {
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
