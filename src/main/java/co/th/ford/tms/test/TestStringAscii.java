package co.th.ford.tms.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestStringAscii {
	
	private static String REPLACE = "";
	
	public static void main(String[] args) throws IOException {
		
		String str = "การทำงาน";
		for(int i=0; i<str.length(); i++)
		{
		  int asciiValue = str.charAt(i);
		  System.out.println(str.charAt(i) + "=" + asciiValue);
		}
		//OUTPUT
		//A=65
		//B=66
		//C=67
		
		Properties prop;

		
		prop = readPropertiesFile("src/main/resources/application.properties");
		System.out.println(
				"nostra.summary.list.shipment.status : " + prop.getProperty("ford.truck.replace.regex"));

		String strTruckNemberReplace = prop.getProperty("ford.truck.replace.regex");

		//Pattern pattern = re.compile(r"[^\u0E00-\u0E7Fa-zA-Z' ]|^'|'$|''")
		String text = "qฟ4-52855";		
		Pattern p = Pattern.compile(strTruckNemberReplace);		
		Matcher matcher = p.matcher(text);
		String INPUT ="";
		int count = 0;
		
		while(matcher.find()){
			count++;
		    //System.out.println("Found match at: "  + matcher.start() + " to " + matcher.end());
		}
		INPUT = matcher.replaceAll(REPLACE);
		System.out.println("Show text : " + INPUT);

		System.out.println("Show count : " + count);
		
		String strSendTruckNumber = "";
		strSendTruckNumber = text;
		if (count > 0) {
			System.out.println("Show index : " + text.indexOf("-"));
			System.out.println("Show index : " +  strSendTruckNumber.substring((int) text.indexOf("-") + 1));
			
		}
	    
	    
	    
	    
	
	}
	
	public static Properties readPropertiesFile(String fileName) throws IOException {
	      FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         fnfe.printStackTrace();
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	   }
}
