package com.jazs.highscore.domain;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ScoreList {
	
	private List<Score> scores;

	public List<Score> getLabels() {
		return scores;
	}

	public void setLabels(List<Score> scores) {
		this.scores = scores;
	} 
}