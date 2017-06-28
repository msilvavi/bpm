package com.redhat.systems.neptune.model.users;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQUENCE", allocationSize = 1, initialValue = 1)
@Table(name = "COMPANIES")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPANY_SEQ")
	@Column(name = "company_id")
	private Long companyId;

	@NotNull
	@Size(min = 1, max = 50, message = "1-50 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "company_name")
	private String name;

	@Size(min = 1, max = 13, message = "1-13 letters and spaces")
	@Column(name = "rfc")
	private String rfc;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "company_emails", joinColumns = { @JoinColumn(name = "company_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "email_id", nullable = false, updatable = false) })
	@Column(name = "emails")
	private Set<Email> emails;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "company_phones", joinColumns = { @JoinColumn(name = "company_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "phone_id", nullable = false, updatable = false) })
	@Column(name = "phones")
	private Set<Phone> phones;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "company_addresses", joinColumns = { @JoinColumn(name = "company_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "address_id", nullable = false, updatable = false) })
	@Column(name = "addresses")
	private Set<Address> addresses;

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Set<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Set<Address> addresses) {
		this.addresses = addresses;
	}

}
