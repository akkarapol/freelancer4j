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

import com.freelancer4j.apigateway.model.Freelancer;
import com.freelancer4j.apigateway.rest.FreelancerServiceEndpoint;

@RunWith(MockitoJUnitRunner.class)
public class FreelancerServiceTest {
	
	@Mock
	FreelancerService freelancerService;
    @Spy
    @InjectMocks
    FreelancerServiceEndpoint freelancerServiceEndpoint;

    @Test
    public void getFreelancers() throws Exception {   
    	List<Freelancer> freelancers = new ArrayList<Freelancer>();
    	freelancers.add(new Freelancer(1L));
    	freelancers.add(new Freelancer(2L));
    	freelancers.add(new Freelancer(3L));
    	when(freelancerService.getFreelancers()).thenReturn(freelancers);
    	
    	List<Freelancer> actual = freelancerServiceEndpoint.getFreelancers();
    	
    	assertThat(freelancerService, notNullValue());
    	assertThat(freelancerServiceEndpoint, notNullValue());
    	assertThat(freelancers.size(), is(actual.size()));
    	verify(freelancerService, times(1)).getFreelancers();
    	
    }   
    
    @Test
    public void getFreelancersId() throws Exception {    
    	when(freelancerService.getFreelancersId(3L)).thenReturn(new Freelancer(3L));
    	
    	Freelancer actual = freelancerServiceEndpoint.getFreelancersId(3L);
    	
    	assertThat(freelancerService, notNullValue());
    	assertThat(freelancerServiceEndpoint, notNullValue());
    	assertEquals(new Long(3L), actual.getFreelancerId());    	
    	verify(freelancerService, times(1)).getFreelancersId(3L);
    	
    } 
    
}
