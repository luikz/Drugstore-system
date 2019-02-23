package com.drugstore.domain;

public class Manufacturer {
	private Long id;
	private String description;
	
	
	@Override
	public String toString() {
		String output = id + " / " + description;
		return output;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
