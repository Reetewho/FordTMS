package co.th.ford.tms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.SummaryDao;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.Summary;
@Service("SummaryService")
@Transactional

public class SummaryServiceImpl implements SummaryService{
	@Autowired
	private SummaryDao dao;

	public void saveSummarylist(Summary Summary) {
		dao.saveSummarylist(Summary);
	}
	
	public Summary findSummaryLoadByID(int LoadIDSummary) {
		return dao.findSummaryLoadByID(LoadIDSummary);
	}
	
}
