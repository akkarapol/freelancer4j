package com.freelancer4j.freelancer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelancer4j.freelancer.model.Freelancer;
import com.freelancer4j.freelancer.repository.FreelancerRepository;

@Service
@Transactional
public class FreelancerServiceImpl implements FreelancerService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Override
    @Transactional(readOnly = true)
    public Freelancer getFreelancer(Long freelancerId) {
        return freelancerRepository.findOne(freelancerId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Freelancer> getAllFreelancer() {
        return freelancerRepository.findAll();
    }

}
