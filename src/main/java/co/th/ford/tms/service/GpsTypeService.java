package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.GpsType;
import co.th.ford.tms.model.TruckType;

public interface GpsTypeService {
	List<GpsType> findAllGps();

	GpsType findByGpsType(String GpsType);

	void saveGpsType(GpsType GpsType);
}
