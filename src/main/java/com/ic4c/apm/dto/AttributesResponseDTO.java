package com.ic4c.apm.dto;

import java.util.List;

public class AttributesResponseDTO {
	
	
	private List<String> host;
	
	private List<String> customer;
	
	public AttributesResponseDTO(){
		
	}

	public List<String> getHost() {
		return host;
	}

	public void setHost(List<String> host) {
		this.host = host;
	}

	public List<String> getCustomer() {
		return customer;
	}

	public void setCustomer(List<String> customer) {
		this.customer = customer;
	}


}
