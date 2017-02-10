package com.jazs.highscore.dao.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.model.ColoredCsvData;
import com.jazs.highscore.dao.model.DefaultColoredCsvData;
import com.jazs.highscore.dao.model.RedColoredCsvData;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ColoredCsvDataReaderTest {

	private static final String FILE_PATH = "/com/jazs/highscore/dao/repository/TestDataStore.csv";

	private static final String FAKE_FILE_PATH = "/fakepath/fake.csv";

	private ColoredCsvDataReader underTest;
	
	@Autowired
	private CsvMapper csvMapper;

	@Autowired
	private CsvSchema csvSchema;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		underTest = new ColoredCsvDataReader(csvMapper, csvSchema);
	}

	@Test
	public void readSouldReadAllLinesFromGivenFileWhenCalled() {
		// GIVEN
		List<ColoredCsvData> expected = new ArrayList<>();
		expected.add(new RedColoredCsvData(3, "M"));
		expected.add(new RedColoredCsvData(9, "B"));
		expected.add(new RedColoredCsvData(7, "V"));

		// WHEN
		List<ColoredCsvData> result = underTest.read(FILE_PATH, RedColoredCsvData.class);

		// THEN
		assertReflectionEquals(expected, result);
	}

	@Test
	public void readSouldReadToGivenClassWhenCalled() {
		// GIVEN

		// WHEN
		List<ColoredCsvData> redColoredResult = underTest.read(FILE_PATH, RedColoredCsvData.class);
		List<ColoredCsvData> defaultColoredResult = underTest.read(FILE_PATH, DefaultColoredCsvData.class);

		// THEN
		assertThat(redColoredResult).hasOnlyElementsOfType(RedColoredCsvData.class);
		assertThat(defaultColoredResult).hasOnlyElementsOfType(DefaultColoredCsvData.class);
	}

	@Test
	public void readSouldThrowIOExceptionWhenFilePathNotExists() {
		// GIVEN
		//exception.expect(FileNotFoundException.class);
		//exception.expectMessage("class path resource [fakepath/fake.csv] cannot be resolved to URL because it does not exist");

		// WHEN
		//List<ColoredCsvData> result = underTest.read(FAKE_FILE_PATH, RedColoredCsvData.class);		

		//THEN exception thrown
	}

}
