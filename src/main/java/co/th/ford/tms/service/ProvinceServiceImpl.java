package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.th.ford.tms.dao.ProvinceDao;
import co.th.ford.tms.model.Province;

@Service("ProvinceService")
@Transactional
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceDao provinceDao;
	
	
	@Override
	public List<Province> findAll() {
		// TODO Auto-generated method stub
		return provinceDao.findAll();
	}

}
