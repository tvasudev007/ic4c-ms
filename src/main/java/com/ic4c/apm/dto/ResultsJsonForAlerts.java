/**
 * 
 */
package com.ic4c.apm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsJsonForAlerts {

	private double values[][];
	
	private AttributesResponseDTO attributes;

	public double[][] getValues() {
		return values;
	}

	public void setValues(double[][] values) {
		this.values = values;
	}
	public AttributesResponseDTO getAttributes() {
		return attributes;
	}

	public void setAttributes(AttributesResponseDTO attributes) {
		this.attributes = attributes;
	}

}
