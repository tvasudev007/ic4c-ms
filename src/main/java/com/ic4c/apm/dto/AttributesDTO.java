package com.ic4c.apm.dto;

public class AttributesDTO {
	
	
	private String host;
	
	private String customer;
	
	public AttributesDTO(){
		
	}
	public AttributesDTO(String host, String customer) {
		super();
		this.host = host;
		this.customer = customer;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}




	

}
