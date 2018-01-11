
package com.ic4c.apm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class TagsForAlerts {

	private ResultsJsonForAlerts[] results;
	private String name;
	
	public ResultsJsonForAlerts[] getResults() {
		return results;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResults(ResultsJsonForAlerts[] results) {
		this.results = results;
	}
}
