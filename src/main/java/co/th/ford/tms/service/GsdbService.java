package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.Gsdb;


public interface GsdbService {	
	
	void saveGsdbCode(Gsdb gsdb);
	
	void updateGsdbCode(Gsdb gsdb);
	
	void deleteByGsdbCode(String gsdb);

	List<Gsdb> findAllGsdb();
	
	List<Gsdb> findByGsdbName(int gsdbname);
	
	Gsdb findByGsdbCode(String gsdb);


}
