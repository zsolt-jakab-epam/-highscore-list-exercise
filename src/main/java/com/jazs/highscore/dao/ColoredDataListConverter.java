package com.jazs.highscore.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.RawData;

@Component
public class ColoredDataListConverter {
	
	@Autowired
	private DefaultRawDataToColoredDataMapper defauldMapper;
	
	@Autowired
	private OraculumRawDataToColoredDataMapper oraculumMapper;

	public List<ColoredData> convertFromOraculumDataList(List<RawData> rawDataList) {
		return rawDataList.stream().map(oraculumMapper).collect(Collectors.toList());
	}
	
	public List<ColoredData> converFromDataList(List<RawData> rawDataList) {
		return rawDataList.stream().map(defauldMapper).collect(Collectors.toList());
	}
}
