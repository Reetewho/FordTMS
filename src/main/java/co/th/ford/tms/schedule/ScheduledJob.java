package co.th.ford.tms.schedule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import co.th.ford.tms.model.SummaryListServiceCrontrol;
import co.th.ford.tms.service.CheckNostraSummaryService;
import co.th.ford.tms.service.SummaryListServiceCrontrolService;

@Component
public class ScheduledJob  {

//	private static Logger log = Logger.getLogger(ScheduledJob.class);
//	
//	@Autowired
//	private SummaryListServiceCrontrolService slServiceControl;
//	
//	@Autowired
//	private CheckNostraSummaryService checkNostraSummaryService;
//	
//	@Value("${nostra.login.url}")
//	private String loginNostraUrl;
//	
//	@Value("${nostra.username}")
//	private String username;
//	 
//	@Value("${nostra.password}")
//	private String password;
//	
//	//@Scheduled(cron = "0 0/10 * 1/1 * ?")
//	//0 0 9 17 * ?
//	@Scheduled(cron = "0 14 15 01 * ?")
//    public void checkSummaryListJob() {
//		
//        log.debug("checkSummaryListJob begin");
//        System.out.println(">>>>>>>>>>>>>>>>>>>> checkSummaryListJob begin");
//        try {
//            
//        	List<SummaryListServiceCrontrol> slList = slServiceControl.findAll();
//        	
//        	log.debug("summary list size : " + slList.size() );
//        	
//        	for(SummaryListServiceCrontrol sl : slList) {
//        		
//        		try {
//        		
//        			checkNostraSummaryService.callWebService(sl.getLoadID(), sl.getToken(), sl.getActiveDate());
//        		
//        		}catch(HttpClientErrorException e) {
//
//					log.error(e.getMessage());
//
//					log.debug(" Re-Login");
//					System.out.println(">>>>>>>>>>>>>>>>>>>> Re-Login");
//
//					Map map = new HashMap<String, String>();
//
//					map.put("username", username);
//					map.put("password", password);
//
//					RestTemplate restTemplate = new RestTemplate();
//					ResponseEntity<String> resp = restTemplate.postForEntity(loginNostraUrl, map, String.class);
//					HttpHeaders headers = resp.getHeaders();
//
//					String set_cookie = headers.getFirst(headers.SET_COOKIE);
//
//					List<String> headerValues = headers.get("Set-Cookie");
//
//					String token = headerValues.get(1);
//					log.debug(" New Token " + token);
//					checkNostraSummaryService.callWebService(sl.getLoadID(), token, sl.getActiveDate());
//
//        		}
//        		
//        	}
//        	
//            log.debug("checkSummaryListJob end");
//            
//        } catch (final Exception e) {
//            log.error("checkSummaryListJob error " , e);
//        }
//    }

}
