package co.th.ford.tms.test;


import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;





public class TestDate {
	public static void main(String[] args) {
		//String strDate = "2020-10-02 09:20:00.0";
		//String strDate = "2020-9-02 13:10:00.0";
		//String strDate = "2020-10-02T13:17:03";
		String strDate = "2020-10-21T11:27:52.4533333";
		
		System.out.println("########################################## LocalDateTime ##########################################");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss"); 
		formatter.withLocale(Locale.US);
		//LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
		LocalDateTime dateTime = LocalDateTime.parse(strDate);
		System.out.println(">>>>>>>>>>>>>>>>>>> Show : " + dateTime);

		
		//Date getDate = Date.from(dateTime.atZone(ZoneId.of("Asia/Bangkok")).toInstant());
		Date getDate = dateTime.toDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate);
		calendar.add(Calendar.MINUTE, -120);
		

		Date getDate2 = calendar.getTime();
		
		
		
		System.out.println("Show Calendar.MINUTE : " + calendar.get(Calendar.MINUTE));
		System.out.println("Show Calendar.HOUR : " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("Show Calendar.DATE : " + calendar.get(Calendar.DATE));
		
		System.out.println("Show Calendar.MONTH : " + Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
		System.out.println("Show Calendar.MONTH : " + calendar.get(Calendar.YEAR));
		
		System.out.println(">>>>>>>>>>>>>>>>>>> Show getDate2 : " + getDate2);
		
		
		
		String strDateToCompare = "2020-10-14T19:00:00";
		DateTimeFormatter formatterDate = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss"); 
		formatterDate.withLocale(Locale.US);
		LocalDateTime dateTimeToCompare = LocalDateTime.parse(strDateToCompare, formatterDate);
		
		System.out.println("Show dateToCompare : " + dateTimeToCompare);
		
		Date dateToCompare = dateTimeToCompare.toDate();
		Calendar calendarToCompare = Calendar.getInstance();
		calendarToCompare.setTime(dateToCompare);
		calendarToCompare.add(Calendar.MINUTE, 30);
		dateToCompare = calendarToCompare.getTime();
		
		Calendar calendarNow = Calendar.getInstance();
		Date dateNow = calendarNow.getTime();
		
		System.out.println("Show dateNow : " + dateNow);
		
		
		
		if((dateToCompare.compareTo(dateNow) > 0) || (dateToCompare.compareTo(getDate2) == 0)) {
			System.out.println(dateToCompare + " is greater than " + dateNow + " <OR> " + dateToCompare + " is equal to " + dateNow );
		}else {
			System.out.println(dateToCompare + " is less than " + dateNow);
		}
		
		
		
		System.out.println("########################################## LocalDateTime ##########################################");
		
		System.out.println("########################################## SimpleDateFormat ##########################################");
		Calendar calendarCurrent = Calendar.getInstance();
		SimpleDateFormat formatterTime = new SimpleDateFormat("yyyyMMddHHmm");
		System.out.println(formatterTime.format(calendarCurrent.getTime()));
		
		SimpleDateFormat formatterTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		String stringDate = formatterTime2.format(calendarCurrent.getTime());
		System.out.println(stringDate);
		
		System.out.println("########################################## SimpleDateFormat ##########################################");
		
		
		LocalDateTime rightNow = LocalDateTime.now();
		System.out.println("current datetime : " + rightNow);

		rightNow = LocalDateTime.parse("2020-10-02T13:17:03");
		System.out.println("current datetime : " + rightNow);
		
		

		LocalDate startLocalDate = LocalDate.now();
		LocalDate endLocalDate = LocalDate.now();
	
		startLocalDate = LocalDate.parse("2020-10-01");
		endLocalDate = LocalDate.parse("2020-10-29");
		
		System.out.println(startLocalDate.compareTo(endLocalDate));
		
		if(startLocalDate.compareTo(endLocalDate) > 0) {
			System.out.println("StartDate More than EnadDate.");		
		}else {
			System.out.println("StartDate Less than EnadDate.");
		}
		

		
	}
}
