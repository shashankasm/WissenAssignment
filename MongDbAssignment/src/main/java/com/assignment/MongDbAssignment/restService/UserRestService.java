package com.assignment.MongDbAssignment.restService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.MongDbAssignment.pojo.User;
import com.assignment.MongDbAssignment.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class UserRestService {

	@Autowired
	UserRepository repository; 

	Logger logger = LoggerFactory.getLogger(UserRestService.class);

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String name) {
		try {
			List<User> users = new ArrayList<User>();

			repository.findAll().forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in createUser method : ",e);
			throw e;
		}
	}

	
	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserByUserId(@PathVariable("userId") int userId) {
		try {
			Optional<User> user = repository.findByUserId(userId);

			if (user.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getUserByName method : ",e);
			throw e;
		}
	}


	@GetMapping("/user/name={name}")
	public ResponseEntity<List<User>> getUserByName(@PathVariable("name") String name) {
		try {
			List<User> user = repository.findByNameContaining(name);

			if (user.isEmpty()) {
				return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error in getUserByName method : ",e);
			throw e;
		}
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User u = repository.save(new User(user.getUserId(),user.getName(),user.getPhoneNo(),user.getEmailId(),user.getDateOfBirth()));
			return new ResponseEntity<>(u, HttpStatus.CREATED);
		} catch (ConstraintViolationException e) {
			logger.error("Error in createUser method : ConstraintViolationException : ",e);
			throw e;
		} catch (DuplicateKeyException e) {
			logger.error("Error in createUser method : DuplicateKeyException : ",e);
			throw e;
		} catch (Exception e) {
			logger.error("Error in createUser method : ",e);
			throw e;
		}
	}

	@PutMapping("/user/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable("userId") int userId, @RequestBody User user) {
		try {
			Optional<User> userData = repository.findByUserId(userId);

			if (userData.isPresent()) {
				User userT = userData.get();
				userT.setUserId(user.getUserId());
				userT.setName(user.getName());
				userT.setPhoneNo(user.getPhoneNo());
				userT.setEmailId(user.getEmailId());
				userT.setDateOfBirth(user.getDateOfBirth());
				return new ResponseEntity<>(repository.save(userT), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch (ConstraintViolationException e) {
			logger.error("Error in updateUser method : ConstraintViolationException : ",e);
			throw e;
		} catch (DuplicateKeyException e) {
			logger.error("Error in updateUser method : DuplicateKeyException : ",e);
			throw e;
		} catch (Exception e) {
			logger.error("Error in updateUser method : ",e);
			throw e;
		}
	}

	@DeleteMapping("/user/{name}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String name) {
		try {
			repository.deleteByName(name);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("Error in deleteUser method : ",e);
			throw e;
		}
	}

	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			repository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error("Error in deleteAllUsers method : ",e);
			throw e;
		}
	}

}
