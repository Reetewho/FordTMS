package co.th.ford.tms.dao;

import co.th.ford.tms.model.Summary;

public interface SummaryDao {

	Summary findSummaryLoadByID(int loadID);
	
	void saveSummarylist(Summary Summary);
	
	
}
