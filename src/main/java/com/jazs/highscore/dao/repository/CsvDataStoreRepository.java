package com.jazs.highscore.dao.repository;

import static com.jazs.highscore.common.Constants.DATA_STORE_PATH;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jazs.highscore.dao.mapper.ColoredLabelsMapper;
import com.jazs.highscore.dao.model.ColoredCsvData;
import com.jazs.highscore.dao.model.ColoredLabel;

@Repository
public class CsvDataStoreRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(CsvDataStoreRepository.class);

	private Map<String, Class<? extends ColoredCsvData>> csvNameAndTypeMap;

	private Comparator<ColoredCsvData> comparator;

	private ColoredLabelsMapper coloredLabelMapper;

	private ColoredCsvDataReader csvReader;

	@Autowired
	public CsvDataStoreRepository(ColoredCsvDataReader csvReader, Comparator<ColoredCsvData> comparator,
			Map<String, Class<? extends ColoredCsvData>> csvNameAndTypeMap, ColoredLabelsMapper coloredLabelMapper) {
		this.csvNameAndTypeMap = csvNameAndTypeMap;
		this.comparator = comparator;
		this.coloredLabelMapper = coloredLabelMapper;
		this.csvReader = csvReader;
	}

	public <T extends ColoredCsvData> List<ColoredLabel> getAll() {
		List<ColoredCsvData> allColoredCsvData = new ArrayList<>();

		for (Entry<String, Class<? extends ColoredCsvData>> entry : csvNameAndTypeMap.entrySet()) {
			List<ColoredCsvData> entryList = csvReader.read(getFilePath(entry), entry.getValue());
			allColoredCsvData.addAll(entryList);
		}

		List<ColoredCsvData> allSortedColoredCsvData = allColoredCsvData.stream().sorted(comparator)
				.collect(Collectors.toCollection(ArrayList::new));

		return coloredLabelMapper.map(allSortedColoredCsvData);
	}

	private String getFilePath(Entry<String, Class<? extends ColoredCsvData>> entry) {
		return DATA_STORE_PATH + entry.getKey();
	}

}
