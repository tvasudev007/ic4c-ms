package com.ge.predix.solsvc.boot.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TimeSeriesResponse {
	private List<Tags> tags = new ArrayList<>();

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
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
