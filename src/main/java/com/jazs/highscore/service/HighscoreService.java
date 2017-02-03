package com.jazs.highscore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jazs.highscore.dao.model.ColoredLabel;
import com.jazs.highscore.dao.repository.CsvDataStoreRepository;

@Service
public class HighscoreService {
	
	private CsvDataStoreRepository repository;
	
	@Autowired
	public HighscoreService(CsvDataStoreRepository repository) {
		this.repository = repository;
	}

	
	public List<ColoredLabel> getAll() {
		return repository.getAll();
	}

}
