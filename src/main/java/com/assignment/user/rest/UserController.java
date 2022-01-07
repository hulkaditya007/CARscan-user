package com.assignment.user.rest;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.user.exception.StatusCode;
import com.assignment.user.exception.UserException;
import com.assignment.user.intf.UserOperationInterface;
import com.assignment.user.model.UserEntity;

@RestController
public class UserController {

	// PAT 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

	@Autowired
	UserOperationInterface userOperationInterface;

	@PostMapping("/createUsers")
	public Object createUser(@RequestBody List<UserEntity> users) throws UserException {
		List<UserEntity> response = userOperationInterface.createUsers(users);
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);

	}

	@GetMapping("/getUsers")
	public Object getUsers() throws UserException {
		List<UserEntity> response = userOperationInterface.getUsers();
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);
	}

	@PatchMapping("/updateUsers")
	public Object updateUsers(@RequestBody List<UserEntity> users) throws UserException {
		List<UserEntity> response = userOperationInterface.updateUsers(users);
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);
	}

	@DeleteMapping("/deleteUsers")
	public Object deleteUsers(@RequestBody List<Long> userIds) throws UserException {
		Object response = userOperationInterface.deleteUsers(userIds);
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);
	}

}
