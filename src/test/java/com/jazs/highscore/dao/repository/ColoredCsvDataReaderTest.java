package com.jazs.highscore.dao.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.model.ColoredCsvData;
import com.jazs.highscore.dao.model.RedColoredCsvData;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ColoredCsvDataReaderTest {

	private String FILE_PATH = "/com/jazs/highscore/dao/repository/OraculumDataStore.csv";
	
	@Autowired
	private CsvMapper csvMapper;
	
	@Autowired
	private CsvSchema csvSchema;
	
	private  ColoredCsvDataReader underTest;
	
    @Before
    public void setUp() {
    	underTest = new ColoredCsvDataReader(csvMapper, csvSchema);
    }

	@Test
	public void readSouldReadFromGivenFileWhenCalled() {
		//GIVEN
		

		//WHEN
		List<ColoredCsvData> result = underTest.read(FILE_PATH, RedColoredCsvData.class);
		
		//THEN
		assertThat(result.size()).isEqualTo(5);
	}
	
}
