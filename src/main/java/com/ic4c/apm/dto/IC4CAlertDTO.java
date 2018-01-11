package com.ic4c.apm.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IC4CAlertDTO {

	private Date date;
	private String statusCode;
	private String assetId;
	private String dateString;
	private String assetModel;
	
	public IC4CAlertDTO() {
		super();
	}
	
	
	public IC4CAlertDTO(Date date, String statusCode, String assetId, String dateString, String assetModel) {
		super();
		this.date = date;
		this.statusCode = statusCode;
		this.assetId = assetId;
		this.dateString = dateString;
		this.assetModel = assetModel;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatusLevel() {
		return statusCode;
	}

	public void setStatusLevel(String statusLevel) {
		this.statusCode = statusLevel;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getAssetModel() {
		return assetModel;
	}

	public void setAssetModel(String assetModel) {
		this.assetModel = assetModel;
	}

	

}
