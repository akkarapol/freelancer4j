package com.freelancer4j.freelancer.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freelancer4j.freelancer.model.Freelancer;
import com.freelancer4j.freelancer.service.FreelancerService;

@RestController
@RequestMapping("/freelancers")
public class FreelancerEndpoint {
	
    @Autowired
    private FreelancerService freelancerService;

    @GetMapping("/{freelancerId}")
    public Freelancer getFreelancer(@PathVariable("freelancerId") Long freelancerId) {
    	Freelancer freelancer = freelancerService.getFreelancer(freelancerId);
    	System.out.print("freelancer==>"+freelancer.getSkills().size());
        return freelancer;
    }
    
    @GetMapping
    public List<Freelancer> getAllFreelancer() {

        return freelancerService.getAllFreelancer();
    }

}
