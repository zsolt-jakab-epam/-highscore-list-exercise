package com.jazs.highscore.dao.mapper;

import static com.jazs.highscore.common.util.ImmutableListCollector.toImmutableList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jazs.highscore.dao.ColoredCsvData;
import com.jazs.highscore.dao.ColoredLabel;

@Component
public class ColoredLabelsMapper {

	private final ColoredLabelMapper mapper;

	@Autowired
	public ColoredLabelsMapper(ColoredLabelMapper mapper) {
		this.mapper = mapper;
	}

	public List<ColoredLabel> map(List<ColoredCsvData> rawDataList) {
		return rawDataList.stream().map(mapper).collect(toImmutableList());
	}
}
