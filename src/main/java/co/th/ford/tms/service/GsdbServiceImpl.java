package co.th.ford.tms.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.th.ford.tms.dao.GsdbDao;
import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.ReportGSDB;

/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Service("GsdbService")
@Transactional
public class GsdbServiceImpl implements GsdbService {

	@Autowired
	private GsdbDao dao;
	
	

	public void saveGsdbCode(Gsdb gsdb) {
		dao.saveGsdbCode(gsdb);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateGsdbCode(Gsdb t) {
		Gsdb entity = dao.findByGsdbCode(t.getGSDBCODE());
		if(entity!=null){

			entity.setGSDBCODE(t.getGSDBCODE());
			entity.setGSDBNAME(t.getGSDBNAME());
			entity.setGSDBLATITUDE(t.getGSDBLATITUDE());
			entity.setGSDBLONGITUDE(t.getGSDBLONGITUDE());
			entity.setGSDBRADIUS(t.getGSDBRADIUS());
			entity.setGSDBDELIVERYTYPE(t.getGSDBDELIVERYTYPE());
			entity.setGSDBSTARUS(t.getGSDBSTARUS());
			entity.setGSDBCREATERDATE(t.getGSDBCREATERDATE());
			entity.setGSDBCREATEBY(t.getGSDBCREATEBY());
			entity.setGSDBUPDATEDATE(t.getGSDBUPDATEDATE());
			entity.setGSDBUPDATEBY(t.getGSDBUPDATEBY());
			entity.setProvinceId(t.getProvinceId());
			entity.setAreaZone(t.getAreaZone());
		}
	}

	public List<Gsdb> findAllGsdb() {
		return dao.findAllGsdb();
	}
	
	public List<ReportGSDB> querySQLAllGsdb() {
		return dao.querySQLAllGsdb();
	}
	
	public List<Gsdb> findByGsdbName(int gsdbname) {
		return dao.findByGsdbName(gsdbname);
	}

	public Gsdb findByGsdbCode(String gsdb) {
		return dao.findByGsdbCode(gsdb);
	}

	
	@Override
	public void deleteByGsdbCode(String gsdb) {
		dao.deleteByGsdbCode(gsdb);
		
	}

	
}
