package com.assignment.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.user.exception.StatusCode;
import com.assignment.user.exception.UserException;
import com.assignment.user.intf.UserOperationInterface;
import com.assignment.user.model.User;

@Service
public class UserService implements UserOperationInterface {

	@Override
	public List<User> createUsers(List<User> users) throws UserException {
		CheckMobileNumbersValidation(users);
		return users;
	}

	@Override
	public List<User> getUsers() throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(int id) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> updateUsers(List<User> users) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteUsers(List<String> userIds) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void CheckMobileNumbersValidation(List<User> users) throws UserException
	{
		long countOfInvalidNumber = users.stream().filter(user->user.getMobileNumber().length()<10).count();
		 if(countOfInvalidNumber>0)
		 {
			 throw new UserException("Invalid number in this list",StatusCode.BAD_REQUEST);
		 }
	}


}
