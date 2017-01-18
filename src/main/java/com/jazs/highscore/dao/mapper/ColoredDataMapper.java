package com.jazs.highscore.dao.mapper;

import java.util.function.Function;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.RawData;

public interface ColoredDataMapper extends Function<RawData, ColoredData> {

}
