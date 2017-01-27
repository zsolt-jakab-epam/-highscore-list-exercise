package com.jazs.highscore.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jazs.highscore.dao.ColoredLabel;
import com.jazs.highscore.service.HighscoreService;

@Controller
public class HighscoreController {

	@Autowired
	HighscoreService highscoreService;
	
    @RequestMapping("/")
    public String list(Map<String, List<ColoredLabel>> model) {
    	for (ColoredLabel rawData : highscoreService.getAll()) {
			System.out.println(rawData);
		}
    	System.out.println(highscoreService.getAll().hashCode());
		model.put("coloredLabels", highscoreService.getAll());
		
		return "scores";
    }

}