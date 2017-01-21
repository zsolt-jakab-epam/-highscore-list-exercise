package com.jazs.highscore.dao.mapper;

import org.springframework.stereotype.Component;

import com.jazs.highscore.dao.ColoredData;
import com.jazs.highscore.dao.RawData;

@Component("oraculumColoredDataMapper")
public class OraculumColoredDataMapper implements ColoredDataMapper {

	@Override
	public ColoredData apply(RawData rawData) {
		return new ColoredData(rawData.getId(), rawData.getLabel(), "red");
	}
}
