package com.jazs.highscore.dao;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.domain.Score;

@Repository
public class DataStoreDao {

	CsvMapper mapper = new CsvMapper();

	private CsvSchema bootstrapSchema = CsvSchema.builder().addColumn("id", CsvSchema.ColumnType.NUMBER)
			.addColumn("label").addColumn("value", CsvSchema.ColumnType.NUMBER).build();

	public List<Score> readOraculumDataStore() {
		return readDataStore("OraculumDataStore.csv");
	}

	public List<Score> readSingleSourceOfTruthDataStore() {

		return readDataStore("SingleSourceOfTruthDataStore.csv");
	}

	public List<Score> readThirdPartyDataStore() {

		return readDataStore("ThirdPartyDataStore.csv");
	}

	private List<Score> readDataStore(String fileName) {
		try {
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<Score> mappingIterator = mapper.readerFor(Score.class).with(bootstrapSchema)
					.readValues(file);
			return mappingIterator.readAll();

		} catch (IOException e) {
			System.err.println("Error reading from file = " + fileName + e.getMessage());
			return Collections.emptyList();
		}
	}

	@PostConstruct
	private void setupMapper() {
		mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
	}
}
