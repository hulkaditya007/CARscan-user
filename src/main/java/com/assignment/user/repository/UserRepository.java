package com.assignment.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignment.user.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
