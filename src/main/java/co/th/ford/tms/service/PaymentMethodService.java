package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.PaymentMethod;

public interface PaymentMethodService {
	List<PaymentMethod> findAllPaymentMethod(); 
}
