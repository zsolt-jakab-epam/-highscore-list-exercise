package com.jazs.highscore.dao.repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.model.ColoredCsvData;

@Repository
public class ColoredCsvDataReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ColoredCsvDataReader.class);
	
	public CsvMapper csvMapper;
	public CsvSchema csvSchema;

	@Autowired
	public ColoredCsvDataReader(CsvMapper csvMapper, CsvSchema csvSchema) {
		this.csvMapper = csvMapper;
		this.csvSchema = csvSchema;
	}



	@SuppressWarnings("unchecked")
	public <T extends ColoredCsvData> List<ColoredCsvData> read(String filePath, Class<T> clazz) {
		try {
			File file = new ClassPathResource(filePath).getFile();
			MappingIterator<T> mappingIterator = csvMapper.readerFor(clazz).with(csvSchema).readValues(file);
			
			LOGGER.info("Read csv data store: {}", filePath);
			
			return (List<ColoredCsvData>) mappingIterator.readAll();
			
		} catch (IOException e) {
			LOGGER.error("Error reading from file: {} ", filePath, e);
			
			return Collections.emptyList();
		}
	}
}