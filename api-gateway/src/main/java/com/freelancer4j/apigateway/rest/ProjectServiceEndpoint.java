package com.freelancer4j.apigateway.rest;


import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.freelancer4j.apigateway.model.Project;
import com.freelancer4j.apigateway.service.ProjectService;

@Path("/projects")
@ApplicationScoped
public class ProjectServiceEndpoint {

	@Inject
	private ProjectService projectService;
	
	@GET
    @Path("/")
    @Produces("application/json")
    public List<Project> gePprojects() {
        return projectService.getProjects();
    }
	
	@GET
    @Path("/{projectId}")
    @Produces("application/json")
    public Project getProjectId(@PathParam("projectId") String projectId) {
        return projectService.getProjectId(projectId);
    }
	
	@GET
    @Path("/status/{theStatus}")
    @Produces("application/json")
    public List<Project> getProjectStatus(@PathParam("theStatus") String projectStatus) {
        return projectService.getProjectStatus(projectStatus);
    }
}
