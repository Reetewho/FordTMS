package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.ReportStakeHolder;
import co.th.ford.tms.model.StakeHolderMaster;

public interface StakeHolderMasterDao {
	
	List<StakeHolderMaster> findAllStakeHolder();
	
	StakeHolderMaster findByStakeHolder(String StakeHolder);
	
	StakeHolderMaster findByStakeHolderName(String StakeHolder);
	
	StakeHolderMaster findByStakeHolderId(int StakeHolder);
	
	void saveStakeHolder(StakeHolderMaster StakeHolder);
	
	List<ReportStakeHolder> displayAllReportStakeHolder();
	
}
