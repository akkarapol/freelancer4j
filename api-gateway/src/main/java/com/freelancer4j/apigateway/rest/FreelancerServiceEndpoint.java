package com.freelancer4j.apigateway.rest;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.freelancer4j.apigateway.model.Freelancer;
import com.freelancer4j.apigateway.service.FreelancerService;

@Path("/freelancers")
@ApplicationScoped
public class FreelancerServiceEndpoint {

	@Inject
	private FreelancerService freelancerService;
	
	@GET
    @Path("/")
    @Produces("application/json")
    public List<Freelancer> getFreelancers() {
        return freelancerService.getFreelancers();
    }
	
	@GET
    @Path("/{freelancerId}")
    @Produces("application/json")
    public Freelancer getFreelancersId(@PathParam("freelancerId") Long freelancerId) {
        return freelancerService.getFreelancersId(freelancerId);
    }
}
