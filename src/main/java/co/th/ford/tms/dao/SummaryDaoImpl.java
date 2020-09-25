package co.th.ford.tms.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.PaymentReport1;
import co.th.ford.tms.model.Summary;


@Repository("SummaryDao")
public class SummaryDaoImpl extends AbstractDao<Integer, Summary> implements SummaryDao {
	
	public void saveSummarylist(Summary Summary) {
		persist(Summary);
	}

	@Override
	public Summary findSummaryLoadByID(int LoadIDSummary) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("LoadIDSummary", LoadIDSummary));
		return (Summary) criteria.uniqueResult();
	}
}
