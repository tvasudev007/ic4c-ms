package com.ic4c.apm.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesResponseForAlerts {
	private List<TagsForAlerts> tags = new ArrayList<>();

	public List<TagsForAlerts> getTags() {
		return tags;
	}

	public void setTags(List<TagsForAlerts> tags) {
		this.tags = tags;
	}

	/*
	 * Tags[] tags;
	 * 
	 * public Tags[] getTags() { return tags; }
	 * 
	 * public void setTags(Tags[] tags) { this.tags = tags; }
	 */

}
