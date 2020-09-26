package co.th.ford.tms.service;

import org.joda.time.LocalDateTime;

public interface CheckNostraSummaryService {
	
	public void callWebService(String systemLoadID, String strCookie, LocalDateTime date);

}
