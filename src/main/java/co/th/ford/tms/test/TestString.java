package co.th.ford.tms.test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;




public class TestString {
	
	public static void main(String[] args) {
		System.out.println(" shipmentStatusId Status > 1 are completed. Status is "+ "8,9,10,11,13".indexOf(String.valueOf("13")));
		
		System.out.println(" shipmentStatusId Status > 1 are completed. Status is "+ "8,9,10,11,12,13".indexOf(String.valueOf("13")));
		
		String waypointCompletedStatus = "8,9,10,11,12,13";
		
		System.out.println(waypointCompletedStatus.indexOf(String.valueOf(2)));
		
		
		if(waypointCompletedStatus.indexOf("1") != -1) {
			
			System.out.println("more than -1.");
		}else {
			System.out.println("less than -1.");
		}
		
		
		String[] partWaypointCompletedStatus = waypointCompletedStatus.split(",");
		
		System.out.println("Show : " + partWaypointCompletedStatus[1]);
		
		
		
		 // Convert String Array to List
        List<String> list = Arrays.asList(partWaypointCompletedStatus);

        
        System.out.println("to Compare...." + list.contains("14"));
        
        if(!list.contains("14")){
            System.out.println("Check : false");
        }
		
        
      
        
        
        NumberFormat numberFormatterForStopEta = new DecimalFormat("##.0000");
		String resLatitudeStopEta = numberFormatterForStopEta.format(Double.valueOf("2.00005"));
        
		
		System.out.println(resLatitudeStopEta);
		
		
		ReportSystemLoadData reportSystemLoadData = null;
		
		reportSystemLoadData = new ReportSystemLoadData();
		
		
		if(reportSystemLoadData == null)
			System.out.println("Show : null");
		else
			System.out.println("Show : not null");
		
		
		
		String strInput = "ll";
		
		String strInput2 = "";
		
		System.out.println("strInput equals : " + strInput2.trim().equals(""));
		System.out.println("strInput isEmpty : " + strInput2.trim().isEmpty());
		
		if(!strInput.equals("") && !(strInput.isEmpty())) {
			System.out.println("strInput : not null.");
		}else {
			System.out.println("strInput : null");
		}
	}
}
