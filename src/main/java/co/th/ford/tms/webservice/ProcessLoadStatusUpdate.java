package co.th.ford.tms.webservice;



import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.soap.SOAPBody;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import co.th.ford.tms.model.LoadStop;



public class ProcessLoadStatusUpdate extends Base{
	
	private static Logger log = Logger.getLogger(ProcessLoadStatusUpdate.class);
	
	private  final String wsEndpoint = "https://fordswsprd.jdadelivers.com/webservices/services/TransportationManager2";	

	//private  final String wsEndpoint = "https://fordswsqa.jdadelivers.com/webservices/services/TransportationManager2";

	
	
	private  final String inputXML = 
			        "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cis=\"http://www.i2.com/cis\"> \r\n" +					 
					"   <soapenv:Header/>\r\n" + 
					"   <soapenv:Body>\r\n" + 
					"      <cis:processLoadStatusUpdate>      \r\n" + 
					"         <cis:ApiHeader>\r\n" + 
					"            <cis:OperationName>processLoadStatusUpdate</cis:OperationName>\r\n" + 
					"         </cis:ApiHeader>\r\n" + 
					"         <cis:LoadStatusUpdateData>      \r\n" + 
					"            <cis:SystemLoadID>%s</cis:SystemLoadID>\r\n" + 
					"            <cis:TrailerNumber>%s</cis:TrailerNumber>          \r\n" + 
					"            <cis:StopArrivalDepartureData>\r\n" + 
					"               <cis:ShippingLocationCode>%s</cis:ShippingLocationCode>\r\n" + 
					"               <cis:StopTypeEnumVal>STR_NULL</cis:StopTypeEnumVal>\r\n" + 
					"               <cis:ArrivalDateTime>%s</cis:ArrivalDateTime>\r\n" + 
					"              <cis:ArrivalEventCode>DRVRCHKIN_</cis:ArrivalEventCode>\r\n" + 
					"               <cis:DepartureDateTime>%s</cis:DepartureDateTime>\r\n" + 
					"               <cis:DepartureEventCode>DRVRCHKOUT_</cis:DepartureEventCode>\r\n" + 
					"               <cis:Latitude>%s</cis:Latitude>\r\n" + 
					"               <cis:Longitude>%s</cis:Longitude>\r\n" + 
					"               <cis:UpdateStatusFlag>true</cis:UpdateStatusFlag>\r\n" + 												
					"            </cis:StopArrivalDepartureData>\r\n" + 
					"         </cis:LoadStatusUpdateData>        \r\n" + 
					"      </cis:processLoadStatusUpdate>\r\n" + 
					"   </soapenv:Body>\r\n" + 
					"</soapenv:Envelope>";
	
	public LoadStop submit(String Authenticationm,String SOAPAction,LoadStop lsModel, String strTruckNumber){		
		
		log.info("lsModel Data -> SystemLoadID : " + lsModel.getLoad().getSystemLoadID()
				+ " getTruckNumber : " + lsModel.getTruckNumber()
				+ " getTruckNumber_CheckUnicode : " + strTruckNumber
				+ " getStopShippingLocation : " + lsModel.getStopShippingLocation()
				+ " getArriveTime : " + lsModel.getArriveTime()
				+ " getDepartureTime : " + lsModel.getDepartureTime()
				+ " getLatitude : " + lsModel.getLatitude()
				+ " getLongitude : " + lsModel.getLongitude()
				);
		
		
		try{			
			SOAPBody sb = getResponse(sendRequest(wsEndpoint, Authenticationm, SOAPAction,
					String.format(inputXML, "" + lsModel.getLoad().getSystemLoadID(), strTruckNumber,
							lsModel.getStopShippingLocation(), lsModel.getArriveTime(),							
							lsModel.getDepartureTime(),
							lsModel.getLatitude(), lsModel.getLongitude())));
			lsModel.setStatus(extract(sb,"ns1:CompletedSuccessfully"));
			if(!lsModel.getStatus().equals("true")) {				
				lsModel.setErrorMessage(extract(sb,"ns1:SystemMessage"));
			}
					
		}catch(Exception e){
			//e.printStackTrace();
			log.error("Error[Exception] : " + e.getMessage());
		}
		return lsModel;
	}
}
