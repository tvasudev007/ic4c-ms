package com.ic4c.apm.dto;

import java.util.List;

public class TimeSeriesRequestLimitedValuesDTO {

	private String start;
	
	private List<TagsRequestDTO> tags;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public List<TagsRequestDTO> getTags() {
		return tags;
	}

	public void setTags(List<TagsRequestDTO> tags) {
		this.tags = tags;
	}
}
