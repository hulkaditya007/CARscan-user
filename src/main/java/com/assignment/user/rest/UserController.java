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

/**
 * This is a RestController class which will accepts all the requests coming
 * from the client and handover to respective method for processing.
 *
 */
@RestController
public class UserController {

	// PAT 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

	@Autowired
	UserOperationInterface userOperationInterface;

	/**
	 * Takes list of users as User object as input and returns save status as
	 * ResponseEntity<ResponseDetail>
	 */
	@PostMapping("/createUsers")
	public ResponseEntity<ResponseDetail> createUser(@RequestBody List<UserEntity> users) throws UserException {
		List<UserEntity> response = userOperationInterface.createUsers(users);
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);

	}

	/**
	 * To retrieve all users details, returns data retrieval status as
	 * ResponseEntity<ResponseDetail>
	 */
	@GetMapping("/getUsers")
	public ResponseEntity<ResponseDetail> getUsers() throws UserException {
		List<UserEntity> response = userOperationInterface.getUsers();
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);
	}

	/**
	 * To modify any user detail by providing userId, which will update the
	 * corresponding user details and returns status as
	 * ResponseEntity<ResponseDetail>
	 */
	@PatchMapping("/updateUsers")
	public ResponseEntity<ResponseDetail> updateUsers(@RequestBody List<UserEntity> users) throws UserException {
		List<UserEntity> response = userOperationInterface.updateUsers(users);
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);
	}

	/**
	 * To delete list of users by providing UserId, Which will return status as
	 * ResponseEntity<ResponseDetail>
	 *
	 */
	@DeleteMapping("/deleteUsers")
	public ResponseEntity<ResponseDetail> deleteUsers(@RequestBody List<Long> userIds) throws UserException {
		Object response = userOperationInterface.deleteUsers(userIds);
		ResponseDetail responseDetail = new ResponseDetail(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS, response,
				Instant.now());
		return ResponseEntity.status(responseDetail.getStatus()).body(responseDetail);
	}

}
