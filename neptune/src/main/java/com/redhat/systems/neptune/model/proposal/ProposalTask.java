package com.redhat.systems.neptune.model.proposal;

import org.kie.api.task.model.TaskSummary;

public class ProposalTask {

	private TaskSummary task;
	private Proposal proposal;

	public TaskSummary getTask() {
		return task;
	}

	public void setTask(TaskSummary task) {
		this.task = task;
	}

	public Proposal getProposal() {
		return proposal;
	}

	public void setProposal(Proposal proposal) {
		this.proposal = proposal;
	}

}
