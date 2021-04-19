package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckEdit;


public interface TruckService {	
	
	void saveTrucknumber(Truck truck);
	
	void updateTrucknumber(Truck truck);
	
	void deleteByTrucknumber(String trucknumber);

	List<Truck> findAllTruckNumber();
	
	List<Truck> findBytype(int trucktype);
	
	Truck findByTrucknumber(String trucknumber);

	List<TruckEdit> getTruckEdit(String TruckEdit);

}
