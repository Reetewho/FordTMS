package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.TruckType;

public interface TruckTypeService {
	List<TruckType> findAllTruckType();

	TruckType findByTruckType(String TruckType);

	void saveTruckType(TruckType TruckType);
}
