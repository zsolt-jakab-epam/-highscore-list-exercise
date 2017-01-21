package com.jazs.highscore.service;

import static com.jazs.highscore.common.util.ImmutableListCollector.toImmutableList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jazs.highscore.dao.ColoredData;
import com.jazs.highscore.dao.ColoredLabel;

public class ColoredLabelConverter {

	private final ColoredLabelMapper mapper;

	@Autowired
	public ColoredLabelConverter(ColoredLabelMapper mapper) {
		this.mapper = mapper;
	}

	public List<ColoredLabel> converFromDataList(List<ColoredData> rawDataList) {
		return rawDataList.stream().map(mapper).collect(toImmutableList());
	}
}
