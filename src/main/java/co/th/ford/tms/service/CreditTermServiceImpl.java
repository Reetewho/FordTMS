package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.CreditTermDao;
import co.th.ford.tms.model.CreditTerm;

@Service("CreditTermService")
@Transactional
public class CreditTermServiceImpl implements CreditTermService {
	@Autowired
	private CreditTermDao dao;
	
	
	public List<CreditTerm> findAllCreditTerm() {
		return dao.findAllCreditTerm();
	}
}
