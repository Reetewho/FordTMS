package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import co.th.ford.tms.model.GpsType;

@Repository("GpsTypeDao")
public class GpsTypeDaoImpl extends AbstractDao<Integer, GpsType> implements GpsTypeDao {
	
	@SuppressWarnings("unchecked")
	public List<GpsType> findAllGps() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("GPS_STATUS", "1"));
		return (List<GpsType>) criteria.list();
	}

}
