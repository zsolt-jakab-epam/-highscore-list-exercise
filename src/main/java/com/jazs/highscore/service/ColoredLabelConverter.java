package com.jazs.highscore.service;

import static com.jazs.highscore.dao.ImmutableListCollector.toImmutableList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.ColoredLabel;

@Component
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
