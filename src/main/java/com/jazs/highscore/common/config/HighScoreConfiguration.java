package com.jazs.highscore.common.config;

import static com.jazs.highscore.common.Constants.ID;
import static com.jazs.highscore.common.Constants.LABEL;
import static com.jazs.highscore.common.Constants.ORACULUM_DATA_STORE_CSV;
import static com.jazs.highscore.common.Constants.SINGLE_SOURCE_OF_TRUTH_DATA_STORE_CSV;
import static com.jazs.highscore.common.Constants.THIRD_PARTY_DATA_STORE_CSV;
import static com.jazs.highscore.common.Constants.VALUE;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.jazs.highscore.dao.ColoredCsvData;
import com.jazs.highscore.dao.DefaultColoredCsvData;
import com.jazs.highscore.dao.RedColoredCsvData;

@Configuration
public class HighScoreConfiguration {

	@Bean
	public CsvMapper csvMapper() {
		CsvMapper mapper = new CsvMapper();
		mapper.registerModule(new ParameterNamesModule());
		mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return mapper;
	}

	@Bean
	CsvSchema dataStoreCsvSchema() {
		return CsvSchema.builder().addColumn(ID, CsvSchema.ColumnType.NUMBER).addColumn(LABEL)
				.addColumn(VALUE, CsvSchema.ColumnType.NUMBER).build();
	}
	
	@Bean
	Map<String, Class<? extends ColoredCsvData>> dataStoreMap() {
		Map<String, Class<? extends ColoredCsvData>> map = new HashMap<>();
		map.put(ORACULUM_DATA_STORE_CSV, RedColoredCsvData.class);
		map.put(SINGLE_SOURCE_OF_TRUTH_DATA_STORE_CSV, DefaultColoredCsvData.class);
		map.put(THIRD_PARTY_DATA_STORE_CSV, DefaultColoredCsvData.class);
		
		return map;
	}
}
