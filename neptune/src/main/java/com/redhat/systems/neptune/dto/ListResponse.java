package com.redhat.systems.neptune.dto;

import java.util.List;

public class ListResponse {

	protected List<?> records;
	protected int queryRecordCount;
	protected int totalRecordCount;

	public List<?> getRecords() {
		return records;
	}

	public void setRecords(List<?> records) {
		this.records = records;
	}

	public int getQueryRecordCount() {
		return queryRecordCount;
	}

	public void setQueryRecordCount(int queryRecordCount) {
		this.queryRecordCount = queryRecordCount;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

}
