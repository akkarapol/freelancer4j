package com.freelancer4j.apigateway.service;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.freelancer4j.apigateway.model.Project;
import com.freelancer4j.apigateway.rest.ProjectServiceEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class ProjectServiceTest {
	
	@Mock
	ProjectService projectService;
    @Spy
    @InjectMocks
    ProjectServiceEndpoint projectServiceEndpoint;

    @Test
    public void getProjects() throws Exception {   
    	List<Project> projects = new ArrayList<Project>();
    	projects.add(new Project("1"));
    	projects.add(new Project("2"));
    	projects.add(new Project("3"));
    	when(projectService.getProjects()).thenReturn(projects);
    	
    	List<Project> actual = projectServiceEndpoint.gePprojects();
    	
    	assertThat(projectService, notNullValue());
    	assertThat(projectServiceEndpoint, notNullValue());
    	assertThat(projects.size(), is(actual.size()));
    	verify(projectService, times(1)).getProjects();
    	
    }   
    
    @Test
    public void getProjectsId() throws Exception {    
    	when(projectService.getProjectId("1")).thenReturn(new Project("1", "open"));
    	
    	Project actual = projectService.getProjectId("1");
    	
    	assertThat(projectService, notNullValue());
    	assertThat(projectServiceEndpoint, notNullValue());
    	assertEquals("1", actual.getProjectId());    	
    	verify(projectService, times(1)).getProjectId("1");
    	
    } 
    
    @Test
    public void getProjectsStatus() throws Exception {  
    	List<Project> projects = new ArrayList<Project>();
    	projects.add(new Project("2", "cancelled"));
    	projects.add(new Project("3", "cancelled"));
    	when(projectService.getProjectStatus("cancelled")).thenReturn(projects);
    	
    	List<Project> actual = projectServiceEndpoint.getProjectStatus("cancelled");
    	
    	assertThat(projectService, notNullValue());
    	assertThat(projectServiceEndpoint, notNullValue());
    	assertThat(2, is(actual.size()));
    	assertEquals("cancelled", actual.get(0).getProjectStatus());
    	verify(projectService, times(1)).getProjectStatus("cancelled");
    }  

}
