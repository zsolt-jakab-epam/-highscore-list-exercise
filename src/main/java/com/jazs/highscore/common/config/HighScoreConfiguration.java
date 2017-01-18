package com.jazs.highscore.common.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.mapper.ColoredDataListMapper;
import com.jazs.highscore.dao.mapper.ColoredDataMapper;
import com.jazs.highscore.dao.mapper.DefaultColoredDataMapper;
import com.jazs.highscore.dao.mapper.OraculumColoredDataMapper;
import com.jazs.highscore.dao.repository.CsvDataStoreRepository;
import com.jazs.highscore.domain.ColoredData;
import com.jazs.highscore.domain.ColoredLabel;
import com.jazs.highscore.domain.RawData;
import com.jazs.highscore.service.ColoredLabelConverter;
import com.jazs.highscore.service.ColoredLabelMapper;
import com.jazs.highscore.service.LabelComparator;

@Configuration
public class HighScoreConfiguration {

	private static final String THIRD_PARTY_DATA_STORE_CSV = "ThirdPartyDataStore.csv";

	private static final String SINGLE_SOURCE_OF_TRUTH_DATA_STORE_CSV = "SingleSourceOfTruthDataStore.csv";

	private static final String ORACULUM_DATA_STORE_CSV = "OraculumDataStore.csv";

	private static final String VALUE = "value";
	
	private static final String LABEL = "label";
	
	private static final String ID = "id";
	

	@Bean
	public CsvMapper csvMapper() {
		CsvMapper mapper = new CsvMapper();
		mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
		return mapper;
	}
	
	@Bean 
	CsvSchema dataStoreCsvSchema() {
		return CsvSchema.builder().addColumn(ID, CsvSchema.ColumnType.NUMBER)
				.addColumn(LABEL).addColumn(VALUE, CsvSchema.ColumnType.NUMBER).build();
	}
	
	@Bean 
	CsvDataStoreRepository oraculumRepository() {
		return new CsvDataStoreRepository(csvMapper(), dataStoreCsvSchema(), ORACULUM_DATA_STORE_CSV);
	}
	
	@Bean 
	CsvDataStoreRepository singleSourceOfTruthRepository() {
		return new CsvDataStoreRepository(csvMapper(), dataStoreCsvSchema(), SINGLE_SOURCE_OF_TRUTH_DATA_STORE_CSV);
	}

	@Bean 
	CsvDataStoreRepository thirdPartyRepository() {
		return new CsvDataStoreRepository(csvMapper(), dataStoreCsvSchema(), THIRD_PARTY_DATA_STORE_CSV);
	}
		
	@Bean 
	ColoredDataMapper defaultColoredDataMapper() {
		return new DefaultColoredDataMapper();
	}
	
	@Bean 
	ColoredDataMapper oraculumColoredDataMapper() {
		return new OraculumColoredDataMapper();
	}
	
	@Bean
	ColoredDataListMapper defaultColoredDataListMapper() {
		return new ColoredDataListMapper(defaultColoredDataMapper());
	}
	
	@Bean
	ColoredDataListMapper oraculumColoredDataListMapper() {
		return new ColoredDataListMapper(oraculumColoredDataMapper());
	}
	
	@Bean
	List<RawData> oraculumRawDataList() {
		return oraculumRepository().getAll();
	}
	
	@Bean
	List<RawData> singleSourceOfTruthRawDataList() {
		return singleSourceOfTruthRepository().getAll();
	}
	
	@Bean
	List<RawData> thirdPartyRawDataList() {
		return thirdPartyRepository().getAll();
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
    public List<ColoredLabel> coloredLabels(ColoredLabelConverter coloredLabelConverter) {
		List<RawData> oraculumRawDataList = oraculumRepository().getAll();
		List<RawData> singleSourceOfTruthDataList = singleSourceOfTruthRepository().getAll();
		List<RawData> thirdPartyDataList = thirdPartyRepository().getAll();
		
		List<ColoredData> unsortedColoredDataList = oraculumColoredDataListMapper().map(oraculumRawDataList);
		unsortedColoredDataList.addAll(defaultColoredDataListMapper().map(singleSourceOfTruthDataList));
		unsortedColoredDataList.addAll(defaultColoredDataListMapper().map(thirdPartyDataList));

		List<ColoredData> dataList = unsortedColoredDataList.stream().sorted(labelComparator())
				.collect(Collectors.toCollection(ArrayList::new));

		return coloredLabelConverter.converFromDataList(dataList);
    }
}
