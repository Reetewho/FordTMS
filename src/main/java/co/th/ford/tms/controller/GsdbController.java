package co.th.ford.tms.controller;

import java.io.IOException;
import java.util.ArrayList;
//import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.joda.time.DateTime;
//import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.th.ford.tms.model.Roles;
import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckType;
import co.th.ford.tms.model.Department;
import co.th.ford.tms.model.GpsType;
import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.LoadStop;
import co.th.ford.tms.model.User;
import co.th.ford.tms.service.UserService;
import co.th.ford.tms.service.RolesService;
import co.th.ford.tms.service.TruckService;
import co.th.ford.tms.service.TruckTypeService;
import co.th.ford.tms.service.DepartmentService;
import co.th.ford.tms.service.GpsTypeService;
import co.th.ford.tms.service.GsdbService;
//import co.th.ford.tms.aesencrypt.AESCrypt;
/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/



@Controller
@RequestMapping("/")
public class GsdbController {
	org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	UserService uservice;

	@Autowired
	GpsTypeService tgservice;

	@Autowired
	GsdbService gsservice;

	@Autowired
	TruckService tkervice;

	@Autowired
	MessageSource messageSource;

	@Autowired
	TruckTypeService ttervice;

	@Autowired
	RolesService rolesService;

	@Autowired
	DepartmentService departmentService;

	public boolean checkAuthorization(HttpSession session) {
		if (session.getAttribute("S_FordUser") == null) {
			return false;
		} else {
			return true;
		}
	}

	@RequestMapping(value = { "/gsdblist" }, method = RequestMethod.GET)
	public String gsdblist(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		List<Gsdb> FindGsdbCode = gsservice.findAllGsdb();
		model.addAttribute("GSDB", FindGsdbCode);
		return "gsdb-list";
	}

	@RequestMapping(value = { "/addgsdb" }, method = RequestMethod.GET)
	public String addgsdb(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		List<Gsdb> FindGsdbCode = gsservice.findAllGsdb();
		model.addAttribute("GSDB", FindGsdbCode);
		
		return "addgsdb";
	}

	@RequestMapping(value = { "/addgsdb" }, method = RequestMethod.POST)
	public String addgsdb(HttpSession session, @RequestParam String GSDBCode,
			@RequestParam String GSDBName,@RequestParam double Radius,
			@RequestParam double Latitude,@RequestParam double Longitude,
			ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		List<Gsdb> gsdb = gsservice.findAllGsdb();
		for(Gsdb findgsdb : gsdb){
			
			if(findgsdb.getGSDBCODE().equalsIgnoreCase(GSDBCode)) {
				model.addAttribute("Error", "Duplicate name : " + GSDBCode + " Please Try Again.");
				
				List<Gsdb> FindGsdbCode = gsservice.findAllGsdb();
				model.addAttribute("GSDB", FindGsdbCode);

				
				return "addgsdb";
		
			}
			
		}			
		Gsdb  Newgsdb= new Gsdb();
		  
		  DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			String strDateNow = DateTime.now().toString(dtf);
			
			Newgsdb.setGSDBCODE(GSDBCode); 
			Newgsdb.setGSDBNAME(GSDBName);
			Newgsdb.setGSDBSTARUS("1");		 
			Newgsdb.setGSDBRADIUS(Radius);			
			Newgsdb.setGSDBLATITUDE(Latitude);
			Newgsdb.setGSDBLONGITUDE(Longitude);
			
			/* wait for delivery service */
			Newgsdb.setGSDBDELIVERYTYPE(1);
			/* wait for delivery service */
			
			Newgsdb.setGSDBUPDATEBY(((User)session.getAttribute("S_FordUser")).getUsername());
			Newgsdb.setGSDBUPDATEDATE(LocalDateTime.now());		  
		  if(Newgsdb.getGSDBCREATEBY() == null || Newgsdb.getGSDBCREATERDATE() == null) {
			  Newgsdb.setGSDBCREATEBY(((User)session.getAttribute("S_FordUser")).getUsername());
			  Newgsdb.setGSDBCREATERDATE(LocalDateTime.now());  
	        }

		  gsservice.saveGsdbCode(Newgsdb);
		  
		  List<Gsdb> FindGsdbCode = gsservice.findAllGsdb();
			model.addAttribute("GSDB", FindGsdbCode);

		  return "gsdb-list";
	}
	
	@RequestMapping(value = { "/GSDBcode/{GSDBCODE}"}, method = RequestMethod.GET)
	public String GSDBcode(@PathVariable("GSDBCODE") String GSDBcode,ModelMap model) {
			
		Gsdb FindByGsdb = gsservice.findByGsdbCode(GSDBcode);

		if (FindByGsdb.getGSDBSTARUS().equalsIgnoreCase("1")) {
			FindByGsdb.setGSDBSTARUS("0");
			gsservice.updateGsdbCode(FindByGsdb);
		} else {
			FindByGsdb.setGSDBSTARUS("1");
			gsservice.updateGsdbCode(FindByGsdb);
		}

		model.addAttribute("GSDB", FindByGsdb);
		model.addAttribute("edit", true);
		
	
		
		return "redirect:/gsdblist";
	}
	
	@RequestMapping(value = { "/edit-gsdb/{GSDBCODE}" }, method = RequestMethod.GET)
	public String editgsdb(HttpSession session,@PathVariable("GSDBCODE") String GSDBCODE, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";

		Gsdb FindGsdbCode = gsservice.findByGsdbCode(GSDBCODE);
		model.addAttribute("GSDB", FindGsdbCode);
		model.addAttribute("edit", true);
		
		return "edit-gsdb";
	}

	@RequestMapping(value = { "/edit-gsdb/{GSDBCODE}" }, method = RequestMethod.POST)
	public String editgsdb(HttpSession session, @Valid Gsdb Gsdb, BindingResult result, ModelMap model) {
		if (!checkAuthorization(session))return "redirect:/login";
	
		Gsdb.setGSDBUPDATEBY(((User)session.getAttribute("S_FordUser")).getUsername());
		Gsdb.setGSDBUPDATEDATE(LocalDateTime.now());
			
		gsservice.updateGsdbCode(Gsdb);
	
		return "redirect:/gsdblist";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "/UpdateGsdbExcel" }, method = RequestMethod.POST)
	public String UpdateGsdbExcel(HttpSession session, HttpServletRequest requestServer,
			@RequestBody String getSelectItemData, ModelMap model)
			throws InterruptedException, IOException {
		
		String json = getSelectItemData;	
		System.out.println(json);
		String replaceString1 = json.replace("[[","[");
		String replaceString2 = replaceString1.replace("]]","]");
		System.out.println(replaceString2);

		JSONObject obj = new JSONObject(replaceString2);
		JSONArray arr = obj.getJSONArray("theGroupData");		

		for (int i = 0; i < arr.length(); i++) {
			
			float intGsdbCode = 0;
			intGsdbCode = Float.parseFloat(arr.getJSONObject(i).getString("RADIUS"));
		
				Gsdb GsdbSave = new Gsdb();
			
				GsdbSave.setGSDBCODE(arr.getJSONObject(i).getString("CODE"));
				GsdbSave.setGSDBNAME(arr.getJSONObject(i).getString("NAME"));
				GsdbSave.setGSDBLATITUDE(Double.valueOf(arr.getJSONObject(i).getString("LAT")));
				GsdbSave.setGSDBLONGITUDE(Double.valueOf(arr.getJSONObject(i).getString("LON")));
				GsdbSave.setGSDBRADIUS(intGsdbCode);
				GsdbSave.setGSDBSTARUS("1");	
				
				/* wait for delivery service */
				GsdbSave.setGSDBDELIVERYTYPE(1);
				/* wait for delivery service */
				
				  GsdbSave.setGSDBUPDATEBY(((User)session.getAttribute("S_FordUser")).getUsername());
				  GsdbSave.setGSDBUPDATEDATE(LocalDateTime.now());		  
			  if(GsdbSave.getGSDBCREATEBY() == null || GsdbSave.getGSDBCREATERDATE() == null) {
				  GsdbSave.setGSDBCREATEBY(((User)session.getAttribute("S_FordUser")).getUsername());
				  GsdbSave.setGSDBCREATERDATE(LocalDateTime.now());  
		        }
				
			  gsservice.saveGsdbCode(GsdbSave);
			
		}
	   
		return "redirect:/gsdblist";
		
		
	}
	
	
	
	
}
