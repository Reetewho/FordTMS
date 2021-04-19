
package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.GpsType;

public interface GpsTypeDao {
	
	List<GpsType> findAllGps();
	
	GpsType findByGpsType(String GpsType);
	
	void saveGpsType(GpsType GpsType);
	
}
