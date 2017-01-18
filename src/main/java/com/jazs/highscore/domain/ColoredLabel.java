package com.jazs.highscore.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class ColoredLabel {
	
	private final String label;
	private final String color;
	

	@Autowired
	public ColoredLabel(String label, String color) {
		this.label = label;
		this.color = color;
	}


	public String getLabel() {
		return label;
	}


	public String getColor() {
		return color;
	}


	@Override
	public String toString() {
		return "ColoredLabel [label=" + label + ", color=" + color + "]";
	}
}
