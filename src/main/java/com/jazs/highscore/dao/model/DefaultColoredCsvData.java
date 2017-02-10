package com.jazs.highscore.dao.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonCreator;

public class DefaultColoredCsvData implements ColoredCsvData {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultColoredCsvData.class);

	private static final double GREEN_LOWER_LIMIT = 100.0;

	private static final double GREEN_UPPER_LIMIT = 200.0;

	private Integer id;

	private String label;

	private Color color;

	@JsonCreator
	public DefaultColoredCsvData(Integer id, String label, Double value) {
		this.id = id;
		this.label = label;

		if (value != null && value < GREEN_LOWER_LIMIT) {
			this.color = Color.BLUE;
		} else if (value != null && GREEN_LOWER_LIMIT <= value && value <= GREEN_UPPER_LIMIT) {
			this.color = Color.GREEN;
		} else {
			this.color = Color.DEFAULT;
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
	public Color getColor() {
		return color;
	}

	@Override
	public String toString() {
		return "DefaultColoredCsvData [id=" + id + ", label=" + label + ", color=" + color + "]";
	}

}
