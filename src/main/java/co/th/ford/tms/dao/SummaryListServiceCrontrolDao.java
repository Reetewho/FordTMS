package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.SummaryListServiceCrontrol;

public interface SummaryListServiceCrontrolDao {
	
	List<SummaryListServiceCrontrol> findAll();
	
	SummaryListServiceCrontrol findByLoadID(String loadID);
	
	void save(SummaryListServiceCrontrol slServiceCtrl);
	
	void deleteByID(String loadId);

}
