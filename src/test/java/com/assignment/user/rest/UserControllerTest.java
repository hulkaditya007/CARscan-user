package com.assignment.user.rest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.assignment.user.exception.UserException;
import com.assignment.user.intf.UserOperationInterface;
import com.assignment.user.model.UserEntity;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController = new UserController();

	@Mock
	private UserOperationInterface userOperationInterface;

	private List<UserEntity> createUserDetails() {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(1);
		userEntity.setfName("XYZ");
		userEntity.setlName("ABC");
		userEntity.setMobileNumber("1111111111");
		userEntity.setDob(new Date("12/12/2012"));
		List<UserEntity> userEntityList = new ArrayList<>();
		userEntityList.add(userEntity);
		return userEntityList;
	}

	@Test
	public void createUserTest() throws UserException {
		Mockito.when(userOperationInterface.createUsers(Mockito.any())).thenReturn(createUserDetails());
		ResponseEntity<ResponseDetail> result = userController.createUser(createUserDetails());
		result.getBody().getResponseBody();

		// ObjectMapper mapper = new ObjectMapper();
		// List<UserEntity> myObjects = Arrays
		// .asList(mapper.readValue(result.getBody().getResponseBody(),
		// UserEntity[].class));
		//
		// mapper.readValue(result.getBody().getResponseBody(),
		// UserEntity[].class);

		assertEquals(createUserDetails(), result.getBody());

	}
}
