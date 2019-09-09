package com.freelancer4j.freelancer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.freelancer4j.freelancer.model.Freelancer;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {

}
