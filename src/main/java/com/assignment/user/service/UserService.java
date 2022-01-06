package com.assignment.user.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.user.exception.StatusCode;
import com.assignment.user.exception.UserException;
import com.assignment.user.intf.UserOperationInterface;
import com.assignment.user.model.User;
import com.assignment.user.repository.UserRepository;

@Service
public class UserService implements UserOperationInterface {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> createUsers(List<User> users) throws UserException {
		try {
			checkUsersDOB(users.stream().map(user -> user.getDob()).collect(Collectors.toList()));
			checkMobileNumbersValidation(users);
			for (User user : users) {
				userRepository.saveAndFlush(user);
			}
			return users;
		} catch (UserException ex) {
			throw new UserException(ex.getMessage(), ex.getStatusCode());
		} catch (Exception ex) {
			throw new UserException(ex.getMessage(), ex.getCause(), StatusCode.GENERIC_EXCEPTION);
		}
	}

	@Override
	public List<User> getUsers() throws UserException {
		return userRepository.findAll();
	}

	@Override
	public List<User> updateUsers(List<User> users) throws UserException {
		try {
			checkUsersAreValid(users.stream().map(user -> user.getUserId()).collect(Collectors.toList()));
			checkMobileNumbersValidation(users);
			users = autoFillingValues(users);
			checkUsersDOB(users.stream().map(user -> user.getDob()).collect(Collectors.toList()));
			for (User user : users) {
				userRepository.saveAndFlush(user);
			}
			return users;
		} catch (UserException ex) {
			throw new UserException(ex.getMessage(), ex.getStatusCode());
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
			return "All Users deleted succesfully";
		} catch (UserException ex) {
			throw new UserException(ex.getMessage(), ex.getStatusCode());
		} catch (Exception ex) {
			throw new UserException(ex.getMessage(), ex.getCause(), StatusCode.GENERIC_EXCEPTION);
		}
	}

	private void checkMobileNumbersValidation(List<User> users) throws UserException {
		long countOfInvalidNumber = users.stream().filter(user -> user.getMobileNumber().length() < 10).count();
		if (countOfInvalidNumber > 0) {
			throw new UserException("Invalid number in this list", StatusCode.BAD_REQUEST);
		}
	}

	private void checkUsersAreValid(List<Long> userIds) throws UserException {
		for (Long userId : userIds) {
			if (!userRepository.existsById(userId)) {
				throw new UserException("Invalid user Id in the list", StatusCode.BAD_REQUEST);
			}
		}
	}

	private void checkUsersDOB(List<Date> dates) throws UserException {
		Calendar cal = Calendar.getInstance();
		Date todayDate = cal.getTime();
		for (Date date : dates) {
			if (date.compareTo(todayDate) > 0) {
				throw new UserException("Date is Invalid! And I dont beleive in time travelling.",
						StatusCode.BAD_REQUEST);
			}
		}

	}

	private List<User> autoFillingValues(List<User> users) {
		for (User user : users) {
			User userDetail = userRepository.getById(user.getUserId());
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
