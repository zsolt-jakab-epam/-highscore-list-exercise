package com.jazs.highscore.resource;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.DataStoreDao;
import com.jazs.highscore.domain.Score;
import com.jazs.highscore.domain.ScoreList;

@RestController
public class HighscoreController {

	@Autowired
	ScoreList scores;
	
    @RequestMapping("/")
    String home() throws Exception {
    	for (Score rawData : scores.getScores()) {
			System.out.println(rawData);
		}
        return "under construction!";
    }

}