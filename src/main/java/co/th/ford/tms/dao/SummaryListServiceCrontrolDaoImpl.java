package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.SummaryListServiceCrontrol;

@Repository
public class SummaryListServiceCrontrolDaoImpl extends AbstractDao<Integer, SummaryListServiceCrontrol> implements SummaryListServiceCrontrolDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<SummaryListServiceCrontrol> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<SummaryListServiceCrontrol>) criteria.list();
	}

	@Override
	public void save(SummaryListServiceCrontrol slServiceCtrl) {
		persist(slServiceCtrl);		
	}

	@Override
	public void deleteByID(String loadId) {
		
		Query query = getSession().createSQLQuery(
				  " DELETE  FROM tb_sl_service_ctrl"
				+ " WHERE loadID = :id ");		
		query.setString("id", loadId);
		query.executeUpdate();
		
	}

	@Override
	public SummaryListServiceCrontrol findByLoadID(String loadID) {
		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("loadID", loadID));
		return (SummaryListServiceCrontrol) criteria.uniqueResult();
		
	}

}
