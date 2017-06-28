package com.redhat.systems.neptune.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import com.redhat.systems.neptune.exceptions.ControllerException;
import com.redhat.systems.neptune.model.users.Role;
import com.redhat.systems.neptune.model.users.User;

public class UserController {
	
	private static final Logger logger = Logger.getLogger(UserController.class);
	
	@Inject
	EntityManager em;
	
	public User getUserByUsername(String username) {
		User user = null;
		Query q = em.createNamedQuery("User.findByUsername", User.class);
		q.setParameter("username", username); 
		try {
			Object result = q.getSingleResult();
			if (result != null) {
				user = (User) result;
			}
		} catch (NoResultException e) {
			logger.warn("No result found", e);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList(String query, int limit, int offset){
		List<User> users = null;
		Query q = null;
		if(query != null && query.length()>0){
			q = em.createNamedQuery("User.listUsersByName",	User.class);
			q.setParameter("name", query.toLowerCase()+"%");
		}else{
			q = em.createNamedQuery("User.listUsers",	User.class);
			q.setFirstResult(offset);
		}
		q.setMaxResults(limit);
		try {
			Object result = q.getResultList();
			if (result != null) {
				users = (List<User>) result;
			}
		} catch (NoResultException e) {
			logger.warn("No result found", e);
		}
		return users;
	}
	
	public User getUserByUserId(Long userId){
		User user = null;
		Query q = em.createNamedQuery("User.findByUserId",	User.class);
		q.setParameter("userId", userId);
		try {
			Object result = q.getSingleResult();
			if (result != null) {
				user = (User) result;
			}
		} catch (NoResultException e) {
			logger.warn("No result found", e);
		}
		return user;
	}
	
	public long countUsers(){
		Long count = null;
		Query q = em.createQuery("SELECT COUNT(u.userId) FROM User u");
		try {
			count = (Long)q.getSingleResult();
		} catch (NoResultException e) {
			logger.warn("No result found", e);
		}
		return count;
	}
	
	public User getUserByUsernameAndPassword(String username, String password) {
		User user = null;
		Query q = em.createNamedQuery("User.findByUsernameAndPassword",	User.class);
		q.setParameter("username", username);
		q.setParameter("password", password); 
		try {
			Object result = q.getSingleResult();
			if (result != null) {
				user = (User) result;
			}
		} catch (NoResultException e) {
			logger.warn("No result found", e);
		}
		return user;
	}

	@Transactional
	public void createUser(User user) throws ControllerException {
		user.setActive(true);
		Role role = new Role();
		role.setRoleId(1l);
		Set<Role> roles =  new HashSet<Role>();
		roles.add(role);
		user.setRoles(roles);
		em.persist(user);
	}

}
