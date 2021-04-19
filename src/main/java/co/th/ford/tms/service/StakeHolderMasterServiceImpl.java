package co.th.ford.tms.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.controller.StakeHolderController;
import co.th.ford.tms.dao.StakeHolderMasterDao;
import co.th.ford.tms.model.ReportStakeHolder;
import co.th.ford.tms.model.StakeHolderMaster;

@Service("SupplierProfileService")
@Transactional
public class StakeHolderMasterServiceImpl implements StakeHolderMasterService {
	
	private static final Logger log = Logger.getLogger(StakeHolderMasterServiceImpl.class);
	
	@Autowired
	private StakeHolderMasterDao dao;
	
	
	public List<StakeHolderMaster> findAllStakeHolder() {
		return dao.findAllStakeHolder();
	}
	
	public StakeHolderMaster findByStakeHolder(String StakeHolder) {
		return dao.findByStakeHolder(StakeHolder);
	}
	
	public StakeHolderMaster findByStakeHolderName(String StakeHolder) {
		return dao.findByStakeHolderName(StakeHolder);
	}
	
	public StakeHolderMaster findByStakeHolderId(int StakeHolder) {
		return dao.findByStakeHolderId(StakeHolder);
	}
	
	public void saveStakeHolder(StakeHolderMaster StakeHolder) {
		dao.saveStakeHolder(StakeHolder);
	}
	
	public List<ReportStakeHolder> displayAllReportStakeHolder() {
		return dao.displayAllReportStakeHolder();
	}
	
	
	public void updateStakeHolder(StakeHolderMaster s) {
		
		log.debug("Show StakeHolderMaster data : " + s.getSTAKE_HOLDER_UN_ID() + " | "  + s.getSTAKE_HOLDER_ID());
		
		StakeHolderMaster entity = dao.findByStakeHolderId(s.getSTAKE_HOLDER_UN_ID());
		if(entity!=null){

			
			entity.setSTAKE_HOLDER_ID(s.getSTAKE_HOLDER_ID());
			entity.setSTAKE_HOLDER_NAME(s.getSTAKE_HOLDER_NAME());
			entity.setSTAKE_HOLDER_ADDRESS(s.getSTAKE_HOLDER_ADDRESS());
			entity.setSTAKE_HOLDER_SUBDISTRICT(s.getSTAKE_HOLDER_SUBDISTRICT());
			entity.setSTAKE_HOLDER_DISTRICT(s.getSTAKE_HOLDER_DISTRICT());
			entity.setSTAKE_HOLDER_PROVINCE(s.getSTAKE_HOLDER_PROVINCE());
			entity.setSTAKE_HOLDER_POST_CODE(s.getSTAKE_HOLDER_POST_CODE());
			entity.setSTAKE_HOLDER_TEL_NO(s.getSTAKE_HOLDER_TEL_NO());
			entity.setSTAKE_HOLDER_FAX_NO(s.getSTAKE_HOLDER_FAX_NO());
			entity.setSTAKE_HOLDER_MOBILE_NO(s.getSTAKE_HOLDER_MOBILE_NO());
			entity.setSTAKE_HOLDER_EMAIL(s.getSTAKE_HOLDER_EMAIL());
			entity.setSTAKE_HOLDER_CONTACT_NAME(s.getSTAKE_HOLDER_CONTACT_NAME());
			entity.setSTAKE_HOLDER_TAX_NO(s.getSTAKE_HOLDER_TAX_NO());
			entity.setSTAKE_HOLDER_SALE_TYPE(s.getSTAKE_HOLDER_SALE_TYPE());
			entity.setSTAKE_HOLDER_PAYMENT_CONDITION(s.getSTAKE_HOLDER_PAYMENT_CONDITION());
			entity.setSTAKE_HOLDER_PAYMENT_METHOD(s.getSTAKE_HOLDER_PAYMENT_METHOD());
			entity.setSTAKE_HOLDER_REF_BANK(s.getSTAKE_HOLDER_REF_BANK());
			entity.setSTAKE_HOLDER_REF_BANK_BRANCH(s.getSTAKE_HOLDER_REF_BANK_BRANCH());
			entity.setSTAKE_HOLDER_REF_ACCOUNT_NO(s.getSTAKE_HOLDER_REF_ACCOUNT_NO());
			entity.setSTAKE_HOLDER_REF_ACCOUNT_NAME(s.getSTAKE_HOLDER_REF_ACCOUNT_NAME());
			entity.setSTAKE_HOLDER_PROJECT(s.getSTAKE_HOLDER_PROJECT());
			entity.setSTAKE_HOLDER_CREDIT_TERM(s.getSTAKE_HOLDER_CREDIT_TERM());
			entity.setSTAKE_HOLDER_TYPE(s.getSTAKE_HOLDER_TYPE());
			entity.setSTAKE_HOLDER_STATUS(s.getSTAKE_HOLDER_STATUS());
			entity.setSTAKE_HOLDER_CREATE_DATE(s.getSTAKE_HOLDER_CREATE_DATE());
			entity.setSTAKE_HOLDER_CREATE_BY(s.getSTAKE_HOLDER_CREATE_BY());
			entity.setSTAKE_HOLDER_UPDATE_DATE(s.getSTAKE_HOLDER_UPDATE_DATE());
			entity.setSTAKE_HOLDER_UPDATE_BY(s.getSTAKE_HOLDER_UPDATE_BY());
			
		}
	}
}
