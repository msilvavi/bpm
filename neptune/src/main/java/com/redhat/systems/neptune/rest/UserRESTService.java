package com.redhat.systems.neptune.rest;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import com.redhat.systems.neptune.controller.UserController;
import com.redhat.systems.neptune.dto.ListResponse;
import com.redhat.systems.neptune.exceptions.ControllerException;
import com.redhat.systems.neptune.model.users.User;
import com.redhat.systems.neptune.util.Roles;

@RolesAllowed(Roles.REST_ALL)
@Path("/users")
public class UserRESTService { 

//	@SuppressWarnings("cdi-ambiguous-dependency")
//	@Inject  
//	private transient Logger logger;
	
	private static final Logger logger = Logger.getLogger(UserRESTService.class);

	@Inject
	UserController userController;

	@GET
	@Path("/login/info")
	@Produces(MediaType.APPLICATION_JSON)
	public User info(@Context HttpServletRequest req) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		return user;
	}
	
	@GET
	@Path("/info/{userId:[0-9][0-9]*}")
	@RolesAllowed(Roles.USERS_ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public User info(@PathParam(value="userId") Long userId) {
		User user = userController.getUserByUserId(userId);
		return user;
	}

	@GET
	@Path("/list")
	@RolesAllowed(Roles.USERS_ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse listUsers(@QueryParam("queries[search]") String query,
								  @QueryParam("page") int page,
								  @QueryParam("perPage") int perPage,
								  @QueryParam("offset") int offset,
								  @Context HttpServletRequest req,
								  @Context UriInfo uriInfo) {
		logger.debug("query: "+query);
		logger.debug("page: "+page);
		logger.debug("perPage: "+perPage);
		logger.debug("offset: "+offset);
		List<User> users = userController.getUserList(query, perPage, offset);
		Long total =  userController.countUsers();
		ListResponse response = new ListResponse();
		response.setRecords(users);
		response.setQueryRecordCount(users.size());
		response.setTotalRecordCount(total.intValue());
		return response;
	}
	
	@POST
	@Path("/create")
	@RolesAllowed(Roles.USERS_ADMIN)
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Boolean create(User user) {
		boolean created = false;
		logger.info("Creating user: " + user.getName());
		try {
			userController.createUser(user);
			created = true;
		} catch (ControllerException e) {
			logger.error("Error creating user",e);
		}
		return created;
	}

}
