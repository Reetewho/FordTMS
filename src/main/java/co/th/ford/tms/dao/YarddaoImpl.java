package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.Yard;

@Repository("YardDao")

public class YarddaoImpl extends AbstractDao<Integer, Yard> implements Yarddao {
	
	@SuppressWarnings("unchecked")
	public List<Yard> findAllYard() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("yard_status", "Active"));
		return (List<Yard>) criteria.list();
	}
	
	public Yard findByNameYard(String NameYard) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("yard_code", NameYard));
		return (Yard) criteria.uniqueResult();
	}
	
	public Yard findByNameYardId(int NameYard) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("yard_id", NameYard));
		return (Yard) criteria.uniqueResult();
	}
	
	@Override
	public void saveYard(Yard Yard) {
		persist(Yard);
	}
	
}