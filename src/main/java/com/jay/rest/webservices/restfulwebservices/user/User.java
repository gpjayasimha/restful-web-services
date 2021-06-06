package com.jay.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

	@Size(min=3, message="Name must be minimum 3 characters")
	String name;
	Integer id;
	@Past
	@JsonIgnore
	Date dob;

	public User(String name, Integer id, Date dob) {
		super();
		this.name = name;
		this.id = id;
		this.dob = dob;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", dob=" + dob + "]";
	}

}
