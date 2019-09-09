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
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.wildfly.swarm.spi.runtime.annotations.ConfigurationValue;

import com.freelancer4j.apigateway.model.Project;

@Path("/projects")
@ApplicationScoped
public class ProjectService {
	
	private WebTarget projectService;

    @Inject
    @ConfigurationValue("project4j.service.url")
    private String projectUrl;
    
    @PostConstruct
    public void init() {
    	System.out.println("projectUrl===>"+projectUrl);
        ResteasyClientBuilder clientBuilder = (ResteasyClientBuilder)ClientBuilder.newBuilder();
        projectService = clientBuilder.connectionPoolSize(10).build().target(projectUrl);
    }

    public List<Project> getProjects() {
        Response response = projectService.path("projects").request().get();
        if (response.getStatus() == 200) {
        	List<Project> projects = response.readEntity(new GenericType<List<Project>>() {});
            return projects;
        } else if (response.getStatus() == 404) {
            return null;
        } else {
        	throw new ServiceUnavailableException();
        }
    }
    
	public Project getProjectId(String projectId) {
		Response response = projectService.path("projects/"+projectId).request().get();
        if (response.getStatus() == 200) {
        	Project projects = response.readEntity(Project.class);
            return projects;
        } else if (response.getStatus() == 404) {
            return null;
        } else {
        	throw new ServiceUnavailableException();
        }
	}
	
	public List<Project> getProjectStatus(String projectStatus) {
		Response response = projectService.path("projects/status/"+projectStatus).request().get();
        if (response.getStatus() == 200) {
        	List<Project> projects = response.readEntity(new GenericType<List<Project>>() {});
            return projects;
        } else if (response.getStatus() == 404) {
            return null;
        } else {
        	throw new ServiceUnavailableException();
        }
	}

}
