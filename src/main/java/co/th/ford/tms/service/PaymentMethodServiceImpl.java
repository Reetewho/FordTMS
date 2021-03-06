package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.PaymentMethodDao;
import co.th.ford.tms.model.PaymentMethod;

@Service("PaymentMethodService")
@Transactional
public class PaymentMethodServiceImpl implements PaymentMethodService {
	@Autowired
	private PaymentMethodDao dao;
	
	
	public List<PaymentMethod> findAllPaymentMethod() {
		return dao.findAllPaymentMethod();
	}
}

