package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import co.th.ford.tms.model.Department;

@Repository("departmentDao")
public class DepartmentDaoImpl extends AbstractDao<Integer, Department> implements DepartmentDao {
	
	@SuppressWarnings("unchecked")
	public List<Department> findAllDepartment() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", "1"));
		return (List<Department>) criteria.list();
	}
	
}
