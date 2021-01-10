package com.rbc.stock.market.application.services;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.stock.market.application.models.DataSet;


/**
 * @author athira nair
 * 
 *
 */
@Service
public interface DataSetService {
	
	DataSet getDataSets(Long id);
	
	DataSet createDataSet(DataSet dataSet);
	
	public Iterable<DataSet> saveAllDataSets(Iterable<DataSet> dataSets);
	
	public void save(MultipartFile file);
	
	List<DataSet> getAllDataSets(Specification<DataSet> specs);

}
