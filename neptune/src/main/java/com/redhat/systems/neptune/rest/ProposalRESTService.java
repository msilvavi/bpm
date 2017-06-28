package com.redhat.systems.neptune.rest;

import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.jboss.logging.Logger;
import org.kie.api.task.model.TaskSummary;

import com.redhat.systems.neptune.controller.ProposalController;
import com.redhat.systems.neptune.controller.UserController;
import com.redhat.systems.neptune.dto.ListResponse;
import com.redhat.systems.neptune.dto.Variable;
import com.redhat.systems.neptune.model.proposal.Proposal;
import com.redhat.systems.neptune.model.proposal.ProposalTask;
import com.redhat.systems.neptune.model.users.User;
import com.redhat.systems.neptune.util.Roles;

@RolesAllowed(Roles.REST_ALL)
@Path("/proposals")
public class ProposalRESTService {

	private static final Logger logger = Logger.getLogger(UserRESTService.class);

	@Inject
	ProposalController proposalController;

	@Inject
	UserController userController;

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public long create(Proposal proposal, @Context HttpServletRequest req) {
		logger.info("Creating Proposal: " + proposal.getCode());
		long processId = -1;
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		processId = proposalController.createProposal(proposal, user.getUsername(), user.getPassword());
		logger.info("Proposal created: "+proposal.getCode()+" with ID: " + processId);
		return processId;
	}

	@GET
	@Path("/tasks")
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse getUserTasks(@Context HttpServletRequest req) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		logger.info("Getting user tasks: "+user.getUsername());
		List<TaskSummary> tasks = proposalController.getTasks(user.getUsername(), user.getPassword());
		ListResponse response = new ListResponse();
		response.setRecords(tasks);
		response.setQueryRecordCount(tasks.size());
		response.setTotalRecordCount(tasks.size());
		logger.info("User tasks returned");
		return response;
	}
	
	@GET
	@Path("/proposals")
	@Produces(MediaType.APPLICATION_JSON)
	public ListResponse getUserProposals(@Context HttpServletRequest req) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		logger.info("Getting user tasks: "+user.getUsername());
		List<ProposalTask> tasks = proposalController.getTasksWithVariables(user.getUsername(), user.getPassword());
		ListResponse response = new ListResponse();
		response.setRecords(tasks);
		response.setQueryRecordCount(tasks.size());
		response.setTotalRecordCount(tasks.size());
		logger.info("User tasks returned");
		return response;
	}
	
	@GET
	@Path("/proposal-info/{taskId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Proposal getProposalInfoFromTask(@Context HttpServletRequest req, @PathParam(value="taskId") int taskId) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		logger.info("Getting user tasks: "+user.getUsername());
		Proposal proposal  = new Proposal();
		proposal.setTaskId(taskId);
		proposal = proposalController.getProposalWithVariables(user.getUsername(), user.getPassword(), proposal);
		logger.info("Proposal returned");
		return proposal;
	}
	
	@GET
	@Path("/proposal-variables/{taskId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Variable> getProposalVariablesFromTask(@Context HttpServletRequest req, @PathParam(value="taskId") int taskId) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		logger.info("Getting user tasks: "+user.getUsername());
		Proposal proposal  = new Proposal();
		proposal.setTaskId(taskId);
		List<Variable> varaibles = proposalController.getProposalVariables(user.getUsername(), user.getPassword(), proposal);
		logger.info("Variables returned");
		return varaibles;
	}
	
	@GET
	@Path("/tasks/{taskId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,Object> getTaskContent(@PathParam(value="taskId") int taskId, @Context HttpServletRequest req) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		proposalController.getTaskContent(taskId, user.getUsername(), user.getPassword());
		Map<String,Object> content = proposalController.getTaskContent(taskId, user.getUsername(), user.getPassword());
		return content;
	}
	
	@GET
	@Path("/variables/{processId:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String,String> amReview(@PathParam(value="processId") int processId, @Context HttpServletRequest req) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		Map<String,String> processData = proposalController.getProcessVariables(processId, user.getUsername(), user.getPassword());
		return processData;
	}
	
	@POST
	@Path("/complete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public boolean completeTask(Proposal proposal, @Context HttpServletRequest req) {
		User user = userController.getUserByUsername(req.getUserPrincipal().toString());
		boolean completed = proposalController.completeTask(user.getUsername(), user.getPassword(), proposal);
		return completed;
	}
}
