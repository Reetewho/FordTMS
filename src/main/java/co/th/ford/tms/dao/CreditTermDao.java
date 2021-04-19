package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.CreditTerm;

public interface CreditTermDao {
	
	List<CreditTerm> findAllCreditTerm();
	
}
