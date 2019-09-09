package com.freelancer4j.apigateway.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import com.freelancer4j.apigateway.model.Freelancer;

@Path("/freelancers")
@ApplicationScoped
public class FreelancerService {
	
	private WebTarget freelancerService;

    @Inject
    @ConfigurationValue("freelancer.service.url")
    private String freelancerUrl;
    
    @PostConstruct
    public void init() {
    	System.out.println("freelancerUrl===>"+freelancerUrl);
        ResteasyClientBuilder clientBuilder = (ResteasyClientBuilder)ClientBuilder.newBuilder();
        freelancerService = clientBuilder.connectionPoolSize(10).build().target(freelancerUrl);
    }

    public List<Freelancer> getFreelancers() {
        Response response = freelancerService.path("freelancers").request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	List<Freelancer> freelancers = response.readEntity(new GenericType<List<Freelancer>>() {});
            return freelancers;
        } else if (response.getStatus() == 404) {
            return null;
        } else {
        	throw new ServiceUnavailableException();
        }
    }
    
	public Freelancer getFreelancersId(Long freelancerId) {
		Response response = freelancerService.path("freelancers/"+freelancerId).request(MediaType.APPLICATION_JSON).get();
        if (response.getStatus() == 200) {
        	Freelancer freelancer = response.readEntity(Freelancer.class);
            return freelancer;
        } else if (response.getStatus() == 404) {
            return null;
        } else {
        	throw new ServiceUnavailableException();
        }
	}

}
