package com.rbc.stock.market.application.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rbc.stock.market.application.models.DataSet;

@Repository
public interface DataSetRepository extends CrudRepository<DataSet, Long>, JpaSpecificationExecutor<DataSet>{
	
}
