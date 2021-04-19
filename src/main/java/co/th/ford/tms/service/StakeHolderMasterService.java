package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.ReportStakeHolder;
import co.th.ford.tms.model.StakeHolderMaster;

public interface StakeHolderMasterService {

	List<StakeHolderMaster> findAllStakeHolder();
	
	StakeHolderMaster findByStakeHolder(String StakeHolder);
	
	StakeHolderMaster findByStakeHolderName(String StakeHolder);
	
	StakeHolderMaster findByStakeHolderId(int StakeHolder);
	
	void saveStakeHolder(StakeHolderMaster StakeHolder);
	
	void updateStakeHolder(StakeHolderMaster StakeHolder);
	
	List<ReportStakeHolder> displayAllReportStakeHolder();
	
}
