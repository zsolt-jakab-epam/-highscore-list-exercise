package com.jazs.highscore.dao.mapper;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.RawData;

public class DefaultColoredDataMapper implements ColoredDataMapper {

	@Override
	public ColoredData apply(RawData rawData) {

		if (rawData.getValue() != null) {

			if (rawData.getValue() < 100.0) {

				return new ColoredData(rawData.getId(), rawData.getLabel(), "blue");

			} else if (100 <= rawData.getValue() && rawData.getValue() <= 200.0) {

				return new ColoredData(rawData.getId(), rawData.getLabel(), "blue");

			}
		}

		return new ColoredData(rawData.getId(), rawData.getLabel(), "black");
	}

}
