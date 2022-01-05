package com.assignment.user.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.assignment.user.model.User;
import com.assignment.user.exception.UserException;
import com.assignment.user.intf.UserOperationInterface;

@RestController
public class UserController {
	
	//PAT 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

	@Autowired
	UserOperationInterface userOperationInterface;
	
	@PostMapping("/createUsers")
	public List<User> createUser(@RequestBody List<User> users) throws UserException
	{
		return userOperationInterface.createUsers(users);
	}
}
