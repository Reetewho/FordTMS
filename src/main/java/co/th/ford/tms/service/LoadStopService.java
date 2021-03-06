package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.LoadStop;



public interface LoadStopService {	
	
	void saveLoadStop(LoadStop loadStop);
	
	void updateLoadStop(LoadStop loadStop);
	
	void deleteLoadStopByID(int loadStopID);

	List<LoadStop> findAllLoadStops(); 
	
	LoadStop findLoadStopByID(int loadStopID);
	
	LoadStop findLoadStopByLoadIdAndSeq(int loadStopID, int seq);
	
	List<LoadStop> findLoadStopByLoadID(int loadID);
	
	List<LoadStop> findLoadStopByLoadIDAndNullDptArv(int loadID);

	List<LoadStop> findNotCompletedStatusByLoadID(int loadID);
	
	long datetimecount(String datetimecounts);
	
	void deleteLoadStopByLoadID(int loadID);
}
