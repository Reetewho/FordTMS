package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.DepartmentDao;
import co.th.ford.tms.model.Department;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentDao dao;
	
	
	public List<Department> findAllDepartment() {
		return dao.findAllDepartment();
	}
}
