package com.rbc.application.stock.market.application.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rbc.application.stock.market.application.models.DataSet;
import com.rbc.application.stock.market.application.repositories.DataSetRepository;
import com.rbc.application.stock.market.application.utils.CommonUtil;

@Service
public class DataSetServiceImpl implements DataSetService {

	@Autowired
	private DataSetRepository repository;

	@Override
	public DataSet getDataSets(Long id) {
		Optional<DataSet> result = repository.findById(id);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}
	}

	@Override
	public DataSet createDataSet(DataSet dataSet) {
		return repository.save(dataSet);
	}

	@Override
	public Iterable<DataSet> saveAllDataSets(Iterable<DataSet> dataSets) {
		return repository.saveAll(dataSets);
	}

	@Override
	public void save(MultipartFile file) {
		try {
			List<DataSet> dataSets = CommonUtil.csvToDataSet(file.getInputStream());
			repository.saveAll(dataSets);
		} catch (IOException e) {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		}

	}

	@Override
	public List<DataSet> getAllDataSets(Specification<DataSet> specs) {
		return repository.findAll(Specification.where(specs));
	}

}
