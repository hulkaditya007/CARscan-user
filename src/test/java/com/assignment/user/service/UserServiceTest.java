package com.assignment.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.assignment.user.exception.UserException;
import com.assignment.user.model.UserEntity;
import com.assignment.user.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	public void test_createUsers() throws UserException {
		List<UserEntity> users = new ArrayList<>();
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(1l);
		userEntity.setfName("Aditya");
		userEntity.setDob(new Date("07/12/1997"));
		userEntity.setMobileNumber("8574524531");
		users.add(userEntity);
		userService.createUsers(users);
	}

}
