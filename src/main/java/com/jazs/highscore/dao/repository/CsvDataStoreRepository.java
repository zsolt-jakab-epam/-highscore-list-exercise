package com.jazs.highscore.dao.repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.domain.RawData;

public class CsvDataStoreRepository {

	private CsvMapper mapper;

	private CsvSchema schema;
	
	private String fileName;
	
	public CsvDataStoreRepository(CsvMapper mapper, CsvSchema schema, String fileName) {
		this.mapper = mapper;
		this.schema = schema;
		this.fileName = fileName;
	}

	public List<RawData> getAll() {
		try {
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<RawData> mappingIterator = mapper.readerFor(RawData.class).with(schema).readValues(file);
			return mappingIterator.readAll();

		} catch (IOException e) {
			System.err.println("Error reading from file = " + fileName + e.getMessage());
			return Collections.emptyList();
		}
	}
	
}
