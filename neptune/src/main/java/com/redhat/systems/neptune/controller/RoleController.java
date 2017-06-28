package com.redhat.systems.neptune.controller;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.redhat.systems.neptune.model.users.Role;

public class RoleController {

	private static final Logger logger = Logger.getLogger(RoleController.class);
	
	@Inject
	EntityManager em;

	public Role findRoleById(Long id) {
		Role role = null;
		Query q = em.createNamedQuery("Role.findRoleByRoleId", Role.class);
		q.setParameter("roleId", id);
		try {
			Object result = q.getSingleResult();
			if (result != null) {
				role = (Role) result;
			}
		} catch (NoResultException e) {
			logger.warn("No result found", e);
		}
		return role;
	}

	@SuppressWarnings("unchecked")
	public List<Role> getRoleList() {
		List<Role> roles = null;
		Query q = em.createNamedQuery("Role.findRoles", Role.class);
		roles = q.getResultList();
		return roles;
	}

}
