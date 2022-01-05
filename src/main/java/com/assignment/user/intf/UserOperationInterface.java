package com.assignment.user.intf;

import java.util.List;

import com.assignment.user.exception.UserException;
import com.assignment.user.model.User;

public interface UserOperationInterface {

	public List<User> createUsers(List<User> users) throws UserException;
	public List<User> getUsers() throws UserException;
	public User getUserById(int id) throws UserException;
	public List<User> updateUsers(List<User> users) throws UserException;
	public Object deleteUsers(List<String> userIds) throws UserException;
}
