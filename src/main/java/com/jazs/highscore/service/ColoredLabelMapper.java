package com.jazs.highscore.service;

import java.util.function.Function;

import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.ColoredLabel;

public class ColoredLabelMapper implements Function<ColoredData, ColoredLabel> {

	@Override
	public ColoredLabel apply(ColoredData data) {
		return new ColoredLabel(data.getLabel(), data.getColor());
	}

}
