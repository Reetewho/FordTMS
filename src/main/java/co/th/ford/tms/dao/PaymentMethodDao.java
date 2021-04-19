package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.PaymentMethod;

public interface PaymentMethodDao {
	
	List<PaymentMethod> findAllPaymentMethod();
	
}
