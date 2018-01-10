package com.ic4c.apm.dto;

public class TagsRequestDTO {
	
	public TagsRequestDTO() {
		super();

	}
	
	public TagsRequestDTO(String name, String order, int limit) {
		super();
		this.name = name;
		this.order = order;
		this.limit = limit;
	}
	private String name;
    private String order;
    private int limit;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
