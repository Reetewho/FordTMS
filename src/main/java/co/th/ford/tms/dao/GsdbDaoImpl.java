package co.th.ford.tms.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.ReportGSDB;

@Repository("GsdbDao")
public class GsdbDaoImpl extends AbstractDao<Integer, Gsdb> implements GsdbDao {

	/*public User findByUsername(String user) {
		return getByKey(user);
	}*/
	
	@Autowired
	Environment environment;
	
	@Value("${profile}")
	private String profile;
	
	
	@Override
	public void saveGsdbCode(Gsdb gsdb) {
		persist(gsdb);
	}

	public void deleteByGsdbCode(String gsdb) {
		Query query = getSession().createSQLQuery("delete from tb_gsdb_code where GSDB_CODE = :GSDBCODE");
		query.setString("GSDBCODE", gsdb);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Gsdb> findAllGsdb() {
		Criteria criteria = createEntityCriteria();
		return (List<Gsdb>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<ReportGSDB> querySQLAllGsdb() {
		Query query = getSession().createSQLQuery(
				  " select GSDB_CODE, GSDB_NAME, Latitude, Longitude, RADIUS, UPDATE_DATE, UPDATE_BY, STATUS "
				+ " from tb_gsdb_code"
				+ " order by GSDB_CODE ");		
		
		List<ReportGSDB> allList = new ArrayList<ReportGSDB>();
		List<Object[]> list=query.list();
		if(list!=null && list.size() >0)
			for (Object[] obj : list) {
				ReportGSDB reportGSDB = new ReportGSDB();
				
				reportGSDB.setGsdbCode(obj[0]==null?"":obj[0].toString());
				reportGSDB.setGsdbName(obj[1]==null?"":obj[1].toString());
				reportGSDB.setGsdbLatitude(obj[2]==null?"":obj[2].toString());
				reportGSDB.setGsdbLongtitude(obj[3]==null?"":obj[3].toString());
				reportGSDB.setGsdbRadius(obj[4]==null?"":obj[4].toString());
				reportGSDB.setGsdbUpdateDate(obj[5]==null?"":obj[5].toString());
				reportGSDB.setGsdbUpdateBy(obj[6]==null?"":obj[6].toString());
				reportGSDB.setGsdbStatus(obj[7]==null?"":obj[7].toString());
				
	

				allList.add(reportGSDB);
			}
		
		return allList;
	}
	
	
	public String getThaiDate(LocalDateTime date) {
		int plus543=0;
		if(environment.getRequiredProperty("local.datetime").equals("th"))plus543=543;
		return (date.getYear()+plus543)+"/"+date.getMonthOfYear()+"/"+date.getDayOfMonth()+" " + date.getHourOfDay()+":"+ date.getMinuteOfHour()+":"+ date.getMillisOfSecond();
	}

	
	@SuppressWarnings("unchecked")
	public List<Gsdb> findByGsdbName(int gsdbname) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("GSDBDELIVERYTYPE", gsdbname));
		return (List<Gsdb>) criteria.list();
	}

	public Gsdb findByGsdbCode(String gsdb) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("GSDBCODE", gsdb));
		return (Gsdb) criteria.uniqueResult();
	}

}
