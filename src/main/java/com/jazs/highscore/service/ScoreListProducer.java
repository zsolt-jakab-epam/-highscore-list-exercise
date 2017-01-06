package com.jazs.highscore.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jazs.highscore.dao.DataStoreDao;
import com.jazs.highscore.domain.ScoreList;
import com.jazs.highscore.domain.Score;

@Component
public class ScoreListProducer {
	
	@Autowired
	ScoreList scoreList;
	
	@Autowired
	DataStoreDao dao;
	
	@PostConstruct
	private void setupScoreList() {
    	List<Score> unsortedScores = dao.readOraculumDataStore();
    	unsortedScores.addAll(dao.readSingleSourceOfTruthDataStore());
    	unsortedScores.addAll(dao.readThirdPartyDataStore());
    	
    	List<Score> sortedScores = unsortedScores.stream().sorted(scoreComparator()).collect(Collectors.toCollection(ArrayList::new));
    	
    	scoreList.setScores(Collections.unmodifiableList(sortedScores));
	}

	private Comparator<Score> scoreComparator() {
		return Comparator.comparing(Score::getLabel).thenComparing(Score::getId);
	}
	
}
