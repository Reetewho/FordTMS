package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.TruckTypeDao;
import co.th.ford.tms.model.TruckType;

@Service("TruckTypetService")
@Transactional
public class TruckTypeServiceImpl implements TruckTypeService {
	@Autowired
	private TruckTypeDao dao;
	
	
	public List<TruckType> findAllTruckType() {
		return dao.findAllTruckType();
	}
	
	public TruckType findByTruckType(String TruckType) {
		return dao.findByTruckType(TruckType);
	}
	
	public void saveTruckType(TruckType TruckType) {
		dao.saveTruckType(TruckType);
	}
}
