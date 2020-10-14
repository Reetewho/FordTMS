package co.th.ford.tms.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.SummaryListServiceCrontrol;

@Service("checkNostraSummaryService")
public class CheckNostraSummaryServiceImpl implements CheckNostraSummaryService {
	
	private static Logger log = Logger.getLogger(CheckNostraSummaryServiceImpl.class);
	
	@Autowired
	private SummaryListServiceCrontrolService summaryListServiceCrontrolService;
	
	@Autowired
	private LoadStopService loadStopService;
	
	@Value("${nostra.summary.list.url}")
	private String summaryListNostraUrl;
	
	@Value("${nostra.comp.id}")
	private String companyId;
	
	@Value("${nostra.summary.list.completed.status}")
	private String completedStatus;

	@Override
	public void callWebService(String systemLoadID, String token, LocalDateTime activeDate) {
		
		// Request Headers
		HttpHeaders headersSummary = new HttpHeaders();
		
		// set `content-type` header
		headersSummary.setContentType(MediaType.APPLICATION_JSON);
		headersSummary.add("GISC-CompanyId", companyId);
		headersSummary.add("Cookie", token);
		
		Map<String, String> map = new HashMap<String, String>();

		// Map map = new HashMap<String, String>();
		map.put("shipmentCode", systemLoadID);

		// Transform to JSON
		Gson gson = new Gson();
		String jsons = gson.toJson(map);
		
		// WebService Request
		RestTemplate respSummarytList = new RestTemplate();
		HttpEntity<String> request = new HttpEntity<String>(jsons, headersSummary);
		log.debug("Show request : " + request);
		ResponseEntity<String> entityPOST = respSummarytList.postForEntity(summaryListNostraUrl, request, String.class);
		//log.debug(entityPOST.getBody());
		
		
		JSONObject jsonResp = new JSONObject(entityPOST.getBody());
		
		JSONArray items = jsonResp.getJSONArray("items");
		
		if( null != items && items.length() > 0) { // No Complete
			
			JSONObject item = (JSONObject)items.get(0);
			log.debug(" shipmentStatusId = "+item.getInt("shipmentStatusId"));
			log.debug(" shipmentStatusId Status > 1 are completed. Status is "+ String.valueOf(item.getInt("shipmentStatusId")).indexOf(completedStatus));
			
			if( String.valueOf(item.getInt("shipmentStatusId")).indexOf(completedStatus) == -1 ) {
				
				SummaryListServiceCrontrol slServiceCtrl = summaryListServiceCrontrolService.findByLoadID(systemLoadID);
				
				if( null == slServiceCtrl ) {
					slServiceCtrl =new SummaryListServiceCrontrol();
					slServiceCtrl.setLoadID(systemLoadID);
					slServiceCtrl.setToken(token);
					slServiceCtrl.setActiveDate(activeDate);
					log.debug("#### create control table ");
					summaryListServiceCrontrolService.save(slServiceCtrl);
				}else {
					
					log.debug("##### update");
					log.debug("old token "+slServiceCtrl.getToken());
					slServiceCtrl.setToken(token);
				}
				
			}else { // Completed
				
				summaryListServiceCrontrolService.deleteByID(systemLoadID);
				
			}
			
			// Update Load Stop
			String waypointProgress = item.getString("waypointProgress");
			
			if( null != waypointProgress ) {
				
				Integer seq = Integer.parseInt(waypointProgress.split("/")[0]);
				
				if( seq > 1 ) {
					
					log.info("### systemLoadID : "+systemLoadID + ", seq : "+seq);
					LoadStop loadStop = loadStopService.findLoadStopByLoadIdAndSeq(Integer.parseInt(systemLoadID), seq);
					
					if( null != loadStop ) {
					
						log.info("load stop not null");
						String actualStartDateStr = !item.isNull("actualStartDate") ?  (String)item.get("actualStartDate") : null;
						String actualEndDateStr = !item.isNull("actualEndDate") ? (String)item.get("actualEndDate") : null;
						String etaDateStr = !item.isNull("eta") ? (String)item.get("eta") : null;
						
						if( null != actualStartDateStr) {
							LocalDateTime actualStartDate = LocalDateTime.parse(actualStartDateStr);
							loadStop.setActualStartDate(actualStartDate);
						}
						if( null != actualEndDateStr) {
							LocalDateTime actualEndDate = LocalDateTime.parse(actualEndDateStr);
							loadStop.setActualEndDate(actualEndDate);
						}
						if( null != etaDateStr ) {
							LocalDateTime etaDate = LocalDateTime.parse(etaDateStr);
							loadStop.setEtaDate(etaDate);
						}
						
						log.info("getActualStartDate "+loadStop.getActualStartDate());
						log.info("getActualEndDate "+loadStop.getActualEndDate());
						log.info("getEtaDate "+loadStop.getEtaDate());
						
						loadStopService.updateLoadStop(loadStop);
					
					}
					
				}
				
			}
			
		}
		
	}

}
