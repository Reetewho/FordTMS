package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;


import co.th.ford.tms.model.Roles;

@Repository("rolesDao")
public class RolesDaoImpl extends AbstractDao<Integer, Roles> implements RolesDao {
	
	@SuppressWarnings("unchecked")
	public List<Roles> findAllRoles() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("status", "1"));
		return (List<Roles>) criteria.list();
	}
	
}
