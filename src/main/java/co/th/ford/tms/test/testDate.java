package co.th.ford.tms.test;


import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;





public class testDate {
	public static void main(String[] args) {
		//String strDate = "2020-10-02 09:20:00.0";
		//String strDate = "2020-9-02 13:10:00.0";
		String strDate = "2020-10-02T13:17:03";
		
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss"); 
		formatter.withLocale(Locale.US);
		LocalDateTime dateTime = LocalDateTime.parse(strDate, formatter);
		System.out.println("Show : " + dateTime);

		
		//Date getDate = Date.from(dateTime.atZone(ZoneId.of("Asia/Bangkok")).toInstant());
		Date getDate = dateTime.toDate();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate);
		calendar.add(Calendar.MINUTE, -30);
		
		System.out.println("Show date : " + calendar.getTime());
		
		Date getDate_2 = calendar.getTime();
		
		System.out.println("Show getDate_2 : " + getDate_2);
		
		System.out.println("Show Calendar.MINUTE : " + calendar.get(Calendar.MINUTE));
		System.out.println("Show Calendar.HOUR : " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("Show Calendar.DATE : " + calendar.get(Calendar.DATE));
		
		System.out.println("Show Calendar.MONTH : " + Integer.valueOf(calendar.get(Calendar.MONTH) + 1));
		System.out.println("Show Calendar.MONTH : " + calendar.get(Calendar.YEAR));
		
		
		
		
		Calendar calendarCurrent = Calendar.getInstance();
		SimpleDateFormat formatterTime = new SimpleDateFormat("yyyyMMddHHmm");
		System.out.println(formatterTime.format(calendarCurrent.getTime()));
		
		SimpleDateFormat formatterTime2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
		String stringDate = formatterTime2.format(calendarCurrent.getTime());
		System.out.println(stringDate);
		
	}
}
