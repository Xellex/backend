package com.example.backend.model;

public class Email {
	
	private String firstname;
	private String lastname;
    private String to;
    private String phone;
    private String subject;
    private String message;

	public Email(String firstname, String lastname, String to, String phone, String subject, String message) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.to = to;
		this.phone = phone;
		this.subject = subject;
		this.message = message;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}