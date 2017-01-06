package com.jazs.highscore.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.jazs.highscore.domain.Score;
import com.jazs.highscore.domain.ScoreList;

@Controller
public class HighscoreController {

	@Autowired
	ScoreList scores;
	
    @RequestMapping("/")
    public String list(Map<String, List<Score>> model) {
    	for (Score rawData : scores.getScores()) {
			System.out.println(rawData);
		}
    	System.out.println(scores);
		model.put("scores", scores.getScores());
		
		return "scores";
    }

}