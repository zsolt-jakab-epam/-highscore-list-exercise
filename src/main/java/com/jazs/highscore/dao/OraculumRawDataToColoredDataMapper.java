package com.jazs.highscore.dao;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.RawData;

@Component
public class OraculumRawDataToColoredDataMapper implements Function<RawData, ColoredData> {

	@Override
	public ColoredData apply(RawData rawData) {
		return new ColoredData(rawData.getId(), rawData.getLabel(), "red");
	}
}
