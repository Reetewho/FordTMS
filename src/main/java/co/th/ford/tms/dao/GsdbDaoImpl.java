package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.Gsdb;

@Repository("GsdbDao")
public class GsdbDaoImpl extends AbstractDao<Integer, Gsdb> implements GsdbDao {

	/*public User findByUsername(String user) {
		return getByKey(user);
	}*/
	
	@Override
	public void saveGsdbCode(Gsdb gsdb) {
		persist(gsdb);
	}

	public void deleteByGsdbCode(String gsdb) {
		Query query = getSession().createSQLQuery("delete from tb_gsdb_code where GSDB_CODE = :GSDBCODE");
		query.setString("GSDBCODE", gsdb);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Gsdb> findAllGsdb() {
		Criteria criteria = createEntityCriteria();
		return (List<Gsdb>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Gsdb> findByGsdbName(int gsdbname) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("GSDBDELIVERYTYPE", gsdbname));
		return (List<Gsdb>) criteria.list();
	}

	public Gsdb findByGsdbCode(String gsdb) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("GSDBCODE", gsdb));
		return (Gsdb) criteria.uniqueResult();
	}

}
