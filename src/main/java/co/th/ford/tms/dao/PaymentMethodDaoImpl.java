package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import co.th.ford.tms.model.PaymentMethod;

@Repository("PaymentMethodDao")
public class PaymentMethodDaoImpl extends AbstractDao<Integer, PaymentMethod> implements PaymentMethodDao {
	
	@SuppressWarnings("unchecked")
	public List<PaymentMethod> findAllPaymentMethod() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("paymentmethodstatus", "1"));
		return (List<PaymentMethod>) criteria.list();
	}
	
}

