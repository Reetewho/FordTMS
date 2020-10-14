package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.Gsdb;

public interface GsdbDao {

	Gsdb findByGsdbCode(String gsdb);

	void saveGsdbCode(Gsdb gsdb);
	
	void deleteByGsdbCode(String gsdb);
	
	List<Gsdb> findByGsdbName(int gsdbname);
	
	List<Gsdb> findAllGsdb();
}
