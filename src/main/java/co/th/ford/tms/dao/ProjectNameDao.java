package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.ProjectName;

public interface ProjectNameDao {
	
	List<ProjectName> findAllProject();
	
}

