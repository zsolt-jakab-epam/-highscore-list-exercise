package com.jazs.highscore.dao.repository;

import static com.jazs.highscore.common.Constants.DATA_STORE_PATH;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.mapper.ColoredLabelsMapper;
import com.jazs.highscore.dao.model.ColoredCsvData;
import com.jazs.highscore.dao.model.ColoredLabel;

@Repository
public class CsvDataStoreRepository {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CsvDataStoreRepository.class);

	private CsvMapper csvMapper;

	private CsvSchema csvSchema;

	private Map<String, Class<? extends ColoredCsvData>> csvNameAndTypeMap;

	private Comparator<ColoredCsvData> comparator;

	private ColoredLabelsMapper coloredLabelMapper;

	@Autowired
	public CsvDataStoreRepository(CsvMapper csvMapper, CsvSchema csvSchema, Comparator<ColoredCsvData> comparator,
			Map<String, Class<? extends ColoredCsvData>> csvNameAndTypeMap, ColoredLabelsMapper coloredLabelMapper) {
		this.csvMapper = csvMapper;
		this.csvSchema = csvSchema;
		this.csvNameAndTypeMap = csvNameAndTypeMap;
		this.comparator = comparator;
		this.coloredLabelMapper = coloredLabelMapper;
	}

	@SuppressWarnings("unchecked")
	public <T extends ColoredCsvData> List<ColoredLabel> getAll() {
		List<ColoredCsvData> allColoredCsvData = new ArrayList<>();

		for (Entry<String, Class<? extends ColoredCsvData>> entry : csvNameAndTypeMap.entrySet()) {
			List<ColoredCsvData> entryList = get(entry.getKey(), entry.getValue());
			allColoredCsvData.addAll(entryList);
		}

		List<ColoredCsvData> allSortedColoredCsvData = allColoredCsvData.stream().sorted(comparator)
				.collect(Collectors.toCollection(ArrayList::new));
		
		return coloredLabelMapper.map(allSortedColoredCsvData);
	}

	@SuppressWarnings("unchecked")
	private <T extends ColoredCsvData> List<ColoredCsvData> get(String fileName, Class<T> clazz) {
		try {
			String filePath = DATA_STORE_PATH + fileName;
			File file = new ClassPathResource(filePath).getFile();
			MappingIterator<T> mappingIterator = csvMapper.readerFor(clazz).with(csvSchema).readValues(file);
			LOGGER.info("Read csv data store: {}", filePath);
			return (List<ColoredCsvData>) mappingIterator.readAll();
		} catch (IOException e) {
			LOGGER.info("Error reading from file {} ", e);
			return Collections.emptyList();
		}
	}

}
