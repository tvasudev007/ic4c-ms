package com.ic4c.apm.dto;

import java.util.List;

public class TagIngestDTO {
	
	private String name;
	private List<List<Long>> datapoints;
	private AttributesDTO attributes;
	
	public TagIngestDTO(){
		
	}
	
	public TagIngestDTO(String name, List<List<Long>> datapoints, AttributesDTO attributes) {
		super();
		this.name = name;
		this.datapoints = datapoints;
		this.attributes = attributes;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<List<Long>> getDatapoints() {
		return datapoints;
	}
	public void setDatapoints(List<List<Long>> datapoints) {
		this.datapoints = datapoints;
	}
	public AttributesDTO getAttributes() {
		return attributes;
	}
	public void setAttributes(AttributesDTO attributes) {
		this.attributes = attributes;
	}

}


