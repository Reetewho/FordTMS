package co.th.ford.tms.timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalDateTime;



public class DateDifferent {

	public static void main(String[] args) {

		LocalDateTime localDateTime2 = LocalDateTime.now();
		String dateFormat = "MM/dd/yyyy HH:mm:ss";
		String date2 = localDateTime2.toString(dateFormat);
		
		String dateStart = "10/21/2019 14:50:58";

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(date2);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");

			if(diff >= 1800000) {
				System.out.print( "testfuckingtest");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	
}
