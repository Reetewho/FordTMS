package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.SummaryListServiceCrontrolDao;
import co.th.ford.tms.model.SummaryListServiceCrontrol;

@Service("summaryListServiceCrontrolService")
@Transactional
public class SummaryListServiceCrontrolServiceImpl implements SummaryListServiceCrontrolService {

	@Autowired
	private SummaryListServiceCrontrolDao dao;
	
	@Override
	public List<SummaryListServiceCrontrol> findAll() {
		
		return dao.findAll();
	}

	@Override
	public void save(SummaryListServiceCrontrol slServiceCtrl) {
		
		dao.save(slServiceCtrl);
	}

	@Override
	public void deleteByID(String loadId) {
		
		dao.deleteByID(loadId);
	}

	@Override
	public SummaryListServiceCrontrol findByLoadID(String loadID) {
		
		return dao.findByLoadID(loadID);
	}

}
