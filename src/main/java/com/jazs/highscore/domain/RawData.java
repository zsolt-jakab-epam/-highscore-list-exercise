package com.jazs.highscore.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RawData {
	
	private Integer id;
	private String label;
	private Float value;
	
	@JsonCreator
	public RawData(@JsonProperty("id") Integer id, @JsonProperty("label") String label, @JsonProperty("value") Float value) {
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
