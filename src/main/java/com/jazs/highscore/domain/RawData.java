package com.jazs.highscore.domain;

public class RawData {
	
	private Integer id;
	private String label;
	private Float value;
	
	public RawData() {
	}
	
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

	public float getValue() {
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
