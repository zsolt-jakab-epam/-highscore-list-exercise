package com.jazs.highscore.resource;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jazs.highscore.dao.ColoredLabel;

@Controller
public class HighscoreController {

	@Autowired
	List<ColoredLabel> coloredLabels;
	
    @RequestMapping("/")
    public String list(Map<String, List<ColoredLabel>> model) {
    	for (ColoredLabel rawData : coloredLabels) {
			System.out.println(rawData);
		}
    	System.out.println(coloredLabels);
		model.put("coloredLabels", coloredLabels);
		
		return "scores";
    }

}