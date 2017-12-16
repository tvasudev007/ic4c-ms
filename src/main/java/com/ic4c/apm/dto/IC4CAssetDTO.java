/**
 * 
 */
package com.ic4c.apm.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class IC4CAssetDTO {
	
	private String uri;
	private String classification;
	private String parent;
	private String id;
	private String complexType;
	private String description;
	private Map<String,String> attributes;
	private Map<String,IC4CAssetParametersDTO> parameters;

	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComplexType() {
		return complexType;
	}
	public void setComplexType(String complexType) {
		this.complexType = complexType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Map<String, String> getAttributes() {
		return attributes;
	}
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	public Map<String, IC4CAssetParametersDTO> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, IC4CAssetParametersDTO> parameters) {
		this.parameters = parameters;
	}
	
}
