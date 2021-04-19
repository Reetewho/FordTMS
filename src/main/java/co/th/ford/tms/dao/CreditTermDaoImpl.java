package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import co.th.ford.tms.model.CreditTerm;

@Repository("CreditTermDao")
public class CreditTermDaoImpl extends AbstractDao<Integer, CreditTerm> implements CreditTermDao {
	
	@SuppressWarnings("unchecked")
	public List<CreditTerm> findAllCreditTerm() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("credittermstatus", "1"));
		return (List<CreditTerm>) criteria.list();
	}
	
}
