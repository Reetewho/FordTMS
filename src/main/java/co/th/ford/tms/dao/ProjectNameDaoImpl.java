package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import co.th.ford.tms.model.ProjectName;

@Repository("ProjectNameDao")
public class ProjectNameDaoImpl extends AbstractDao<Integer, ProjectName> implements ProjectNameDao {
	
	@SuppressWarnings("unchecked")
	public List<ProjectName> findAllProject() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("projectnamestatus", "1"));
		return (List<ProjectName>) criteria.list();
	}
	
}
