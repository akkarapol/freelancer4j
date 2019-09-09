package com.freelancer4j.freelancer.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.freelancer4j.freelancer.model.Freelancer;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreelancerServiceTest {
	
	@Autowired
	private FreelancerService freelancerService;
	
    @Test
    public void testAllFreelancer() {
    	List<Freelancer> freelancers = freelancerService.getAllFreelancer();
        assertThat(freelancers, notNullValue());
        assertThat(freelancers.size(), equalTo(3));
        //Expect FreelancerId = 1 and Skills size = 4
        assertThat(freelancers.get(0).getFreelancerId(), equalTo(1L));
        assertThat(freelancers.get(0).getSkills().size(), equalTo(4));
    }
    
    @Test
    public void testFreelancer() {
    	Freelancer freelancer = freelancerService.getFreelancer(2L);
        assertThat(freelancer, notNullValue());
        assertThat(freelancer.getFreelancerId(), equalTo(2L));
        assertThat(freelancer.getFirstName(), equalTo("freelancer2_firstname"));
        assertThat(freelancer.getLastName(), equalTo("freelancer2_lastname"));
        assertThat(freelancer.getEmail(), equalTo("freelancer2@email.com"));
        assertThat(freelancer.getSkills().size(), equalTo(2));
    }
    
    @Test
    public void testGetNonExistingFreelancer() {
    	Freelancer freelancer = freelancerService.getFreelancer(5L);
    	assertThat(freelancer, nullValue());
    }

}
