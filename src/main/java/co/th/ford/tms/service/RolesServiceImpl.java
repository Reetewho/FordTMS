package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.RolesDao;
import co.th.ford.tms.model.Roles;

@Service("rolestService")
@Transactional
public class RolesServiceImpl implements RolesService {
	@Autowired
	private RolesDao dao;
	
	
	public List<Roles> findAllRoles() {
		return dao.findAllRoles();
	}
}
