package com.jazs.highscore.dao.comparator;

import java.util.Comparator;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.jazs.highscore.dao.model.ColoredCsvData;

@Component
public class ColoredCsvDataByLabelAndIdComparator implements Comparator<ColoredCsvData> {

	@Override
	public int compare(ColoredCsvData data1, ColoredCsvData data2) {
		int i = ObjectUtils.compare(data1.getLabel(), data2.getLabel()); 
		
		if (i != 0) 
			return i;
		
		return ObjectUtils.compare(data1.getId(), data2.getId()); 
	}

}
