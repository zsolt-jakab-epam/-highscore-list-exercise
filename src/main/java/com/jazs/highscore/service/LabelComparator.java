package com.jazs.highscore.service;

import java.util.Comparator;

import com.jazs.highscore.dao.ColoredData;

public class LabelComparator implements Comparator<ColoredData> {

	@Override
	public int compare(ColoredData data1, ColoredData data2) {
		int i = data1.getLabel().compareTo(data2.getLabel());
		if (i != 0) return i;
		
		return Integer.compare(data1.getId(), data2.getId());
	}

}
