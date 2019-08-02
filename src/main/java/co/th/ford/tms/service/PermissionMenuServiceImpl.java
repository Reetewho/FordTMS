package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.PermissionMenuDao;

import co.th.ford.tms.model.PermissionMenu;

@Service("PermissionMenuService")
@Transactional
public  class PermissionMenuServiceImpl implements PermissionMenuService {
	@Autowired
	private PermissionMenuDao dao;


	public List<PermissionMenu> getPermissionMenu(int idroless) {
		return dao.getPermissionMenu(idroless);
	} 
}
