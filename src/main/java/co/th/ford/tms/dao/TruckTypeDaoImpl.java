package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.TruckType;

@Repository("TruckTypeDao")
public class TruckTypeDaoImpl extends AbstractDao<Integer, TruckType> implements TruckTypeDao {
	
	@SuppressWarnings("unchecked")
	public List<TruckType> findAllTruckType() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("TRUCKTYPE_STATUS", "1"));
		return (List<TruckType>) criteria.list();
	}
	
	public TruckType findByTruckType(String TruckType) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("TRUCKTYPE_TYPE", TruckType));
		return (TruckType) criteria.uniqueResult();
	}
	
	@Override
	public void saveTruckType(TruckType TruckType) {
		persist(TruckType);
	}
}
