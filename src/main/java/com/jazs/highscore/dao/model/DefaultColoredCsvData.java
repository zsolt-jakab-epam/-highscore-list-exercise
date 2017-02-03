package com.jazs.highscore.dao.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DefaultColoredCsvData implements ColoredCsvData {

	private static final double GREEN_LOWER_LIMIT = 100.0;
	
	private static final double GREEN_UPPER_LIMIT = 200.0;
	
	private Integer id;
	
	private String label;
	
	private String color;

	@JsonCreator
	public DefaultColoredCsvData(Integer id, String label, Double value) {
		this.id = id;
		this.label = label;

		if (value != null && value < GREEN_LOWER_LIMIT) {
			this.color = "blue";
		} else if (value != null && GREEN_LOWER_LIMIT <= value && value <= GREEN_UPPER_LIMIT) {
			this.color = "green";
		} else {
			this.color = "black";
		}
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
		return "DefaultColoredCsvData [id=" + id + ", label=" + label + ", color=" + color + "]";
	}
}
