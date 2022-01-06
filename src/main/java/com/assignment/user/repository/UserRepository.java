package com.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
