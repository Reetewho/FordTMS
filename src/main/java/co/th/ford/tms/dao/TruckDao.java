package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckEdit;

public interface TruckDao {

	Truck findByTrucknumber(String trucknumber);

	void saveTrucknumber(Truck truck);
	
	void deleteByTrucknumber(String trucknumber);
	
	List<Truck> findBytype(int trucktype);
	
	List<Truck> findAllTruckNumber();
	
	List<TruckEdit> getTruckEdit(String TruckEdit);

}
