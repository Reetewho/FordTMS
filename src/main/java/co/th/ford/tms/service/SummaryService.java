package co.th.ford.tms.service;

import co.th.ford.tms.model.Summary;

public interface SummaryService {
	
	void saveSummarylist(Summary Summary);
	
	Summary findSummaryLoadByID(int LoadIDSummary);
	
}
