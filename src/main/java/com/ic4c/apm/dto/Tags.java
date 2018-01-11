
package com.ic4c.apm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Tags {

	private ResultsJson[] results;
	private String name;
	private AttributesDTO attributes;

	public ResultsJson[] getResults() {
		return results;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResults(ResultsJson[] results) {
		this.results = results;
	}

	public AttributesDTO getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributesDTO attributes) {
		this.attributes = attributes;
	}
}
