package com.jazs.highscore.common.config;

import static com.jazs.highscore.common.Constants.ID;
import static com.jazs.highscore.common.Constants.LABEL;
import static com.jazs.highscore.common.Constants.ORACULUM_DATA_STORE_CSV;
import static com.jazs.highscore.common.Constants.SINGLE_SOURCE_OF_TRUTH_DATA_STORE_CSV;
import static com.jazs.highscore.common.Constants.THIRD_PARTY_DATA_STORE_CSV;
import static com.jazs.highscore.common.Constants.VALUE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.mapper.ColoredDataListMapper;
import com.jazs.highscore.dao.mapper.ColoredDataMapper;
import com.jazs.highscore.dao.repository.CsvDataStoreRepository;
import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.ColoredLabel;
import com.jazs.highscore.domain.RawData;
import com.jazs.highscore.service.ColoredLabelConverter;
import com.jazs.highscore.service.ColoredLabelMapper;
import com.jazs.highscore.service.LabelComparator;

@Configuration
public class HighScoreConfiguration {



	@Autowired
	private ApplicationContext ctx;

	@Bean
	public CsvMapper csvMapper() {
		CsvMapper mapper = new CsvMapper();
		mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
		return mapper;
	}

	@Bean
	CsvSchema dataStoreCsvSchema() {
		return CsvSchema.builder().addColumn(ID, CsvSchema.ColumnType.NUMBER).addColumn(LABEL)
				.addColumn(VALUE, CsvSchema.ColumnType.NUMBER).build();
	}

	@Bean
	@Scope("prototype")
	CsvDataStoreRepository repository(String fileName) {
		return new CsvDataStoreRepository(csvMapper(), dataStoreCsvSchema(), fileName);
	}

	@Bean
	@Scope("prototype")
	List<RawData> csvRawDataList(String fileName) {
		
		CsvDataStoreRepository repository = repository(fileName);
		
		return repository.getAll();
	}
	
	@Bean
	@Scope("prototype")
	ColoredDataListMapper coloredDataListMapper(String insideMapperName) {
		
		ColoredDataMapper mapper = ctx.getBean(insideMapperName, ColoredDataMapper.class);
		
		return new ColoredDataListMapper(mapper);
	}

	@Bean
	@Scope("prototype")
	List<ColoredData> oraculumColoredDataList(String fileName) {
		
		List<RawData> list = csvRawDataList(ORACULUM_DATA_STORE_CSV);
		
		ColoredDataListMapper mapper = coloredDataListMapper("oraculumColoredDataMapper");
		
		return mapper.map(list);
	}

	@Bean
	LabelComparator labelComparator() {
		return new LabelComparator();
	}

	@Bean
	ColoredLabelMapper coloredLabelMapper() {
		return new ColoredLabelMapper();
	}

	@Bean
	ColoredLabelConverter coloredLabelConverter(ColoredLabelMapper coloredLabelMapper) {
		return new ColoredLabelConverter(coloredLabelMapper);
	}

	@Bean
	List<ColoredData> coloredDataList() {
		
		List<RawData> oraculumRawDataList = csvRawDataList(ORACULUM_DATA_STORE_CSV);
		List<RawData> singleSourceOfTruthRawDataList = csvRawDataList(SINGLE_SOURCE_OF_TRUTH_DATA_STORE_CSV);
		List<RawData> thirdPartyRawDataList = csvRawDataList(THIRD_PARTY_DATA_STORE_CSV);
		
		ColoredDataListMapper oraculumColoredDataListMapper = coloredDataListMapper("oraculumColoredDataMapper");
		ColoredDataListMapper defaultColoredDataListMapper = coloredDataListMapper("defaultColoredDataMapper");
		
		List<ColoredData> coloredDataList = oraculumColoredDataListMapper.map(oraculumRawDataList);
		coloredDataList.addAll(defaultColoredDataListMapper.map(singleSourceOfTruthRawDataList));
		coloredDataList.addAll(defaultColoredDataListMapper.map(thirdPartyRawDataList));

		return coloredDataList;
	}

	@Bean
	public List<ColoredLabel> coloredLabels(List<ColoredData> coloredDataList,
			ColoredLabelConverter coloredLabelConverter) {

		List<ColoredData> sortedColoredDataList = coloredDataList.stream().sorted(labelComparator())
				.collect(Collectors.toCollection(ArrayList::new));

		return coloredLabelConverter.converFromDataList(sortedColoredDataList);
	}
}
