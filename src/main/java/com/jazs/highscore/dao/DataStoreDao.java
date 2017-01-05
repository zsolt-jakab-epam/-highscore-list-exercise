package com.jazs.highscore.dao;

import java.io.File;
import java.io.IOException;
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
import com.jazs.highscore.domain.RawData;

@Repository
public class DataStoreDao {
	
	CsvMapper mapper = new CsvMapper();
	
    private CsvSchema bootstrapSchema = CsvSchema.builder()
            .addColumn("id", CsvSchema.ColumnType.NUMBER)
            .addColumn("label")
            .addColumn("value", CsvSchema.ColumnType.NUMBER)
            .build();
	
    public List<RawData> readOraculumDataStore() throws JsonProcessingException, IOException  {

        File file = new ClassPathResource("OraculumDataStore.csv").getFile();
        
        return readDataStore(file);
    }
    
    public List<RawData> readSingleSourceOfTruthDataStore() throws JsonProcessingException, IOException  {

        File file = new ClassPathResource("SingleSourceOfTruthDataStore.csv").getFile();
        
        return readDataStore(file);
    }
    
    public List<RawData> readThirdPartyDataStore() throws JsonProcessingException, IOException  {

        File file = new ClassPathResource("ThirdPartyDataStore.csv").getFile();
        
        return readDataStore(file);
    }

	private List<RawData> readDataStore(File file) throws IOException, JsonProcessingException {
		MappingIterator<RawData> mappingIterator = 
                mapper.readerFor(RawData.class).with(bootstrapSchema).readValues(file);
 

		return mappingIterator.readAll();
	}
	
	@PostConstruct
	private void setupMapper() {
		mapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);
	}
}
