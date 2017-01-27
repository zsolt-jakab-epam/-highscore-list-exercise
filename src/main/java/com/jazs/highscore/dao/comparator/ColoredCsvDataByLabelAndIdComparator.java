package com.jazs.highscore.dao.comparator;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.jazs.highscore.dao.ColoredCsvData;

@Component
public class ColoredCsvDataByLabelAndIdComparator implements Comparator<ColoredCsvData> {

	@Override
	public int compare(ColoredCsvData data1, ColoredCsvData data2) {
		int i = data1.getLabel().compareTo(data2.getLabel());
		if (i != 0) return i;
		
		return Integer.compare(data1.getId(), data2.getId());
	}

}
