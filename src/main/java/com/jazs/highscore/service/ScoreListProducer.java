package com.jazs.highscore.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jazs.highscore.dao.DataStoreDao;
import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.ColoredLabel;
import com.jazs.highscore.domain.ScoreList;

@Component
public class ScoreListProducer {

	@Autowired
	ScoreList scoreList;

	@Autowired
	DataStoreDao dao;
	
	@Autowired
	LabelComparator comparator;
	
	@Autowired
	ColoredLabelConverter converter;

	public List<ColoredLabel> setupScoreList() {
		List<ColoredData> unsortedScores = dao.readOraculumDataStore();
		unsortedScores.addAll(dao.readSingleSourceOfTruthDataStore());
		unsortedScores.addAll(dao.readThirdPartyDataStore());

		List<ColoredData> dataList = unsortedScores.stream().sorted(comparator)
				.collect(Collectors.toCollection(ArrayList::new));

		return converter.converFromDataList(dataList);
	}
}
