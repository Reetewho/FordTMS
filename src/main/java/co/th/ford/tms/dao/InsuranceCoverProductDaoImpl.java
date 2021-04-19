package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.InsuranceCoverProduct;


@Repository("InsuranceCoverProductDao")
public class InsuranceCoverProductDaoImpl extends AbstractDao<Integer, InsuranceCoverProduct> implements InsuranceCoverProductDao {
	
	@SuppressWarnings("unchecked")
	public List<InsuranceCoverProduct> findAllInsuranceCoverProduct() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("InsuranceCover_Status", "1"));
		return (List<InsuranceCoverProduct>) criteria.list();
	}
	
	public InsuranceCoverProduct findByInsuranceCoverProduct(String InsuranceCoverProduct) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("InsuranceCover_Cover_Product", InsuranceCoverProduct));
		return (InsuranceCoverProduct) criteria.uniqueResult();
	}
	
	public InsuranceCoverProduct findByInsuranceCoverProductNo(String InsuranceCoverProduct) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("InsuranceCover_No", InsuranceCoverProduct));
		return (InsuranceCoverProduct) criteria.uniqueResult();
	}
	
	public InsuranceCoverProduct findByInsuranceCoverProductId(int InsuranceCoverProduct) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("InsuranceCover_Id", InsuranceCoverProduct));
		return (InsuranceCoverProduct) criteria.uniqueResult();
	}
	
	@Override
	public void saveInsuranceCoverProduct(InsuranceCoverProduct InsuranceCoverProduct) {
		persist(InsuranceCoverProduct);
	}
}
