package com.jazs.highscore;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.jazs.highscore.dao.DataStoreDao;
import com.jazs.highscore.domain.RawData;

@RestController
public class SampleController {

	@Autowired
	DataStoreDao dao;
	
    @RequestMapping("/")
    String home() throws Exception {
    	List<RawData> list = dao.readOraculumDataStore();
    	list.addAll(dao.readSingleSourceOfTruthDataStore());
    	list.addAll(dao.readThirdPartyDataStore());
    	
    	for (RawData rawData : list) {
			System.out.println(rawData);
		}
        return "Hello World!";
    }

}