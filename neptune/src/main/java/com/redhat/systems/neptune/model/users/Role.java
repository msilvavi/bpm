package com.redhat.systems.neptune.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name = "Role.findByRoleRoleId", query = "SELECT r FROM Role r WHERE r.roleId = :roleId"),
	@NamedQuery(name = "Role.findRoles", query = "SELECT r FROM Role r")
})
@SequenceGenerator(name="ROLES_SEQ", sequenceName="ROLES_SEQUENCE",allocationSize=1,initialValue=1)
@Table(name = "ROLES")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ROLES_SEQ")
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "role_name")
	private String name;

	@Column(name = "role_description")
	private String description;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
