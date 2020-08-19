package co.th.ford.tms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import co.th.ford.tms.dao.LoadStopDao;
import co.th.ford.tms.model.LoadStop;

@Service("loadStopService")
@Transactional
public class LoadStopServiceImpl implements LoadStopService {

	@Autowired
	private LoadStopDao dao;
	
	@Autowired
	Environment environment;
	

	public void saveLoadStop(LoadStop loadStop) {
		dao.saveLoadStop(loadStop);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateLoadStop(LoadStop ls) {
		LoadStop entity = dao.findLoadStopByID(ls.getId());
		if(entity!=null){
			entity.setLoadID(ls.getLoadID());
			entity.setStopSequence(ls.getStopSequence());
			entity.setStopShippingLocation(ls.getStopShippingLocation());
			entity.setStopShippingLocationName(ls.getStopShippingLocationName());
			entity.setTruckNumber(ls.getTruckNumber());
			entity.setDepartureTime(ls.getDepartureTime());
			entity.setArriveTime(ls.getArriveTime());
			
			//----------------------------------------------------------------
			entity.setShipingOrder(ls.getShipingOrder());
			entity.setWaybillNumber(ls.getWaybillNumber());
			entity.setManifest(ls.getManifest());
			entity.setLoadstopremark(ls.getLoadstopremark());
			entity.setLatitude(ls.getLatitude());
			entity.setLongitude(ls.getLongitude());
			//----------------------------------------------------------------
			entity.setStatusLoad(ls.getStatusLoad());
			entity.setStatus(ls.getStatus());
			entity.setErrorMessage(ls.getErrorMessage());
			entity.setLastUpdateDate(ls.getLastUpdateDate());
			entity.setLastUpdateUser(ls.getLastUpdateUser());
		}
	}

	public void deleteLoadStopByID(int loadStopID) {
		dao.deleteLoadStopByID(loadStopID);
	}
	
	public List<LoadStop> findAllLoadStops() {
		return dao.findAllLoadStops();
	}

	public List<LoadStop> findLoadStopByLoadID(int loadID) {
		return dao.findLoadStopByLoadID(loadID);
	}
	
	public LoadStop findLoadStopByID(int loadStopID) {
		return dao.findLoadStopByID(loadStopID);
	}

	public  List<LoadStop> findNotCompletedStatusByLoadID(int loadID){
		return dao.findNotCompletedStatusByLoadID(loadID);
	}	
	
	public long datetimecount(String datetimecounts) {
		
		LocalDateTime localDateTime2 = LocalDateTime.now();
		String dateFormat = "yyyy/MM/dd HH:mm:ss";
		String date2 = localDateTime2.toString(dateFormat);
		
		//String getDateNow = getThaiDate(LocalDateTime.parse(date2, DateTimeFormat.forPattern("yyyy/MM/dd HH:mm:ss")));


		//System.out.println("---------> Start Request[POSTdate2] <--------- " +"get Result datetimecount : " + getDateNow 	);
		System.out.println("---------> Start Request[POSTdate2] <--------- " +"get Result datetimecount : " + datetimecounts 	);

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(datetimecounts);
			d2 = format.parse(date2);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

//			long diffSeconds = diff / 1000 % 60;
//			long diffMinutes = diff / (60 * 1000) % 60;
//			long diffHours = diff / (60 * 60 * 1000) % 24;
//			long diffDays = diff / (24 * 60 * 60 * 1000);

//			System.out.print(diffDays + " days, ");
//			System.out.print(diffHours + " hours, ");
//			System.out.print(diffMinutes + " minutes, ");
//			System.out.print(diffSeconds + " seconds.");
			
			return diff;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return 99;
		
	}
	
	public String getThaiDate(LocalDateTime date) {
		int plus543=0;
		if(environment.getRequiredProperty("local.datetime").equals("th"))plus543=543;
		return (date.getYear()+plus543)+"/"+date.getMonthOfYear()+"/"+date.getDayOfMonth()+" " + date.getHourOfDay()+":"+ date.getMinuteOfHour()+":"+ date.getMillisOfSecond();
	}
}
