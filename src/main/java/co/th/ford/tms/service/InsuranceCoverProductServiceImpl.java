package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.InsuranceCoverProductDao;
import co.th.ford.tms.model.InsuranceCoverProduct;
import co.th.ford.tms.model.Truck;



@Service("InsuranceCoverProductService")
@Transactional
public class InsuranceCoverProductServiceImpl implements InsuranceCoverProductService {
	@Autowired
	private InsuranceCoverProductDao dao;
	
	
	public List<InsuranceCoverProduct> findAllInsuranceCoverProduct() {
		return dao.findAllInsuranceCoverProduct();
	}
	
	public InsuranceCoverProduct findByInsuranceCoverProduct(String InsuranceCoverProduct) {
		return dao.findByInsuranceCoverProduct(InsuranceCoverProduct);
	}
	
	public InsuranceCoverProduct findByInsuranceCoverProductNo(String InsuranceCoverProduct) {
		return dao.findByInsuranceCoverProductNo(InsuranceCoverProduct);
	}
	
	public InsuranceCoverProduct findByInsuranceCoverProductId(int InsuranceCoverProduct) {
		return dao.findByInsuranceCoverProductId(InsuranceCoverProduct);
	}
	
	public void saveInsuranceCoverProduct(InsuranceCoverProduct InsuranceCoverProduct) {
		dao.saveInsuranceCoverProduct(InsuranceCoverProduct);
	}
	
	public void updateInsuranceCoverProduct(InsuranceCoverProduct InsuranceCoverProduct) {
		InsuranceCoverProduct entity = dao.findByInsuranceCoverProductId(InsuranceCoverProduct.getInsuranceCover_Id());
		if(entity!=null){
			
			entity.setInsuranceCover_Cover_Product(InsuranceCoverProduct.getInsuranceCover_Cover_Product());
			entity.setInsuranceCover_No(InsuranceCoverProduct.getInsuranceCover_No());
			entity.setInsuranceCover_Limit(InsuranceCoverProduct.getInsuranceCover_Limit());
			entity.setInsuranceCover_date(InsuranceCoverProduct.getInsuranceCover_date());
			entity.setInsuranceCover_Response_By(InsuranceCoverProduct.getInsuranceCover_Response_By());
			entity.setInsuranceCover_Status(InsuranceCoverProduct.getInsuranceCover_Status());
			
		}
		}
		
	
	
	
}


