package com.redhat.systems.neptune.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "EMAILS_SEQ", sequenceName = "EMAILS_SEQUENCE", allocationSize = 1, initialValue = 1)
@Table(name = "EMAILS")
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMAILS_SEQ")
	@Column(name = "email_id")
	private Long emailId;

	@Column(name = "email")
	private String email;

	@Column(name = "category")
	private String category;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getEmailId() {
		return emailId;
	}

	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

}
