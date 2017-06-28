package com.redhat.systems.neptune.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;

import com.redhat.systems.neptune.controller.RoleController;
import com.redhat.systems.neptune.model.users.Role;
import com.redhat.systems.neptune.util.Roles;

@RolesAllowed(Roles.USERS_ADMIN)
@Path("/roles")
public class RoleRESTService {

//	@SuppressWarnings("cdi-ambiguous-dependency")
//	@Inject  
//	private transient Logger logger;
	
	private static final Logger logger = Logger.getLogger(RoleRESTService.class);

	@Inject
	RoleController roleController;

	@Context
	ServletContext ctx;
	@Context
	private HttpServletResponse response;
	@Context
	private HttpServletRequest request;

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	@HEAD()
	public List<Role> listRoles() {
		logger.info("Aquiring role list");
		List<Role> roles = roleController.getRoleList();
		return roles;
	}
}
