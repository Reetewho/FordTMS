package co.th.ford.tms.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import co.th.ford.tms.model.ShipmentStatus;

public class TestArray {
	 
	public static void main(String[] args) {
		TestArray scriptTestArray = new TestArray();
		try {
			scriptTestArray.startProcess();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startProcess() throws IOException {
		
		Properties prop;

		prop = readPropertiesFile("src/main/resources/application.properties");
		System.out.println(
				"nostra.summary.list.shipment.status : " + prop.getProperty("nostra.summary.list.shipment.status"));
		System.out.println(
				"nostra.waypoint.list.completed.status : " + prop.getProperty("nostra.waypoint.list.completed.status"));
		
	      
        //String strShipmentStatus = "Unassigned,Waiting,Start,OnTheWay,InsideWaypoint,GoingToTerminal,InsideTerminal,Finished,FinishedLate,FinishedIncomplete,FinishedLateIncomplete,Cancelled,Pending";
		String strShipmentStatus = prop.getProperty("nostra.summary.list.shipment.status");
		
        String[] partShipmentStatus = strShipmentStatus.split(",");
        
        List<String> listShipmentStatus = Arrays.asList(partShipmentStatus);
        
        System.out.println("Show size listShipmentStatus : " + listShipmentStatus.size());
        System.out.println("Show listShipmentStatus : " + listShipmentStatus);
        
        List<ShipmentStatus> shipmentStatusData = new ArrayList<>();
        int noShipmentStatus = 0;
        
        for (int i = 0; i < listShipmentStatus.size(); i++) {
        	
        	noShipmentStatus = i + 1;
        	ShipmentStatus shipmentStatus = new ShipmentStatus();
        	shipmentStatus.setShipmentStatusNo(noShipmentStatus);
        	shipmentStatus.setShipmentStatusDesc(listShipmentStatus.get(i));
        	
        	shipmentStatusData.add(shipmentStatus);
		}
        
        for (ShipmentStatus shipmentStatus : shipmentStatusData) {
			System.out.println("Show shipmentstatus : " + shipmentStatus.getShipmentStatusNo() + " | " + shipmentStatus.getShipmentStatusDesc());
		}
		
        System.out.println("Test Match : "
        				  + shipmentStatusData.stream()
        				  	.filter(s -> s.getShipmentStatusDesc().equals("Start"))
        				  	.findFirst().orElse(null));
        
        System.out.println("Test Match find id : "
				  + shipmentStatusData.stream()
				  	.filter(s -> s.getShipmentStatusNo() == 1200)
				  	.findFirst().orElse(null));

        
        ShipmentStatus findShipmentStatus = shipmentStatusData.stream()
	  	.filter(s -> s.getShipmentStatusDesc().equals("GoingToTerminal"))
	  	.findFirst().orElse(null);
        
        
        
        if(findShipmentStatus != null) {
        	 System.out.println("Show shipmentstatus : " + findShipmentStatus.getShipmentStatusNo() + " | " + findShipmentStatus.getShipmentStatusDesc());
        }else {
        	 System.out.println("FindShipmentStatus is null.");
        }
       
        
//        list.stream()
//        .filter(t -> t.getFirstName().equalsIgnoreCase(firstName))
//        .filter(t -> t.getLastName().equalsIgnoreCase(lastName))
//        .findFirst().orElse(null);
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


