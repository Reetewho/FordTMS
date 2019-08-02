
package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.Department;

public interface DepartmentDao {
	

	List<Department> findAllDepartment();
	
}
