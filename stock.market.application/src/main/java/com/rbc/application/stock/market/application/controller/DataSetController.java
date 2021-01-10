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

@RequestMapping("/api/V1")

public class DataSetController {

	@Autowired
	DataSetService dataSetService;
	
	/* more EXCEPTION handling code needs to be added to the below APIs to handle all possible error scenarios 
	 * 
	 * in order to avoid any concurrent access anomalies, optimistic locking with the help of JPA @Version is implemented
	 * 
	 * in the GET request, an ETag is returned in the response header indicating the version of the resource.
	 * 
	 * Later in a subsequent PUT request, the client can pass this value back in request header under If-Match 
	 * and can be checked for correct version of the resource
	 * 
	 * 
	 * */

	/*API to get data sets according to the passed Id
	 * 
	 * operation: GET
	 * Accepts search criteria Id as a path variable
	 * returns 200 OK and the object as success response 
	 * 404, not returned in failure scenarios
	 * 
	 * */
	@GetMapping("/dataSets/{id}")
	public ResponseEntity<DataSet> getDataSets(@PathVariable Long id) {
		DataSet dataSet = dataSetService.getDataSets(id);
		if (null != dataSet) {
			/* sample eTag value looks like >> "876876867c88h" */
			return ResponseEntity.status(HttpStatus.OK).eTag("\"" + dataSet.getVersion() + "\"").body(dataSet);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	/* API to add a single data set . accepts a data set JSON and writes to DB 
	 * 
	 * Operation: POST
	 * Accepts JSON object to be inserted in the request body
	 * success response : 201, CREATED
	 * if request body is empty, returns 417 EXPECTATION FAILED.
	 * 
	 * Can be improvised to return more error responses in case of failures while writing etc.
	 * 
	 * */
	@PostMapping("/dataSet/")
	public ResponseEntity<ResponseMessage> addDataSet(@RequestBody DataSet dataSet) {
		if (null != dataSet) {
			dataSetService.createDataSet(dataSet);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Added a DataSet"));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Request Failed"));
		}
	}
	
	/* API to add a set of data sets to DB
	 * 
	 * OPERATION : POST
	 * 
	 * Accepts a JSON array in the request body
	 * 
	 * Response: 201, CREATED for success scenarios 
	 * 
	 * Error response: 417, EXPECTATION FAILED when request body is empty
	 * 
	 * More error responses can be added to improve this API
	 *  
	 *  
	 *  */
	@PostMapping("/allDataSets/")
	public ResponseEntity<ResponseMessage> addAllDataSets(@RequestBody Iterable<DataSet> dataSets) {
		if (null != dataSets) {
			dataSetService.saveAllDataSets(dataSets);
			return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Added dataSets"));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Request Failed"));
		}
	}

	/* API to add all data sets from CSV file to DB 
	 * 
	 * OPERATION: POST
	 * 
	 * Accepts a CSV file in the request
	 * 
	 * Success response: 201, CREATED
	 * 
	 * Failure responses: 417, EXPECTATION FAILED if some exception happens during the process
	 * 
	 * 400, BAD REQUEST if the file supplied is not a CSV file
	 * 
	 * Max size of the CSV file is configured as 2MB (in application.properties)
	 * 
	 * */
	@PostMapping("/allDataSetsFromCSV")
	public ResponseEntity<ResponseMessage> addAllDataSetsFromCSV(@RequestParam("file") MultipartFile file) {
		if (CommonUtil.hasCsvFormat(file)) {
			try {
				dataSetService.save(file);
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Added DataSets successfully"));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("Request Failed"));
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("file format not supported"));
		}
	}
	
	/* API to get all data sets by passing search criteria
	 * sample : http://localhost:8080/api/dataSet/allDataSets?search=(stock:'RBC')
	 * 
	 * operation: GET
	 * 
	 * success response: 200 OK with a set of records in the response body that matches the criteria
	 * 
	 * failure response: 404 not found
	 * 
	 * This API can be modified 
	 * 
	 * >>> to return the response a CSV file for more easiness to handle the data in future
	 * >>> include Etag header in the response that can be used for optimistic locking 
	 *  
	 *  */
	@GetMapping("/allDataSets")
	public ResponseEntity<List<DataSet>> getDataSetsWithSpecificStockTicker(@SearchSpec Specification<DataSet> specs) {
		List<DataSet> dataSets = dataSetService.getAllDataSets(specs);
		if (null != dataSets) {
			return ResponseEntity.status(HttpStatus.OK).body(dataSets);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
}
