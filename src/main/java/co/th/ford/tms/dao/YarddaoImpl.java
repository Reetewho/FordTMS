package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.City;
import co.th.ford.tms.model.Yard;

@Repository("YardDao")

public class YarddaoImpl extends AbstractDao<Integer, Yard> implements Yarddao {
	
	@SuppressWarnings("unchecked")
	public List<Yard> findAllYard() {
		Criteria criteria = createEntityCriteria();
		//criteria.add(Restrictions.eq("status", "1"));
		return (List<Yard>) criteria.list();
	}
	
}