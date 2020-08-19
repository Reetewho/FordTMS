package co.th.ford.tms.service;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import co.th.ford.tms.model.Load;



public interface LoadService {	
	
	void saveLoad(Load load);
	
	void updateLoad(Load load);
	
	void deleteLoadByID(int loadID);
	
	Load deleteLoadByLoadID(int loadID);

	List<Load> findAllLoads(); 
	
	Load findLoadByID(int loadID);
		
	List<Load> findLoadByusername(String driverid);
	
	List<Load> findLoadByCarrierID(int carrierID);
	
	List<Load> findLoadByDate(LocalDateTime loadStartDateTime ,LocalDateTime loadEndDateTime); 
	
	Load findLoadByCarrierID_SystemLoadID(int carrierID, int systemLoadID);

	
	
}
