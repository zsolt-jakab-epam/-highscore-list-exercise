package com.jazs.highscore.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ScoreList {
	
	private List<RawData> scores;

	public List<RawData> getScores() {
		return scores;
	}

	public void setScores(List<RawData> scores) {
		this.scores = scores;
	} 
}
