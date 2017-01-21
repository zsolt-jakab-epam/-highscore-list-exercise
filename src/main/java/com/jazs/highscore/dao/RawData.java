package com.jazs.highscore.dao;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RawData {
	
	private Integer id;
	private String label;
	private Float value;
	
	@JsonCreator
	public RawData(Integer id, String label, Float value) {
		this.id = id;
		this.label = label;
		this.value = value;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Line [id=" + id + ", label=" + label + ", value=" + value + "]";
	}
}
