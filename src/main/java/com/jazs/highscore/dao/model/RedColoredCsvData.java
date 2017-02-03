package com.jazs.highscore.dao.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RedColoredCsvData implements ColoredCsvData {

	private Integer id;
	private String label;
	private String color;

	@JsonCreator
	public RedColoredCsvData(Integer id, String label) {
		this.id = id;
		this.label = label;
		this.color = "red";
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public String getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "RedColoredCsvData [id=" + id + ", label=" + label + ", color=" + color + "]";
	}
	
}
