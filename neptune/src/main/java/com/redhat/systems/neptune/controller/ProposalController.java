package com.redhat.systems.neptune.controller;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.jboss.logging.Logger;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;

import com.redhat.systems.neptune.dto.Variable;
import com.redhat.systems.neptune.model.proposal.Proposal;
import com.redhat.systems.neptune.model.proposal.ProposalTask;
import com.redhat.systems.neptune.util.JbpmBridge;
import com.redhat.systems.neptune.util.ProposalConverter;

public class ProposalController {

	private static final Logger logger = Logger.getLogger(ProposalController.class);

	@Inject
	EntityManager em;

	JbpmBridge jbpmBridget = new JbpmBridge();

	public long createProposal(Proposal proposal, String starterUser, String password){
		logger.debug("Creating proposal: " + proposal.getName());
		proposal.setCreation(new Date());
		Map<String, Object> params = ProposalConverter.convertToParams(proposal);
		long processId = jbpmBridget.startProcess(starterUser, password, params);
		return processId;
	}
	
	public List<TaskSummary> getTasks(String username, String password){
		List<TaskSummary> tasks = null;
		try {
			tasks = jbpmBridget.getUserTasks(username, password);
		} catch (MalformedURLException e) {
			logger.error("Error while getting user tasks",e);
		}
		return tasks;
	}
	
	public List<ProposalTask> getTasksWithVariables(String username, String password){
		List<ProposalTask> proposalTasks = new ArrayList<ProposalTask>();
		List<TaskSummary> tasks = null;
		try {
			tasks = jbpmBridget.getUserTasks(username, password);
			for(TaskSummary task: tasks){
				ProposalTask ptask = new ProposalTask();
				Map<String, String> params = getProcessVariables(task.getProcessInstanceId().intValue(), username, password);
				Proposal prop = ProposalConverter.convertFromStringsParams(params);
				ptask.setProposal(prop);
				ptask.setTask(task);
				proposalTasks.add(ptask);
			}
		} catch (MalformedURLException e) {
			logger.error("Error while getting user tasks",e);
		}
		return proposalTasks;
	}
	
	public Proposal getProposalWithVariables(String username, String password, Proposal proposal){
		Task task = null;
		try {
			task = jbpmBridget.getTask(proposal.getTaskId(), username, password);
			int processId = (int)task.getTaskData().getProcessInstanceId();
			Map<String, String> params = getProcessVariables(processId, username, password);
			proposal = ProposalConverter.convertFromStringsParams(params);
		} catch (MalformedURLException e) {
			logger.error("Error while getting user tasks",e);
		}
		return proposal;
	}
	
	public List<Variable> getProposalVariables(String username, String password, Proposal proposal){
		Task task = null;
		Map<String, String> params = new HashMap<String, String>();
		List<Variable> variables = new ArrayList<Variable>();
		try {
			task = jbpmBridget.getTask(proposal.getTaskId(), username, password);
			int processId = (int)task.getTaskData().getProcessInstanceId();
			params = getProcessVariables(processId, username, password);		
		} catch (MalformedURLException e) {
			logger.error("Error while getting user tasks",e);
		}
		variables = ProposalConverter.convertMapToVariables(params);
		return variables;
	}
	
	public Task getTask(int taskId, String username, String password){
		Task task = null;
		try {
			task = jbpmBridget.getTask(taskId, username, password);
		} catch (MalformedURLException e) {
			logger.error("Error while getting task",e);
		}
		return task;
	}
	
	public Map<String,String> getProcessVariables(int processId, String username, String password){
		Map<String,String> processVariables = null;
		try {
			processVariables = jbpmBridget.getProcessVariables(processId, username, password);
		} catch (MalformedURLException e) {
			logger.error("Error while getting task",e);
		}
		return processVariables;
	}
	
	public Map<String,Object> getTaskContent(int taskId, String username, String password){
		Map<String, Object> content = null;
		try {
			content = jbpmBridget.getTaskContent(taskId, username, password);
		} catch (MalformedURLException e) {
			logger.error("Error while getting task content",e);
		}
		return content;
	}
	
	public boolean completeTask(String username, String password, Proposal proposal){
		boolean completed = false;
		Map<String, Object> params = ProposalConverter.convertToParams(proposal);
		try {
			jbpmBridget.completeTask(proposal.getTaskId(), username, password, params);
			completed = true;
		} catch (MalformedURLException e) {
			logger.error("Error while completing task",e);
		}
		return completed;
	}
}
