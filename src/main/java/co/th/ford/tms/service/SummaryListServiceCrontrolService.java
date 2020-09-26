package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.SummaryListServiceCrontrol;

public interface SummaryListServiceCrontrolService {
	
	List<SummaryListServiceCrontrol> findAll();
	
	SummaryListServiceCrontrol findByLoadID(String loadID);
	
	void save(SummaryListServiceCrontrol slServiceCtrl);
	
	void deleteByID(String loadId);

}
