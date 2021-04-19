package co.th.ford.tms.dao;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Repository;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.ReportSystemLoadData;


@Repository("loadDao")
public class LoadDaoImpl extends AbstractDao<Integer, Load> implements LoadDao {

	/*public User findByUsername(String user) {
		return getByKey(user);
	}*/

	public void saveLoad(Load load) {
		persist(load);
	}

	public void deleteLoadByID(int loadID) {
		Query query = getSession().createSQLQuery(
				  " delete tb_load,tb_loadstop,tb_setstopeta "
				+ " from tb_load lef join tb_loadstop on tb_load.loadID=tb_loadstop.loadID "
				+ " left join tb_setstopeta  on tb_loadstop.id=tb_setstopeta.loadStopID   "
				+ " where tb_load.loadID = :loadID ");
		query.setInteger("loadID", loadID);
		query.executeUpdate();
		
		/*query = getSession().createSQLQuery("delete from tb_load where loadID = :loadID");
		query.setInteger("loadID", loadID);
		query.executeUpdate();*/
	}
	
	
	public Load deleteLoadByLoadID(int loadID) {
		Query query = getSession().createSQLQuery(
				  " Delete "
				+ " FROM tb_load "
				+ " WHERE tb_load.loadID = :loadID ");
		query.setInteger("loadID", loadID);
		query.executeUpdate();
		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("loadID", loadID));
		return (Load) criteria.uniqueResult();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Load> findAllLoads() {
		Criteria criteria = createEntityCriteria();
		return (List<Load>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Load> findLoadByCarrierID(int carrierID) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("carrierID", carrierID));
		criteria.add(Restrictions.or(Restrictions.ne("loadAction", "MANUAL"), Restrictions.isNull("loadAction")));
		return (List<Load>) criteria.list();
	}

	public Load findLoadByID(int loadID) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("loadID", loadID));
		return (Load) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Load> findLoadByusername(String driverid) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("driverid", driverid));
		return (List<Load>) criteria.list();
	}
	
	public Load findLoadByCarrierID_SystemLoadID(int carrierID, int systemLoadID) {
		  Criteria criteria = createEntityCriteria();
		  criteria.add(Restrictions.eq("carrierID", carrierID));
		  criteria.add(Restrictions.eq("systemLoadID", systemLoadID));
		  return (Load) criteria.uniqueResult();
	}
	
	public Load findLoadBySystemLoadID(int systemLoadID) {
		  Criteria criteria = createEntityCriteria();
		  criteria.add(Restrictions.eq("systemLoadID", systemLoadID));
		  return (Load) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Load> findLoadByDate(LocalDateTime loadStartDateTime,LocalDateTime loadEndDateTime){
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.between("loadStartDateTime", loadStartDateTime, loadEndDateTime));
		return (List<Load>) criteria.list();
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<ReportSystemLoadData> findLoadGroupBySystemLoad(int systemLoadID){
		
		Query query = getSession().createSQLQuery(
				  " SELECT l.systemLoadID, l.loadID, DATE_FORMAT(c.loadDate,'%d/%m/%Y') AS loadDate , l.completedFlag,  "				
				+ " (SELECT count(*) FROM tb_loadstop ls WHERE l.loadID = ls.loadID ) AS COUNT_LOADSTOP "				
				+ " FROM tb_load l, tb_carrier c "
				+ " WHERE l.carrierID = c.carrierID AND l.systemLoadID = :systemLoadID "
				+ " GROUP BY l.systemLoadID, l.loadID, c.loadDate, l.completedFlag ");		
		query.setInteger("systemLoadID", systemLoadID);
		List<ReportSystemLoadData> allList = new ArrayList<ReportSystemLoadData>();
		List<Object[]> list = query.list();
		
		if(list!=null && list.size() >0)
			for (Object[] obj : list) {
			
				ReportSystemLoadData loadData=new ReportSystemLoadData();
				loadData.setSystemLoadID(obj[0]==null?"":obj[0].toString());
				loadData.setLoadID(obj[1]==null?"":obj[1].toString());
				loadData.setLoadDate(obj[2]==null?"":obj[2].toString());
				loadData.setLoadStatus(obj[3]==null?"":obj[3].toString());
				loadData.setCountLoadStop(obj[4]==null?"":obj[4].toString());
				
				allList.add(loadData);
			}
		
		return allList;
	 }
	 
	
}
