package com.ic4c.apm.dto;

import java.util.List;

public class TimeSeriesIngestionDTO {

	private String messgaeId;
	
	private List<TagIngestDTO> body;

	public String getMessgaeId() {
		return messgaeId;
	}

	public void setMessgaeId(String messgaeId) {
		this.messgaeId = messgaeId;
	}

	public List<TagIngestDTO> getBody() {
		return body;
	}

	public void setBody(List<TagIngestDTO> body) {
		this.body = body;
	}
 
}

