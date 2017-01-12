package com.jazs.highscore.dao;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.RawData;

@Repository
public class DataStoreDao {

	private CsvMapper mapper = new CsvMapper();

	private CsvSchema bootstrapSchema = CsvSchema.builder().addColumn("id", CsvSchema.ColumnType.NUMBER)
			.addColumn("label").addColumn("value", CsvSchema.ColumnType.NUMBER).build();
	
	@Autowired
	private ColoredDataListConverter converter;

	public List<ColoredData> readOraculumDataStore() {
		return converter.convertFromOraculumDataList(readDataStore("OraculumDataStore.csv"));
	}

	public List<ColoredData> readSingleSourceOfTruthDataStore() {
		return converter.converFromDataList(readDataStore("SingleSourceOfTruthDataStore.csv"));
	}

	public List<ColoredData> readThirdPartyDataStore() {
		return converter.converFromDataList(readDataStore("ThirdPartyDataStore.csv"));
	}

	private List<RawData> readDataStore(String fileName) {
		try {
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<RawData> mappingIterator = mapper.readerFor(RawData.class).with(bootstrapSchema)
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
