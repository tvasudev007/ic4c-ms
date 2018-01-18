/**
 * 
 */
package com.ic4c.apm.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IC4CAssetParametersDTO {

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
	private String tileType;
	private int binaryDigits;
	private List<String> pinNames;
	private int index;


	public IC4CAssetParametersDTO() {

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

	public IC4CAssetParametersDTO(String pinType, String tagType, String tag, String uom, String binary,
			String multiplier, float low, float high, double value, long timestamp,int index) {
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
		this.index=index;

	}

	public IC4CAssetParametersDTO(String pinType, String tagType, String tag, String uom, String binary,
			String multiplier, float low, float high, double value, long timestamp, String tileType, int binaryDigits,
			List<String> pinNames,int index) {
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
		this.tileType = tileType;
		this.binaryDigits = binaryDigits;
		this.pinNames = pinNames;
		this.index =index;
	}

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

	public String getTileType() {
		return tileType;
	}

	public void setTileType(String tileType) {
		this.tileType = tileType;
	}

	public int getBinaryDigits() {
		return binaryDigits;
	}

	public void setBinaryDigits(int binaryDigits) {
		this.binaryDigits = binaryDigits;
	}

	public List<String> getPinNames() {
		return pinNames;
	}

	public void setPinNames(List<String> pinNames) {
		this.pinNames = pinNames;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
