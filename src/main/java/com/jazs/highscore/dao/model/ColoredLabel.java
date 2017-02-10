package com.jazs.highscore.dao.model;

import org.springframework.beans.factory.annotation.Autowired;

public class ColoredLabel {
	
	private final String label;
	
	private final Color color;
	

	@Autowired
	public ColoredLabel(String label, Color color) {
		this.label = label;
		this.color = color;
	}


	public String getLabel() {
		return label;
	}


	public Color getColor() {
		return color;
	}


	@Override
	public String toString() {
		return "ColoredLabel [label=" + label + ", color=" + color + "]";
	}
}
