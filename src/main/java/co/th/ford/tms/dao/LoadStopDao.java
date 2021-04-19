package co.th.ford.tms.dao;

import java.util.List;


import co.th.ford.tms.model.LoadStop;


public interface LoadStopDao {

	LoadStop findLoadStopByID(int loadStopID);

	LoadStop findLoadStopByLoadIdAndSeq(int loadStopID, int seq);

	void saveLoadStop(LoadStop loadStop);

	void deleteLoadStopByID(int loadStopID);

	List<LoadStop> findLoadStopByLoadID(int loadID);
	
	List<LoadStop> findLoadStopByLoadIDAndNullDptArv(int loadID);

	List<LoadStop> findAllLoadStops();

	List<LoadStop> findNotCompletedStatusByLoadID(int loadID);
	
	void deleteLoadStopByLoadID(int loadID);

	public List<Object[]> getAllTriggers();

}
