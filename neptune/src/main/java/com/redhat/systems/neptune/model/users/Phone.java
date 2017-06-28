package com.redhat.systems.neptune.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "PHONES_SEQ", sequenceName = "PHONES_SEQUENCE", allocationSize = 1, initialValue = 1)
@Table(name = "PHONES")
public class Phone {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PHONES_SEQ")
	@Column(name = "phone_id")
	private Long phoneId;

	@Column(name = "phone")
	private String phone;

	@Column(name = "category")
	private String category;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

}
