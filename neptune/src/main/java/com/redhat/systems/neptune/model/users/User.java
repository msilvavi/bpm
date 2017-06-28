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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
		@NamedQuery(name = "User.findByUsernameAndPassword", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
		@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
		@NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
		@NamedQuery(name = "User.listUsers", query = "SELECT u FROM User u"),
		@NamedQuery(name = "User.listUsersByName", query = "SELECT u FROM User u where lower(u.name) LIKE :name")

})
@SequenceGenerator(name = "USER_SEQ", sequenceName = "USERS_SEQUENCE", allocationSize = 1, initialValue = 1)
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@Column(name = "user_id")
	private Long userId;

	@NotNull
	@Size(min = 1, max = 50, message = "1-50 letters and spaces")
	@Column(name = "username")
	private String username;

	@NotNull
	@Size(min = 1, max = 50, message = "1-50 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "name")
	private String name;

	@NotNull
	@Size(min = 1, max = 50, message = "1-50 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "surname")
	private String surname;

	@NotNull
	@Size(min = 1, max = 50, message = "1-50 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "lastname")
	private String lastname;

	@NotNull
	@Size(min = 1, max = 80, message = "1-80 letters and spaces")
	@Column(name = "password")
	private String password;

	@NotNull
	@Column(name = "active")
	private Boolean active;

	@NotNull
	@Column(name = "fullname")
	private String fullname;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_emails", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "email_id", nullable = false, updatable = false) })
	@Column(name = "emails")
	private Set<Email> emails;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_phones", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "phone_id", nullable = false, updatable = false) })
	@Column(name = "phones")
	private Set<Phone> phones;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_addresses", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "address_id", nullable = false, updatable = false) })
	@Column(name = "addresses")
	private Set<Address> addresses;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "user_roles", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", nullable = false, updatable = false) })
	private Set<Role> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<Email> getEmails() {
		return emails;
	}

	public void setEmails(Set<Email> emails) {
		this.emails = emails;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
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

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

}
