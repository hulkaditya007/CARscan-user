package com.assignment.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.assignment.user.model.UserEntity;
import com.assignment.user.repository.UserRepository;
import com.assignment.user.rest.ResponseDetail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApplicationTests {

	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

	 @Test
	    void testCreateAndGetUser() {
	        // Arrange
	        String baseUrl = "http://localhost:" + port + "/createUsers";
	        List<UserEntity> users = new ArrayList<>();
			UserEntity userEntity = new UserEntity();
			userEntity.setfName("Aditya");
			userEntity.setDob(new Date("07/12/1997"));
			userEntity.setMobileNumber("8574524589");
			users.add(userEntity);

	        // Act
			ResponseEntity<ResponseDetail> postResponse = restTemplate.postForEntity(baseUrl, users, ResponseDetail.class);
			assertThat(postResponse.getStatusCode().compareTo(HttpStatus.ACCEPTED)); 
			System.out.println(postResponse);
	        
			/*
			 * User createdUser = postResponse.getBody(); ResponseEntity<User> getResponse =
			 * restTemplate.getForEntity(baseUrl + "/" + createdUser.getId(), User.class);
			 * 
			 * // Assert assertThat(getResponse.getStatusCodeValue()).isEqualTo(200);
			 * assertThat(getResponse.getBody()).isNotNull();
			 * assertThat(getResponse.getBody().getFirstName()).isEqualTo("John");
			 */
	    }

}
