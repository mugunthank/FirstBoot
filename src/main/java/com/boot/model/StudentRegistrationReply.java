package com.boot.model;

public class StudentRegistrationReply {

	private int age;
	private String name;
	private String registrationNumber;
	private String registrationStatus;

	public int getAge() {
		return this.age;
	}

	public String getName() {
		return this.name;
	}

	public String getRegistrationNumber() {
		return this.registrationNumber;
	}

	public String getRegistrationStatus() {
		return this.registrationStatus;
	}

	public void setAge(final int age) {
		this.age = age;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setRegistrationNumber(final String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public void setRegistrationStatus(final String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
}
