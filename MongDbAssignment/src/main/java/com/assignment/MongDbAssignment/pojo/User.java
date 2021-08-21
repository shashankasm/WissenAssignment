package com.assignment.MongDbAssignment.pojo;

import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "User")
public class User {

	@Id
	private String id;

	@Indexed(unique = true)
	@NotNull(message = "userId is mandatory")
	@Min(value = 1, message = "userId is mandatory")
	private int userId;
	
	@NotBlank(message = "Name is mandatory")
	private String name;

	@Pattern(regexp="(^$|[0-9]{10})", message = "Phone no is invalid")
	private  String phoneNo;

	@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email Id is invalid")
	private  String emailId;

	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent(message = "Dob must not be a future date")
	private  Date dateOfBirth;
	
	public User() {
		super();
	}

	public User(@NotNull(message = "userId is mandatory") @Min(value = 1, message = "userId is mandatory") int userId,
			@NotBlank(message = "Name is mandatory") String name,
			@Pattern(regexp = "(^$|[0-9]{10})", message = "Phone no is invalid") String phoneNo,
			@Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Email Id is invalid") String emailId,
			@PastOrPresent(message = "Dob must not be a future date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth) {
		super();
		this.userId = userId;
		this.name = name;
		this.phoneNo = phoneNo;
		this.emailId = emailId;
		this.dateOfBirth = dateOfBirth;
	}



	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirth, emailId, id, name, phoneNo, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(dateOfBirth, other.dateOfBirth) && Objects.equals(emailId, other.emailId)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(phoneNo, other.phoneNo) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", name=" + name + ", phoneNo=" + phoneNo + ", emailId="
				+ emailId + ", dateOfBirth=" + dateOfBirth + "]";
	}

	
	
}
