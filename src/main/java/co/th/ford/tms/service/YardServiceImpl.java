package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.Yarddao;
import co.th.ford.tms.model.Yard;

@Service("YardService")
@Transactional


public class YardServiceImpl implements YardService {
	@Autowired
	private Yarddao dao;
	
	
	public List<Yard> findAllYard() {
		return dao.findAllYard();
	}
}
