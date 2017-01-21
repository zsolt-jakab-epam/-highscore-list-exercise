package com.jazs.highscore.dao.mapper;

import java.util.function.Function;

import com.jazs.highscore.dao.ColoredData;
import com.jazs.highscore.dao.RawData;

public interface ColoredDataMapper extends Function<RawData, ColoredData> {

}
