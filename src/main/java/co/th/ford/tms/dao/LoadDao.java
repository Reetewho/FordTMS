package co.th.ford.tms.dao;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.PaymentReport1;
import co.th.ford.tms.model.ReportSystemLoadData;


public interface LoadDao {

	Load findLoadByID(int loadID);
		
	List<Load> findLoadByusername(String driverid);

	void saveLoad(Load load);
	
	void deleteLoadByID(int loadID);	
	
	Load deleteLoadByLoadID(int loadID);
	
	List<Load> findLoadByCarrierID(int carrierID);
	
	List<Load> findAllLoads();
	
	Load findLoadByCarrierID_SystemLoadID(int carrierID, int systemLoadID);
	
	Load findLoadBySystemLoadID(int systemLoadID);
	
	List<Load> findLoadByDate(LocalDateTime loadStartDateTime,LocalDateTime loadEndDateTime);
	
	
	List<ReportSystemLoadData> findLoadGroupBySystemLoad(int systemLoadID);

	

}
