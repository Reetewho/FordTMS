package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.SaleTypeDao;
import co.th.ford.tms.model.SaleType;

@Service("SaleTypeService")
@Transactional
public class SaleTypeServiceImpl implements SaleTypeService {
	@Autowired
	private SaleTypeDao dao;
	
	
	public List<SaleType> findAllSaleType() {
		return dao.findAllSaleType();
	}
}
