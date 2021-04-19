package co.th.ford.tms.webservice;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.Charset;


import javax.net.ssl.HttpsURLConnection;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;

import org.apache.log4j.Logger;




public class Base {	
	
	private static Logger log = Logger.getLogger(Base.class);
	
	public  String sendRequest(String wsEndpoint,String Authorization, String SOAPAction, String xmlInput) throws Exception {
		
		//System.out.println(Authorization);
		//System.out.println(SOAPAction);
		//System.out.println(xmlInput);
		log.info("Authorization : " + Authorization);
		log.info("SOAPAction : " + SOAPAction);
		log.info("xmlInput : " + xmlInput);
		URL url = new URL(wsEndpoint);
	    HttpsURLConnection httpConn = (HttpsURLConnection)url.openConnection();
	     
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
	
		byte[] buffer = new byte[xmlInput.length()];
		buffer = xmlInput.getBytes();
		bout.write(buffer);
		byte[] b = bout.toByteArray();
		
		// Add headers
		httpConn.setRequestMethod("POST");
		httpConn.setRequestProperty("Content-Length",String.valueOf(b.length));
		httpConn.addRequestProperty("Authorization", Authorization);	          
		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); // We send our data in JSON format
		httpConn.setRequestProperty("SOAPAction", SOAPAction);		
		httpConn.setRequestMethod("POST");
		httpConn.setDoOutput(true);
		httpConn.setDoInput(true);
		//httpConn.setUseCaches(false);
		//httpConn.setConnectTimeout(30 * 1000);
		//httpConn.setReadTimeout(30 * 1000);
		

		
		OutputStream out = httpConn.getOutputStream();
		out.write(b);
		out.close();
	
		log.info("HttpsURLConnection ResponseCode : " + httpConn.getResponseCode());
		
		BufferedReader in = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
		String responseString = "";
		String outputString = "";
		while ((responseString = in.readLine()) != null) {
			outputString += responseString;
		}
		in.close();
		//System.out.println(outputString);
		log.info("Response sendRequest : " + outputString);
		
		httpConn.disconnect();
		
		return outputString;
	}
	
	
	public  SOAPBody getResponse(String xml) throws Exception {
		MessageFactory factory = MessageFactory.newInstance();
	    SOAPMessage msg = factory.createMessage(new MimeHeaders(), new ByteArrayInputStream(xml.getBytes(Charset.forName("UTF-8"))));
	    msg.saveChanges();
	    return msg.getSOAPBody();
	}
	
	public  String extract(SOAPBody sb, String tag){
		if (sb.getElementsByTagName(tag).getLength() > 0) {
			return sb.getElementsByTagName(tag).item(0).getFirstChild().getNodeValue();
		}
		return null;
		//return sb.getElementsByTagName(tag).item(0).getFirstChild().getNodeValue();
	}
	
	
	}
