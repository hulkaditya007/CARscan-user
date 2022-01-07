package com.assignment.user.intf;

import java.util.List;

import com.assignment.user.exception.UserException;
import com.assignment.user.model.UserEntity;

public interface UserOperationInterface {

	public List<UserEntity> createUsers(List<UserEntity> users) throws UserException;

	public List<UserEntity> getUsers() throws UserException;

	public List<UserEntity> updateUsers(List<UserEntity> users) throws UserException;

	public Object deleteUsers(List<Long> userIds) throws UserException;
}
