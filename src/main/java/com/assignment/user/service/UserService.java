package com.assignment.user.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.assignment.user.exception.StatusCode;
import com.assignment.user.exception.UserException;
import com.assignment.user.intf.UserOperationInterface;
import com.assignment.user.model.UserEntity;
import com.assignment.user.repository.UserRepository;

/**
 * This UserService class contains implementation of method declared in
 * UserOperationInterface for database related operations.
 *
 */
@Service
public class UserService implements UserOperationInterface {

	private static final String MOBILE_ERROR_MESSAGE = "Mobile number should be unique for a user";
	private static final String INVALID_USER_ID = "Invalid number in the input list";
	private static final String INVALID_MOBILE_NUMBER = "Invalid mobile number in the input list";
	private static final String INVALID_DOB = "Date is Invalid! And I dont beleive in time travelling.";
	private static final String DELETE_SUCCESS_MESSAGE = "All Users deleted succesfully";

	@Autowired
	UserRepository userRepository;

	@Override
	public List<UserEntity> createUsers(List<UserEntity> users) throws UserException {
		try {
			checkUsersDOB(users.stream().map(user -> user.getDob()).collect(Collectors.toList()));
			checkMobileNumbersValidation(users);
			for (UserEntity user : users) {
				userRepository.save(user);
			}
			return users;
		} catch (UserException ex) {
			throw new UserException(ex.getMessage(), ex.getStatusCode());
		} catch (DataIntegrityViolationException ex) {
			throw new UserException(MOBILE_ERROR_MESSAGE, ex.getCause(), StatusCode.INVALID_INPUT_EXCEPTION);
		} catch (Exception ex) {
			throw new UserException(ex.getMessage(), ex.getCause(), StatusCode.GENERIC_EXCEPTION);
		}
	}

	@Override
	public List<UserEntity> getUsers() throws UserException {
		return userRepository.findAll();
	}

	@Override
	public List<UserEntity> updateUsers(List<UserEntity> users) throws UserException {
		try {
			users = autoFillingValues(users);
			checkUsersAreValid(users.stream().map(user -> user.getUserId()).collect(Collectors.toList()));
			checkMobileNumbersValidation(users);
			checkUsersDOB(users.stream().map(user -> user.getDob()).collect(Collectors.toList()));
			for (UserEntity user : users) {
				userRepository.save(user);
			}
			return users;
		} catch (UserException ex) {
			throw new UserException(ex.getMessage(), ex.getStatusCode());
		} catch (DataIntegrityViolationException ex) {
			throw new UserException(MOBILE_ERROR_MESSAGE, ex.getCause(), StatusCode.INVALID_INPUT_EXCEPTION);
		} catch (Exception ex) {
			throw new UserException(ex.getMessage(), ex.getCause(), StatusCode.GENERIC_EXCEPTION);
		}

	}

	@Override
	public Object deleteUsers(List<Long> userIds) throws UserException {
		try {
			checkUsersAreValid(userIds);
			for (Long userId : userIds) {
				userRepository.deleteById(userId);
			}
			return DELETE_SUCCESS_MESSAGE;
		} catch (UserException ex) {
			throw new UserException(ex.getMessage(), ex.getStatusCode());
		} catch (Exception ex) {
			throw new UserException(ex.getMessage(), ex.getCause(), StatusCode.GENERIC_EXCEPTION);
		}
	}

	private void checkMobileNumbersValidation(List<UserEntity> users) throws UserException {
		long countOfInvalidNumber = users.stream().filter(user -> user.getMobileNumber().length() < 10).count();
		if (countOfInvalidNumber > 0) {
			throw new UserException(INVALID_MOBILE_NUMBER, StatusCode.BAD_REQUEST);
		}

	}

	private void checkUsersAreValid(List<Long> userIds) throws UserException {
		for (Long userId : userIds) {
			if (!userRepository.existsById(userId)) {
				throw new UserException(INVALID_USER_ID, StatusCode.BAD_REQUEST);
			}
		}
	}

	private void checkUsersDOB(List<Date> dates) throws UserException {
		Calendar cal = Calendar.getInstance();
		Date todayDate = cal.getTime();
		for (Date date : dates) {
			if (date.compareTo(todayDate) > 0) {
				throw new UserException(INVALID_DOB, StatusCode.BAD_REQUEST);
			}
		}

	}

	private List<UserEntity> autoFillingValues(List<UserEntity> users) {
		for (UserEntity user : users) {
			UserEntity userDetail = userRepository.findById(user.getUserId()).get();
			if (user.getfName() == null) {
				user.setfName(userDetail.getfName());
			}

			if (user.getlName() == null) {
				user.setlName(userDetail.getlName());
			}

			if (user.getDob() == null) {
				user.setDob(userDetail.getDob());
			}

			if (user.getMobileNumber() == null) {
				user.setMobileNumber(userDetail.getMobileNumber());
			}
		}
		return users;
	}

}
