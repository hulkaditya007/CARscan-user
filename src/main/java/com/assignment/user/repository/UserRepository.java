package com.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.user.model.UserEntity;

/**
 * This is the Repository class which will interact with database. This class
 * extends a JpaRepository, which will help to perform all the database
 * operations.
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
