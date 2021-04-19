package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.InsuranceCoverProduct;


public interface InsuranceCoverProductDao {
	
List<InsuranceCoverProduct> findAllInsuranceCoverProduct();
	
InsuranceCoverProduct findByInsuranceCoverProduct(String InsuranceCoverProduct);

InsuranceCoverProduct findByInsuranceCoverProductNo(String InsuranceCoverProduct);

InsuranceCoverProduct findByInsuranceCoverProductId(int InsuranceCoverProduct);
	
void saveInsuranceCoverProduct(InsuranceCoverProduct InsuranceCoverProduct);
}
