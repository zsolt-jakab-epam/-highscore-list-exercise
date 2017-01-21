package com.jazs.highscore.dao.mapper;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.jazs.highscore.dao.ColoredData;
import com.jazs.highscore.dao.RawData;

public class ColoredDataListMapper {
	
	private Function<RawData, ColoredData> mapper;

	@Autowired
	public ColoredDataListMapper(Function<RawData, ColoredData> mapper) {
		this.mapper = mapper;
	}

	public List<ColoredData> map(List<RawData> rawDataList) {
		return rawDataList.stream().map(mapper).collect(Collectors.toList());
	}
}
