package com.assignment.MongDbAssignment;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.assignment.MongDbAssignment.pojo.ApiError;

@ControllerAdvice
public class MongoDbAssignmentApplicationExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
		List<String> details = new ArrayList<String>();
		details.add(e.getMessage());
		ApiError err = new ApiError(HttpStatus.BAD_REQUEST, "Received Invalid Input Parameters", details);
		return new ResponseEntity<>(err, err.getStatus());

	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseEntity<Object> handleDuplicateKeyException(DuplicateKeyException e) {
		List<String> details = new ArrayList<String>();
		details.add(e.getMessage());
		ApiError err = new ApiError(HttpStatus.BAD_REQUEST, "Duplicate key error : userId", details);
		return new ResponseEntity<>(err, err.getStatus());

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception e) {
		List<String> details = new ArrayList<String>();
		details.add(e.getMessage());
		ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Error Occured", details);
		return new ResponseEntity<>(err, err.getStatus());

	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleRuntimeException(RuntimeException e) {
		List<String> details = new ArrayList<String>();
		details.add(e.getMessage());
		ApiError err = new ApiError(HttpStatus.BAD_REQUEST, "Invalid Date Format(yyyy-MM-dd)", details);
		return new ResponseEntity<>(err, err.getStatus());

	}
	
	@ExceptionHandler(ParseException.class)
	public ResponseEntity<Object> handleParseException(ParseException e) {
		List<String> details = new ArrayList<String>();
		details.add(e.getMessage());
		ApiError err = new ApiError(HttpStatus.BAD_REQUEST, "1Invalid Date Format(yyyy-MM-dd)", details);
		return new ResponseEntity<>(err, err.getStatus());

	}
	
}
