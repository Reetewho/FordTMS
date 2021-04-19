package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.ProjectNameDao;
import co.th.ford.tms.model.ProjectName;

@Service("ProjectNameService")
@Transactional
public class ProjectNameServiceImpl implements ProjectNameService {
	@Autowired
	private ProjectNameDao dao;
	
	
	public List<ProjectName> findAllProject() {
		return dao.findAllProject();
	}
}
