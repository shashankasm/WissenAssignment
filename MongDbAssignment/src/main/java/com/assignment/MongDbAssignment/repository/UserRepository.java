package com.assignment.MongDbAssignment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.assignment.MongDbAssignment.pojo.User;

public interface UserRepository extends MongoRepository<User, String> {
	List<User> findByNameContaining(String name);

	void deleteByName(String id);

	Optional<User> findByUserId(int userId);
}
