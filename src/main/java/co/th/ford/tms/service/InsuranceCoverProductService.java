package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.InsuranceCoverProduct;
import co.th.ford.tms.model.Truck;


public interface InsuranceCoverProductService {
	List<InsuranceCoverProduct> findAllInsuranceCoverProduct();

	InsuranceCoverProduct findByInsuranceCoverProduct(String InsuranceCoverProduct);
	
	InsuranceCoverProduct findByInsuranceCoverProductNo(String InsuranceCoverProduct);
	
	InsuranceCoverProduct findByInsuranceCoverProductId(int InsuranceCoverProduct);

	void saveInsuranceCoverProduct(InsuranceCoverProduct InsuranceCoverProduct);
	
	void updateInsuranceCoverProduct(InsuranceCoverProduct InsuranceCoverProduct);
}
