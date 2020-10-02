package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.Truck;

@Repository("truckDao")
public class TruckDaoImpl extends AbstractDao<Integer, Truck> implements TruckDao {

	/*public User findByUsername(String user) {
		return getByKey(user);
	}*/
	
	@Override
	public void saveTrucknumber(Truck truck) {
		persist(truck);
	}

	public void deleteByTrucknumber(String trucknumber) {
		Query query = getSession().createSQLQuery("delete from tb_trucknumber where trucknumber = :TRUCK_NUMBER");
		query.setString("TRUCK_NUMBER", trucknumber);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Truck> findAllTruckNumber() {
		Criteria criteria = createEntityCriteria();
		return (List<Truck>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Truck> findBytype(int trucktype) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("TRUCK_TYPE", trucktype));
		return (List<Truck>) criteria.list();
	}

	public Truck findByTrucknumber(String trucknumber) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("TRUCK_NUMBER", trucknumber));
		return (Truck) criteria.uniqueResult();
	}

}
