package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import co.th.ford.tms.model.Province;

@Repository("ProvinceDao")
public class ProvinceDaoImpl  extends AbstractDao<Integer, Province> implements ProvinceDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Province> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<Province>) criteria.list();
	}

}
