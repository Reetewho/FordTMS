package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.GpsTypeDao;
import co.th.ford.tms.model.GpsType;

@Service("GpsTypetService")
@Transactional
public class GpsTypeServiceImpl implements GpsTypeService {
	@Autowired
	private GpsTypeDao dao;
	
	
	public List<GpsType> findAllGps() {
		return dao.findAllGps();
	}

}
