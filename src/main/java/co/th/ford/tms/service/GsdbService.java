package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.ReportGSDB;


public interface GsdbService {	
	
	void saveGsdbCode(Gsdb gsdb);
	
	void updateGsdbCode(Gsdb gsdb);
	
	void deleteByGsdbCode(String gsdb);

	List<Gsdb> findAllGsdb();

	List<ReportGSDB> querySQLAllGsdb();
	
	List<Gsdb> findByGsdbName(int gsdbname);
	
	Gsdb findByGsdbCode(String gsdb);


}
