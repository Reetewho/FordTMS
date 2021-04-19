
package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.TruckType;

public interface TruckTypeDao {
	

	List<TruckType> findAllTruckType();
	
	TruckType findByTruckType(String TruckType);
	
	void saveTruckType(TruckType TruckType);
}
