package com.jazs.highscore.dao.mapper;

import org.springframework.stereotype.Component;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.RawData;

@Component("oraculumColoredDataMapper")
public class OraculumColoredDataMapper implements ColoredDataMapper {

	@Override
	public ColoredData apply(RawData rawData) {
		return new ColoredData(rawData.getId(), rawData.getLabel(), "red");
	}
}
