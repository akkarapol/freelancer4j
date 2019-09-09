package com.freelancer4j.apigateway.model;

public class FreelancerSkill {
	
	private Long id;
	private Long freelancerId;
	private String skill;
	
	public FreelancerSkill() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public Long getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(Long freelancerId) {
		this.freelancerId = freelancerId;
	}

}
