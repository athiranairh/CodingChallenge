package com.rbc.application.stock.market.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.application.stock.market.application.models.DataSet;
import com.rbc.application.stock.market.application.response.ResponseMessage;
import com.rbc.application.stock.market.application.services.DataSetService;
import com.rbc.application.stock.market.application.utils.CommonUtil;

import com.sipios.springsearch.anotation.SearchSpec;

@RestController()

@RequestMapping("/api/dataSet")

public class DataSetController {

	@Autowired
	DataSetService dataSetService;
	
	/* more EXCEPTION handling code needs to be added to the below APIs to handle all possible error scenarios */

	/*API to get data sets according to the passed Id*/
	@GetMapping("/dataSets/{id}")
	public ResponseEntity<DataSet> getDataSets(@PathVariable Long id) {
		DataSet dataSet = dataSetService.getDataSets(id);
		if (null != dataSet) {
			return ResponseEntity.ok().body(dataSet);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/* API to add data sends . accepts a data set JSON and writes to DB*/
	@PostMapping("/dataSet/")
	public ResponseEntity<ResponseMessage> addDataSet(@RequestBody DataSet dataSet) {
		if (null != dataSet) {
			dataSetService.createDataSet(dataSet);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Added a DataSet"));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Request Failed"));
		}
	}
	
	/* API to add all data sets to DB */
	@PostMapping("/allDataSets/")
	public ResponseEntity<ResponseMessage> addAllDataSets(@RequestBody Iterable<DataSet> dataSets) {
		if (null != dataSets) {
			dataSetService.saveAllDataSets(dataSets);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Added dataSets"));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Request Failed"));
		}
	}

	/* API to add all data sets from CSV file to DB */
	@PostMapping("/allDataSetsFromCSV")
	public ResponseEntity<ResponseMessage> addAllDataSetsFromCSV(@RequestParam("file") MultipartFile file) {
		if (CommonUtil.hasCsvFormat(file)) {
			try {
				dataSetService.save(file);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Added DataSets successfully"));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Request Failed"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("file format not supported"));
		}
	}
	
	/* API to get all data sets by passing search criteria
	 * sample : http://localhost:8080/api/dataSet/allDataSets?search=(stock:'RBC')
	 *  */
	@GetMapping("/allDataSets")
	public ResponseEntity<List<DataSet>> getDataSetsWithSpecificStockTicker(@SearchSpec Specification<DataSet> specs) {
		List<DataSet> dataSets = dataSetService.getAllDataSets(specs);
		if (null != dataSets) {
			return ResponseEntity.ok().body(dataSets);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}


}
