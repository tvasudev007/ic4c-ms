/**
 * 
 */
package com.ic4c.apm.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class IC4CAssetParametersDTO {
	
	public IC4CAssetParametersDTO(){
		
	}

	public IC4CAssetParametersDTO(String pinType, String tagType, String tag, String uom, String binary,
			String multiplier, float low, float high, double value, long timestamp) {
		super();
		this.pinType = pinType;
		this.tagType = tagType;
		this.tag = tag;
		this.uom = uom;
		this.binary = binary;
		this.multiplier = multiplier;
		this.low = low;
		this.high = high;
		this.value = value;
		this.timestamp = timestamp;
	}

	private String pinType;
	private String tagType;
	private String tag;
	private String uom;
	private String binary;
	private String multiplier;
	private float low;
	private float high;
	private double value;
	private long timestamp;

	public String getPinType() {
		return pinType;
	}

	public void setPinType(String pinType) {
		this.pinType = pinType;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}

	public String getBinary() {
		return binary;
	}

	public void setBinary(String binary) {
		this.binary = binary;
	}

	public String getMultiplier() {
		return multiplier;
	}

	public void setMultiplier(String multiplier) {
		this.multiplier = multiplier;
	}

	public float getLow() {
		return low;
	}

	public void setLow(float low) {
		this.low = low;
	}

	public float getHigh() {
		return high;
	}

	public void setHigh(float high) {
		this.high = high;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

}
