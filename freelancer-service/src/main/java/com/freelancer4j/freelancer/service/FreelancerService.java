package com.freelancer4j.freelancer.service;

import java.util.List;

import com.freelancer4j.freelancer.model.Freelancer;

public interface FreelancerService {

    Freelancer getFreelancer(Long freelancerId);

    List<Freelancer> getAllFreelancer();

}
