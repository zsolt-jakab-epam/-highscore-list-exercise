package com.jazs.highscore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jazs.highscore.domain.ColoredLabel;
import com.jazs.highscore.service.ScoreListProducer;

@Configuration
public class HighScoreConfiguration {

	private final ScoreListProducer producer;
	
	@Autowired
    public HighScoreConfiguration(ScoreListProducer producer) {
		this.producer = producer;
	}



	@Bean
    public List<ColoredLabel> coloredLabels() {
        return producer.setupScoreList();
    }
	
}
