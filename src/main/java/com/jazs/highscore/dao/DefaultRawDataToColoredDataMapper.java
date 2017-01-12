package com.jazs.highscore.dao;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.RawData;

@Component
public class DefaultRawDataToColoredDataMapper implements Function<RawData, ColoredData> {

	@Override
	public ColoredData apply(RawData rawData) {

		if (rawData.getValue() < 100.0)
			return new ColoredData(rawData.getId(), rawData.getLabel(), "blue");

		if (100 <= rawData.getValue() && rawData.getValue() <= 200.0)
			return new ColoredData(rawData.getId(), rawData.getLabel(), "blue");

		return new ColoredData(rawData.getId(), rawData.getLabel(), "black");
	}

}
