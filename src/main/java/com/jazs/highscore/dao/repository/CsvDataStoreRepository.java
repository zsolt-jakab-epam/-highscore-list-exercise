package com.jazs.highscore.dao.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.ColoredCsvData;
import com.jazs.highscore.dao.ColoredLabel;
import com.jazs.highscore.dao.mapper.ColoredLabelsMapper;

@Repository
public class CsvDataStoreRepository {

	private CsvMapper mapper;

	private CsvSchema schema;

	private Map<String, Class<? extends ColoredCsvData>> fileNameAndTypeMap;

	private Comparator<ColoredCsvData> comparator;

	private ColoredLabelsMapper coloredLabelMapper;

	@Autowired
	public CsvDataStoreRepository(CsvMapper mapper, CsvSchema schema, Comparator<ColoredCsvData> comparator,
			Map<String, Class<? extends ColoredCsvData>> fileNameAndTypeMap, ColoredLabelsMapper coloredLabelMapper) {
		this.mapper = mapper;
		this.schema = schema;
		this.fileNameAndTypeMap = fileNameAndTypeMap;
		this.comparator = comparator;
		this.coloredLabelMapper = coloredLabelMapper;
	}

	@SuppressWarnings("unchecked")
	public <T extends ColoredCsvData> List<ColoredLabel> getAll() {
		List<ColoredCsvData> allColoredCsvData = new ArrayList<>();

		for (Entry<String, Class<? extends ColoredCsvData>> entry : fileNameAndTypeMap.entrySet()) {
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
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<T> mappingIterator = mapper.readerFor(clazz).with(schema).readValues(file);
			return (List<ColoredCsvData>) mappingIterator.readAll();

		} catch (IOException e) {
			System.err.println("Error reading from file = " + fileName + e.getMessage());
			return Collections.emptyList();
		}
	}

}
