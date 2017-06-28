package com.redhat.systems.neptune.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.audit.AuditService;
import org.kie.api.runtime.manager.audit.VariableInstanceLog;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.Task;
import org.kie.api.task.model.TaskSummary;
import org.kie.remote.client.api.RemoteRuntimeEngineFactory;

public class JbpmBridge {

	private static Logger logger = Logger.getLogger(JbpmBridge.class);
	
	private String deploymentID = "RedHat:Neptuno:1.0.8";
	private String businessCentralURL = "http://localhost:8180/business-central/";
	private String processId = "Neptuno.CreacionPropuesta";
	
	public RuntimeEngine getRuntimeEngine(String userId, String password) throws MalformedURLException {
		RuntimeEngine engine = RemoteRuntimeEngineFactory.newRestBuilder()
			    .addUrl(new URL(businessCentralURL))
			    .addUserName(userId).addPassword(password)
			    .addDeploymentId(deploymentID)
			     .build();
		
		return engine;
	}

	public String getDeploymentID() {
		return deploymentID;
	}

	public String getBusinessCentralURL() {
		return businessCentralURL;
	}

	public String getProcessId() {
		return processId;
	}
	
	public long startProcess(String starterUser, String password,
			Map<String, Object> params) {
		long procId = -1;
		try {
			RuntimeEngine engine = getRuntimeEngine(starterUser, password);
			KieSession kSession = engine.getKieSession();
			ProcessInstance instance = null;
			if (params != null) {
				instance = kSession.startProcess(processId, params);
			} else {
				instance = kSession.startProcess(processId);
			}
			procId = instance.getId();
		} catch (MalformedURLException e) {
			logger.error("Business Central URL connection is malformed",e);
		}
		return procId;
	}

	public void completeTask(int taskId, String taskUserId, String password,
			Map<String, Object> params) throws MalformedURLException {
		RuntimeEngine engine = getRuntimeEngine(taskUserId, password);
		TaskService taskService = engine.getTaskService();

		taskService.start(taskId, taskUserId);
		if (params != null) {
			taskService.complete(taskId, taskUserId, params);
		} else {
			taskService.complete(taskId, taskUserId, null);
		}
	}

	public ArrayList<TaskSummary> getUserTasks(String taskUserId,
			String password) throws MalformedURLException {
		RuntimeEngine engine = getRuntimeEngine(taskUserId, password);
		TaskService taskService = engine.getTaskService();
		ArrayList<TaskSummary> al = (ArrayList<TaskSummary>) taskService
				.getTasksAssignedAsPotentialOwner(taskUserId, "en-UK");
		return al;
	}
	
	public Task getTask(int taskId, String taskUserId,
			String password) throws MalformedURLException {
		RuntimeEngine engine = getRuntimeEngine(taskUserId, password);
		TaskService taskService = engine.getTaskService();
		Task task = taskService.getTaskById(taskId);
		return task;
	}

//	@SuppressWarnings("unchecked")
	public Map<String, Object> getTaskContent(long taskId, String taskUserId,
			String password) throws MalformedURLException {
		Map<String, Object> data = null;
		
		RuntimeEngine engine = getRuntimeEngine(taskUserId, password);
		TaskService taskService = engine.getTaskService();
		data = (Map<String,Object>)taskService.getTaskContent(taskId);
		return data;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> getProcessVariables(int processId, String taskUserId,
			String password) throws MalformedURLException {
		RuntimeEngine engine = getRuntimeEngine(taskUserId, password);
		
		AuditService service = engine.getAuditService();
		List<VariableInstanceLog> vars = (List<VariableInstanceLog>)service.findVariableInstances(processId);
		Map<String,String> results = new HashMap<String,String>();
		for(VariableInstanceLog var : vars){
			results.put(var.getVariableId(), var.getValue());
		}
		return results;
	}
}
