package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import co.th.ford.tms.model.LoadStop;


@Repository("loadStopDao")
public class LoadStopDaoImpl extends AbstractDao<Integer, LoadStop> implements LoadStopDao {

	/*public User findByUsername(String user) {
		return getByKey(user);
	}*/

	public void saveLoadStop(LoadStop loadStop) {
		persist(loadStop);
	}

	public void deleteLoadStopByID(int loadStopID) {
		
		Query query = getSession().createSQLQuery(
				  " delete tb_loadstop,tb_setstopeta "
				+ " from tb_loadstop  left join tb_setstopeta  on tb_loadstop.id=tb_setstopeta.loadStopID   "
				+ " where tb_loadstop.id = :loadStopID");
		
		query.setInteger("loadStopID", loadStopID);
		query.executeUpdate();
	}
	
	
	public void deleteLoadStopByLoadID(int loadID) {
		Query query = getSession().createSQLQuery(
				  " Delete "
				+ " FROM tb_loadstop "
				+ " WHERE loadID = :loadID ");
		query.setInteger("loadID", loadID);
		query.executeUpdate();	
	}
	
	
	@SuppressWarnings("unchecked")
	public List<LoadStop> findAllLoadStops() {
		Criteria criteria = createEntityCriteria();
		return (List<LoadStop>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<LoadStop> findLoadStopByLoadID(int loadID) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("loadID", loadID));
		return (List<LoadStop>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<LoadStop> findLoadStopByLoadIDAndNullDptArv(int loadID) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("loadID", loadID));
		criteria.add(Restrictions.or(
				   Restrictions.isNull("departureTime"),
				   Restrictions.isNull("arriveTime"))
				);
		return (List<LoadStop>) criteria.list();
	}

	public LoadStop findLoadStopByID(int loadStopid) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", loadStopid));
		return (LoadStop) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<LoadStop> findNotCompletedStatusByLoadID(int loadID) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("loadID", loadID));
		criteria.add(Restrictions.ne("status", "update"));
		criteria.add(Restrictions.ne("status", "setStop")); 
		return (List<LoadStop>) criteria.list();
	}

	@Override
	public LoadStop findLoadStopByLoadIdAndSeq(int systemLoads, int seq) {
		
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("SystemLoads", systemLoads));
		criteria.add(Restrictions.eq("stopSequence", seq));
		return (LoadStop) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllTriggers() {
        Query query = getSession().createSQLQuery("SELECT TRIGGER_NAME, "
        		+ " DATE_FORMAT(FROM_UNIXTIME(START_TIME/1000), '%d-%m-%Y %H:%i') AS START_TIME, "
        		+ " DATE_FORMAT(FROM_UNIXTIME(NEXT_FIRE_TIME/1000), '%d-%m-%Y %H:%i') AS NEXT_FIRE_TIME, "
        		+ " DATE_FORMAT(FROM_UNIXTIME(PREV_FIRE_TIME/1000), '%d-%m-%Y %H:%i') AS LAST_FIRE_TIME "
        		+ " FROM QRTZ_TRIGGERS");	
        //Query query = getSession().createSQLQuery("SELECT * FROM qrtz_CRON_TRIGGERS WHERE TRIGGER_NAME<>:triggerName");	
        //query.setString("triggerName", triggerName);
        List<Object[]> list=query.list();
		return list;		
	
	}
}
