package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import co.th.ford.tms.model.SaleType;

@Repository("SaleTypeDao")
public class SaleTypeDaoImpl extends AbstractDao<Integer, SaleType> implements SaleTypeDao {
	
	@SuppressWarnings("unchecked")
	public List<SaleType> findAllSaleType() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("saletypestatus", "1"));
		return (List<SaleType>) criteria.list();
	}
	
}
