package co.th.ford.tms.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
//import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.th.ford.tms.model.Roles;
import co.th.ford.tms.model.StakeHolderMaster;
import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckEdit;
import co.th.ford.tms.model.TruckStatusList;
import co.th.ford.tms.model.TruckType;
import co.th.ford.tms.model.City;
import co.th.ford.tms.model.Department;
import co.th.ford.tms.model.FleetCard;
import co.th.ford.tms.model.GpsType;
import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.InsuranceCoverProduct;
import co.th.ford.tms.model.InsuranceMotor;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.ProjectName;
import co.th.ford.tms.model.User;
import co.th.ford.tms.model.Yard;
import co.th.ford.tms.service.UserService;
import co.th.ford.tms.service.YardService;
import co.th.ford.tms.service.RolesService;
import co.th.ford.tms.service.StakeHolderMasterService;
import co.th.ford.tms.service.TruckService;
import co.th.ford.tms.service.TruckTypeService;
import co.th.ford.tms.service.CityService;
import co.th.ford.tms.service.DepartmentService;
import co.th.ford.tms.service.FleetCardService;
import co.th.ford.tms.service.GpsTypeService;
import co.th.ford.tms.service.InsuranceCoverProductService;
import co.th.ford.tms.service.InsuranceMotorService;
import co.th.ford.tms.service.ProjectNameService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import co.th.ford.tms.aesencrypt.AESCrypt;
/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Controller
@RequestMapping("/")
public class TruckController {
	org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger log = Logger.getLogger(TruckController.class);

	@Autowired
	UserService uservice;
	
	@Autowired
	FleetCardService fcervice;
	
	@Autowired
	StakeHolderMasterService spervice;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	InsuranceMotorService imservice;
	
	@Autowired
	InsuranceCoverProductService icpservice;
	
	@Autowired
	GpsTypeService tgservice;
	
	@Autowired
	YardService ydservice;
	
	@Autowired
	TruckService tkervice;

	@Autowired
	MessageSource messageSource;

	@Autowired
	TruckTypeService ttervice;
		
	@Autowired
	RolesService rolesService;
	
	@Autowired
	ProjectNameService pnservice;

	@Autowired
	DepartmentService departmentService;
	
	 @Value("${StakeHolderMaster.Id}")
	 private String StakeHolderMasterId;
		
	 @Value("${StakeHolderMaster.Name}")
	 private String StakeHolderMasterName;
		
	 @Value("${StakeHolderMaster.Address}")
	 private String StakeHolderMasterAddress;
		
	 @Value("${StakeHolderMaster.Subdistrict}")
	 private String StakeHolderMasterSubdistrict;
		
	 @Value("${StakeHolderMaster.District}")
	 private String StakeHolderMasterDistrict;
		
	 @Value("${StakeHolderMaster.Province}")
	 private String StakeHolderMasterProvince;
		
	 @Value("${StakeHolderMaster.Postcode}")
	 private String StakeHolderMasterPostcode;
		
	 @Value("${StakeHolderMaster.TelNo}")
	 private String StakeHolderMasterTelNo;
		
	 @Value("${StakeHolderMaster.FaxNo}")
	 private String StakeHolderMasterFaxNo;
		
	 @Value("${StakeHolderMaster.MobileNo}")
	 private String StakeHolderMasterMobileNo;
		
	 @Value("${StakeHolderMaster.Email}")
	 private String StakeHolderMasterEmail;
		
	 @Value("${StakeHolderMaster.ContactName}")
	 private String StakeHolderMasterContactName;
		
	 @Value("${StakeHolderMaster.TaxNo}")
	 private String StakeHolderMasterTaxNo;
		
	 @Value("${StakeHolderMaster.SaleType}")
	 private String StakeHolderMasterSaleType;
		
	 @Value("${StakeHolderMaster.PaymentCondition}")
	 private String StakeHolderMasterPaymentCondition;
		
	 @Value("${StakeHolderMaster.PaymentMethod}")
	 private String StakeHolderMasterPaymentMethod;
		
	 @Value("${StakeHolderMaster.RefBank}")
	 private String StakeHolderMasterRefBank;
		
	 @Value("${StakeHolderMaster.RefBankBranch}")
	 private String StakeHolderMasterRefBankBranch;
		
	 @Value("${StakeHolderMaster.RefAccountNo}")
	 private String StakeHolderMasterRefAccountNo;
		
	 @Value("${StakeHolderMaster.AccountName}")
	 private String StakeHolderMasterAccountName;
		
	 @Value("${StakeHolderMaster.ProjectName}")
	 private String StakeHolderMasterProjectName;
		
	 @Value("${StakeHolderMaster.CreditTerm}")
	 private String StakeHolderMasterCreditTerm;
		
	 @Value("${StakeHolderMaster.Type}")
	 private String StakeHolderMasterType;	 
	 
	 @Value("${InsuranceMotorProfile.ActNo}")
	 private String InsuranceMotorProfileActNo;
	 
	 @Value("${InsuranceMotorProfile.ActExpiredate}")
	 private String InsuranceMotorProfileActExpiredate;
	 
	 @Value("${InsuranceMotorProfile.InsuranceMotorMotor}")
	 private String InsuranceMotorProfileInsuranceMotor;
	 
	 @Value("${InsuranceMotorProfile.ActLimit}")
	 private String InsuranceMotorProfileActLimit;
	 
	 @Value("${InsuranceCoverProductProfile.Limit}")
	 private String InsuranceCoverProductProfileLimit;
	 
	 @Value("${InsuranceCoverProductProfile.Date}")
	 private String InsuranceCoverProductProfileDate;
	 
	 @Value("${InsuranceCoverProductProfile.CoverProduct}")
	 private String InsuranceCoverProductProfileCoverProduct;
	 
	 @Value("${InsuranceCoverProductProfile.No}")
	 private String InsuranceCoverProductProfileNo;
	 
	 @Value("${TruckProfile.CarId}")
	 private String TruckProfileCarId;
	 
	 @Value("${TruckProfile.Province}")
	 private String TruckProfileProvince;
	 
	 @Value("${TruckProfile.Yard}")
	 private String TruckProfileYard;
	 
	 @Value("${TruckProfile.Company}")
	 private String TruckProfileCompany;
	 
	 @Value("${TruckProfile.PlateType}")
	 private String TruckProfilePlateType;
	 
	 @Value("${TruckProfile.TruckType}")
	 private String TruckProfileTruckType;
	 
	 @Value("${TruckProfile.Brand}")
	 private String TruckProfileBrand;
	 
	 @Value("${TruckProfile.FuelType}")
	 private String TruckProfileFuelType;
	 
	 @Value("${TruckProfile.ContainerBorderSize}")
	 private String TruckProfileContainerBorderSize;
	 
	 @Value("${TruckProfile.ContainerSize}")
	 private String TruckProfileContainerSize;
	 
	 @Value("${TruckProfile.AvaerageFuel}")
	 private String TruckProfileAvaerageFuel;
	 
	 @Value("${TruckProfile.Project}")
	 private String TruckProfileProject;
	 
	 @Value("${TruckProfile.LatestMilage}")
	 private String TruckProfileLatestMilage;
	 
	 @Value("${TruckProfile.Status}")
	 private String TruckProfileStatus;
	 
	 @Value("${TruckProfile.FirstRegistDate}")
	 private String TruckProfileFirstRegistDate;
	 
	 @Value("${TruckProfile.TruckAge}")
	 private String TruckProfileTruckAge;
	 
	 @Value("${TruckProfile.ExpireDate}")
	 private String TruckProfileExpireDate;
	 
	 @Value("${TruckProfile.Model}")
	 private String TruckProfileModel;
	 
	 @Value("${TruckProfile.ChassisNo}")
	 private String TruckProfileChassisNo;
	 
	 @Value("${TruckProfile.EngineNo}")
	 private String TruckProfileEngineNo;
	 
	 @Value("${TruckProfile.Position1}")
	 private String TruckProfilePosition1;
	 
	 @Value("${TruckProfile.Position2}")
	 private String TruckProfilePosition2;
	 
	 @Value("${TruckProfile.PumpNo}")
	 private String TruckProfilePumpNo;
	 
	 @Value("${TruckProfile.Hps}")
	 private String TruckProfileHps;
	 
	 @Value("${TruckProfile.Weight}")
	 private String TruckProfileWeight;
	 
	 @Value("${TruckProfile.WeightCarry}")
	 private String TruckProfileWeightCarry;
	 
	 @Value("${TruckProfile.WeightTotal}")
	 private String TruckProfileWeightTotal;
	 
	 @Value("${TruckProfile.ActNo}")
	 private String TruckProfileActNo;
	 
	 @Value("${TruckProfile.PolicyNo}")
	 private String TruckProfilePolicyNo;
	 
	 @Value("${TruckProfile.FleetCardNo}")
	 private String TruckProfileFleetCardNo;
	 
	 @Value("${TruckProfile.GpsTruck}")
	 private String TruckProfileGpsTruck;
	 
	 @Value("${TruckProfile.SupplierCode}")
	 private String TruckProfileSupplierCode;
	 
	 @Value("${TruckProfile.TruckAgeYear}")
	 private String TruckProfileTruckAgeYear;
	 
	 @Value("${TruckProfile.TruckAgeMonth}")
	 private String TruckProfileTruckAgeMonth;
	 
	 @Value("${TruckProfile.InstalltionDate}")
	 private String TruckProfileInstalltionDate;
	 
	 @Value("${TruckProfile.RenewDate}")
	 private String TruckProfileRenewDate;
	 
	 @Value("${GPSProfile.GPS}")
	 private String GPSProfileGPS;
	
	public boolean checkAuthorization(HttpSession session) {
		if (session.getAttribute("S_FordUser") == null) {
			return false;
		} else {
			return true;
		}
	}
	@RequestMapping(value = { "/trucklist" }, method = RequestMethod.GET)
	public String TruckList(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		List<Truck> trucks = tkervice.findAllTruckNumber();
		model.addAttribute("trucks", trucks);

		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);

		return "truck-list";
	}
	
	
	@RequestMapping(value = { "/addtruck" }, method = RequestMethod.GET)
	public String adduser(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
//	    	Map<String, String> truckstatus = new HashMap<>();
//	    	truckstatus.put("1", "อนุญาติให้ใช้งานปกติ");
//	    	truckstatus.put("2", "ไม่อนุญาติให้ใช้งานปกติ");
//		    model.addAttribute("truckstatus", truckstatus);
		    
		    
		List<City> ListCity = cityService.findAllCity();
		model.addAttribute("ListCitys", ListCity);

		List<Yard> FindAllYard =  ydservice.findAllYard(); 
		model.addAttribute("FindAllYards", FindAllYard);
	
		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);
		
		List<StakeHolderMaster> ListStakeHolder = spervice.findAllStakeHolder();
		model.addAttribute("ListStakeHolder", ListStakeHolder);

		List<Truck> trucks = tkervice.findAllTruckNumber();
		model.addAttribute("trucks", trucks);
		
		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);
		
		return "addtruck";
	}

	@RequestMapping(value = { "/addtruck" }, method = RequestMethod.POST)
	public String addtruck(HttpSession session, @RequestParam String TruckNumber,@RequestParam String Province,
			@RequestParam String Company,@RequestParam String Brand,@RequestParam String HeadOrTail,@RequestParam int Yard,@RequestParam int TruckType,@RequestParam int GPSTRUCK,
			@RequestParam String FuelType,@RequestParam String ContainerBorderSize,@RequestParam String ContainerSize,@RequestParam String AverageFuel,@RequestParam String Project,
			@RequestParam String LastMileage,@RequestParam int TruckStatus,@RequestParam String FirstRegisterDate,@RequestParam String ExpireDate,
			@RequestParam String TruckAgeYear,@RequestParam String TruckAgeMonth,@RequestParam String Model,@RequestParam String ChassisNo,
			@RequestParam String EngineNo,@RequestParam String Position1,@RequestParam String Position2,@RequestParam String piston,
			@RequestParam int HPS,@RequestParam String Weight,@RequestParam String CarryWeight,@RequestParam String TotalWeight,@RequestParam String ACTCARNUM,
			@RequestParam String ACTCAREXPIRE,@RequestParam String InsuranceMotorNo,@RequestParam String InsuranceMotorPrice,@RequestParam String InsuranceMotorExpireDate,@RequestParam int InsuranceMotorAgent,
			@RequestParam String InsuranceMotorCompany,@RequestParam String InsuranceCargoCompany,@RequestParam int InsuranceCargoAgent,@RequestParam String InsuranceCargoNo,@RequestParam String InsuranceCargoPrice,@RequestParam String InsuranceCargoExpireDate,
			@RequestParam String FleetCardBrand,@RequestParam String FleetCardNo,@RequestParam String FleetCardCreditLimit,@RequestParam String FleetCardReceiveDate,@RequestParam String FleetCardExpireDate,
			ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		List<Truck> trucks = tkervice.findAllTruckNumber();
		for(Truck findtrucks : trucks){
			
			if(findtrucks.getTRUCK_NUMBER().equalsIgnoreCase(TruckNumber)) {
				model.addAttribute("Error", "Duplicate name : " + TruckNumber + " Please Try Again.");
				
				List<Roles> ListRoles = rolesService.findAllRoles();
				model.addAttribute("ListRolest", ListRoles);
				
				List<StakeHolderMaster> ListStakeHolder = spervice.findAllStakeHolder();
				model.addAttribute("ListStakeHolder", ListStakeHolder);

				List<Truck> truck = tkervice.findAllTruckNumber();
				model.addAttribute("trucks", truck);
				
				List<TruckType> ListTypes = ttervice.findAllTruckType();
				model.addAttribute("ListType", ListTypes);
				
				List<GpsType> TruckGps = tgservice.findAllGps();
				model.addAttribute("TruckGps", TruckGps);

				
				return "addtruck";
		
			}
			
		}	
				
		  Truck  Newuser= new Truck();
		  
		  DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
			String strDateNow = DateTime.now().toString(dtf);
			
		  Newuser.setTRUCK_NUMBER(TruckNumber); 
		  Newuser.setPROVINCE(Province);
		  Newuser.setGPS_TRUCK(GPSTRUCK);
		  Newuser.setYARD(Yard);
		  Newuser.setCOMPANY(Company);
		  Newuser.setPLATE_TYPE(HeadOrTail);		  
		  Newuser.setTRUCK_TYPE(TruckType);
		  Newuser.setBRAND(Brand);
		  Newuser.setFUEL_TYPE(FuelType);
		  Newuser.setCONTAINER_BORDER_SIZE(ContainerBorderSize);
		  Newuser.setCONTAINER_SIZE(ContainerSize);
		  Newuser.setAVAERAGE_FUEL(AverageFuel);
		  Newuser.setPROJECT(Integer.valueOf(Project));
		  Newuser.setLATEST_MILAGE(LastMileage);
		  Newuser.setSTATUS(TruckStatus);	
		  Newuser.setFIRST_REGIST_DATE(FirstRegisterDate);
		  Newuser.setEXPIRE_DATE(ExpireDate);
		  Newuser.setTRUCK_AGE_YEAR(TruckAgeYear);
		  Newuser.setTRUCK_AGE_MONTH(TruckAgeMonth);
		  Newuser.setMODEL(Model);
		  Newuser.setCHASSIS_NO(ChassisNo);
		  Newuser.setENGINE_NO(EngineNo);
		  Newuser.setPOSITION1(Position1);
		  Newuser.setPOSITION2(Position2);
		  Newuser.setPUMP_NO(piston);
		  Newuser.setHPS(HPS);
		  Newuser.setWEIGHT(Weight);
		  Newuser.setWEIGHT_CARRY(CarryWeight);
		  Newuser.setWEIGHT_TOTAL(TotalWeight);
		  Newuser.setACT_CAR_NUM(ACTCARNUM);
		  Newuser.setACT_CAR_EXPIRE(ACTCAREXPIRE);
		  
		  
		  /*---------------------------------------------------------------------------------------------------------------------------*/

				InsuranceMotor NewInsuranceMotor = new InsuranceMotor();
				
				NewInsuranceMotor.setInsuranceMotor_Motor(InsuranceMotorCompany);
				NewInsuranceMotor.setInsuranceMotor_Act_No(InsuranceMotorNo);
				NewInsuranceMotor.setInsuranceMotor_Act_Limit(InsuranceMotorPrice);
				NewInsuranceMotor.setInsuranceMotor_Act_Response_By(InsuranceMotorAgent);
				NewInsuranceMotor.setInsuranceMotor_Act_Expire_date(InsuranceMotorExpireDate);
				NewInsuranceMotor.setInsuranceMotor_Act_Status(1);
				imservice.saveInsuranceMotor(NewInsuranceMotor);
					
				Newuser.setACT_NO(NewInsuranceMotor.getInsuranceMotor_id()	);

			
				InsuranceCoverProduct NewInsuranceCoverProduct = new InsuranceCoverProduct();
							
				NewInsuranceCoverProduct.setInsuranceCover_Cover_Product(InsuranceCargoCompany);
				NewInsuranceCoverProduct.setInsuranceCover_No(InsuranceCargoNo);
				NewInsuranceCoverProduct.setInsuranceCover_Limit(InsuranceCargoPrice);
				NewInsuranceCoverProduct.setInsuranceCover_Response_By(InsuranceCargoAgent);
				NewInsuranceCoverProduct.setInsuranceCover_date(InsuranceCargoExpireDate);
				NewInsuranceCoverProduct.setInsuranceCover_Status(1);
				icpservice.saveInsuranceCoverProduct(NewInsuranceCoverProduct);
		
				Newuser.setPOLICY_NO(NewInsuranceCoverProduct.getInsuranceCover_Id());
				
			
			
				FleetCard NewFleetCard = new FleetCard();
							
				NewFleetCard.setFLEETCARD_BRAND(FleetCardBrand);
				NewFleetCard.setFLEETCARD_NO(FleetCardNo);
				
//				LocalDateTime EXPIRYDATE = LocalDateTime.parse(FleetCardExpireDate, dtf);
//				LocalDateTime RECIEVEDATE = LocalDateTime.parse(FleetCardReceiveDate, dtf);	
				NewFleetCard.setFLEET_EXPIRY_DATE(FleetCardExpireDate);			
				NewFleetCard.setFLEETCARD_RECIEVE_DATE(FleetCardReceiveDate);
				
				NewFleetCard.setFLEETCARD_CREDIT_LIMIT(FleetCardCreditLimit);			
				NewFleetCard.setFLEET_CARD_STATUS("1");
				System.out.println(NewFleetCard.getFLEET_CARD_STATUS());
				fcervice.saveFleetCard(NewFleetCard);
		
				FleetCard FindByFleetCardUpdate = fcervice.findByFleetCardNo(FleetCardNo);
				Newuser.setFLEETCARD_NO(FindByFleetCardUpdate.getFLEETCARD_ID());
				
				StakeHolderMaster FindForUpdate = spervice.findByStakeHolder(Company);
				System.out.println(Company);
				Newuser.setSUPPLIER_CODE(FindForUpdate.getSTAKE_HOLDER_UN_ID());
			/*----------------------------------------------------------------------------------------------------------------------------*/

		  Newuser.setUPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
		  Newuser.setUPDATE_DATE(LocalDateTime.now());		  
		  if(Newuser.getCREATE_BY() == null || Newuser.getCREATE_DATE() == null) {
			  Newuser.setCREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
			  Newuser.setCREATE_DATE(LocalDateTime.now());  
	        }

		  tkervice.saveTrucknumber(Newuser);
		  
		  List<Truck> truck = tkervice.findAllTruckNumber();
			model.addAttribute("trucks", truck);

			List<TruckType> ListTypes = ttervice.findAllTruckType();
			model.addAttribute("ListType", ListTypes);
			
			List<GpsType> TruckGps = tgservice.findAllGps();
			model.addAttribute("TruckGps", TruckGps);

		  return "truck-list";
	}
	
	@RequestMapping(value = { "/TruckName/{TRUCK_NUMBER}"}, method = RequestMethod.GET)
	public String TruckStatus(@PathVariable("TRUCK_NUMBER") String TruckName,ModelMap model) {
		
		Truck Trucktatus = tkervice.findByTrucknumber(TruckName);

		if (Trucktatus.getSTATUS()== 1) {
			Trucktatus.setSTATUS(0);
			tkervice.updateTrucknumber(Trucktatus);
		} else {
			Trucktatus.setSTATUS(1);
			tkervice.updateTrucknumber(Trucktatus);
		}

		model.addAttribute("trucks", Trucktatus);
		model.addAttribute("edit", true);
		
		List<Truck> truck = tkervice.findAllTruckNumber();
		model.addAttribute("trucks", truck);

		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);
		
		return "redirect:/trucklist";
	}
	
	
	
	@RequestMapping(value = { "/edit-truck/{TRUCK_NUMBER}" }, method = RequestMethod.GET)
	public String edittruck(HttpSession session,@PathVariable("TRUCK_NUMBER") String TruckName, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		List<TruckEdit> TruckTruckEdit= tkervice.getTruckEdit(TruckName);
		for (TruckEdit obj : TruckTruckEdit) {
			model.addAttribute("TruckTruckEdits", obj);
		}	
		
		Map<String, String> subsidairyData = new HashMap<>();
		subsidairyData.put("0", "Not Found");
		subsidairyData.put("1", "รถบริษัท");
		subsidairyData.put("2", "รถร่วม");
	    
		model.addAttribute("subsidairyData", subsidairyData);
		
		/*
		Map<String, String> truckstatus = new HashMap<>();
	    truckstatus.put("1", "อนุญาติให้ใช้งานปกติ");
	    truckstatus.put("2", "ไม่อนุญาติให้ใช้งาน");
	    */
		
		
		List<TruckStatusList> allTruckStatusList = new ArrayList<TruckStatusList>();
		TruckStatusList truckStatusData = new TruckStatusList();
		truckStatusData.setTruckStatusNo("1");
		truckStatusData.setTruckStatusName("อนุญาติให้ใช้งานปกติ");
		allTruckStatusList.add(truckStatusData);
		
		truckStatusData = new TruckStatusList();
		truckStatusData.setTruckStatusNo("2");
		truckStatusData.setTruckStatusName("ไม่อนุญาติให้ใช้งาน");
		allTruckStatusList.add(truckStatusData);
		
		
		model.addAttribute("truckstatus", allTruckStatusList);
		    
		    
		List<City> ListCity = cityService.findAllCity();
		model.addAttribute("ListCitys", ListCity);

		List<Yard> FindAllYard =  ydservice.findAllYard(); 
		model.addAttribute("FindAllYards", FindAllYard);
		
		List<Truck> ListTruck = tkervice.findAllTruckNumber();
		model.addAttribute("ListTruck", ListTruck);
	
		List<Roles> ListRoles = rolesService.findAllRoles();
		model.addAttribute("ListRolest", ListRoles);
		
		List<StakeHolderMaster> ListStakeHolder = spervice.findAllStakeHolder();
		model.addAttribute("ListStakeHolder", ListStakeHolder);

		List<TruckType> ListTypes = ttervice.findAllTruckType();
		model.addAttribute("ListType", ListTypes);
		
		List<GpsType> TruckGps = tgservice.findAllGps();
		model.addAttribute("TruckGps", TruckGps);
		
		List<ProjectName> FindProject = pnservice.findAllProject();
		model.addAttribute("FindProject", FindProject);
		
		
		return "edit-truck";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-truck/{TRUCK_NUMBER}" }, method = RequestMethod.POST)
	public String edittruckOnSave(HttpSession session, @Valid TruckEdit TruckTruckEdits,
			BindingResult result, @RequestParam String lstcity, @RequestParam int GPSTRUCK, @RequestParam int Yard,
			@RequestParam String Company, @RequestParam String HeadOrTail,
			@RequestParam int TruckType,
			@RequestParam("lstProject") String lstProject, 
			//@RequestParam("lstTruckStatus") String lstTruckStatus,
			@RequestParam("lstActCarCom") String lstActCarCom,
			@RequestParam("lstStakeHolder") String lstStakeHolder, @RequestParam int ListStakeHolderInsuranceMotorAgent,
			@RequestParam String ListStakeHolderInsuranceCargoCompany,
			@RequestParam int ListStakeHolderInsuranceCargoAgent, ModelMap model) {
		if (!checkAuthorization(session))return "redirect:/login";
		
		log.info("Executing update tb_truck_profile.");
		//log.info("Show Choose STATUS : "  + lstTruckStatus.trim());
		log.info("Get request[POST] TRUCK_NUMBER : "  + TruckTruckEdits.getTRUCK_NUMBER());
		//log.info("Get request[POST] TRUCK_STATUS : "  + TruckTruckEdits.getTRUCK_STATUS());
		log.info("Get request[POST] SUBSIDAIRY : "  + TruckTruckEdits.getSUBSIDAIRY());
		
	

		Truck FindTruckForUpdate = tkervice.findByTrucknumber(TruckTruckEdits.getTRUCK_NUMBER());
		FindTruckForUpdate.setPROVINCE(lstcity);
		FindTruckForUpdate.setYARD(Yard);
		FindTruckForUpdate.setCOMPANY(Company);
		FindTruckForUpdate.setPLATE_TYPE(HeadOrTail);		
		FindTruckForUpdate.setBRAND(TruckTruckEdits.getBRAND());
		FindTruckForUpdate.setFUEL_TYPE(TruckTruckEdits.getFUEL_TYPE());
		FindTruckForUpdate.setCONTAINER_BORDER_SIZE(TruckTruckEdits.getCONTAINER_BORDER_SIZE());
		FindTruckForUpdate.setCONTAINER_SIZE(TruckTruckEdits.getCONTAINER_SIZE());
		FindTruckForUpdate.setAVAERAGE_FUEL(TruckTruckEdits.getAVAERAGE_FUEL());
		FindTruckForUpdate.setPROJECT(Integer.valueOf(lstProject.trim()));
		FindTruckForUpdate.setACT_CAR_COMPANY(Integer.valueOf(lstActCarCom.trim()));
		FindTruckForUpdate.setLATEST_MILAGE(TruckTruckEdits.getLATEST_MILAGE());
		//FindTruckForUpdate.setSTATUS(Integer.parseInt(lstTruckStatus.trim()));
		FindTruckForUpdate.setSTATUS(Integer.parseInt(TruckTruckEdits.getTRUCK_STATUS()));
		FindTruckForUpdate.setFIRST_REGIST_DATE(TruckTruckEdits.getFIRST_REGIST_DATE());
		FindTruckForUpdate.setTRUCK_AGE(TruckTruckEdits.getTRUCK_AGE());
		
		FindTruckForUpdate.setACT_CAR_NUM(TruckTruckEdits.getACT_CAR_NUM());
		FindTruckForUpdate.setACT_CAR_EXPIRE(TruckTruckEdits.getACT_CAR_EXPIRE());
				
		FindTruckForUpdate.setEXPIRE_DATE(TruckTruckEdits.getEXPIRE_DATE());
		FindTruckForUpdate.setMODEL(TruckTruckEdits.getMODEL());
		FindTruckForUpdate.setCHASSIS_NO(TruckTruckEdits.getCHASSIS_NO());
		FindTruckForUpdate.setENGINE_NO(TruckTruckEdits.getENGINE_NO());
		FindTruckForUpdate.setPOSITION1(TruckTruckEdits.getPOSITION1());
		FindTruckForUpdate.setPOSITION2(TruckTruckEdits.getPOSITION2());
		FindTruckForUpdate.setPUMP_NO(TruckTruckEdits.getPUMP_NO());
		FindTruckForUpdate.setHPS(TruckTruckEdits.getHPS());
		FindTruckForUpdate.setWEIGHT(TruckTruckEdits.getWEIGHT());
		FindTruckForUpdate.setWEIGHT_CARRY(TruckTruckEdits.getWEIGHT_CARRY());
		FindTruckForUpdate.setWEIGHT_TOTAL(TruckTruckEdits.getWEIGHT_TOTAL());
		FindTruckForUpdate.setTRUCK_TYPE(TruckType);
		FindTruckForUpdate.setGPS_TRUCK(GPSTRUCK);			
		FindTruckForUpdate.setTRUCK_AGE("0");
		FindTruckForUpdate.setSUBSIDAIRY(TruckTruckEdits.getSUBSIDAIRY().trim());
	
		
		InsuranceMotor FindForUpdateInsuranceMotorAct = imservice.findByInsuranceMotorId(FindTruckForUpdate.getACT_NO());
		if(FindForUpdateInsuranceMotorAct == null)
		{
			InsuranceMotor NewInsuranceMotor = new InsuranceMotor();
			
			NewInsuranceMotor.setInsuranceMotor_Motor(lstStakeHolder);
			NewInsuranceMotor.setInsuranceMotor_Act_No(TruckTruckEdits.getACT_NO());
			NewInsuranceMotor.setInsuranceMotor_Act_Limit(TruckTruckEdits.getACT_INSURANCE_LIMIT());
			NewInsuranceMotor.setInsuranceMotor_Act_Response_By(ListStakeHolderInsuranceMotorAgent);
			NewInsuranceMotor.setInsuranceMotor_Act_Expire_date(TruckTruckEdits.getACT_EXPIRE_DATE());
			NewInsuranceMotor.setInsuranceMotor_Act_Status(1);
			imservice.saveInsuranceMotor(NewInsuranceMotor);
				
			FindTruckForUpdate.setACT_NO(NewInsuranceMotor.getInsuranceMotor_id()	);
			
		}else {
			InsuranceMotor FindForUpdateInsuranceMotorAct3 = imservice.findByInsuranceMotorId(FindTruckForUpdate.getACT_NO());
			System.out.println(FindForUpdateInsuranceMotorAct3);
			
			FindForUpdateInsuranceMotorAct3.setInsuranceMotor_Motor(lstStakeHolder);
			FindForUpdateInsuranceMotorAct3.setInsuranceMotor_Act_No(TruckTruckEdits.getACT_NO());
			FindForUpdateInsuranceMotorAct3.setInsuranceMotor_Act_Limit(TruckTruckEdits.getACT_INSURANCE_LIMIT());
			FindForUpdateInsuranceMotorAct3.setInsuranceMotor_Act_Response_By(ListStakeHolderInsuranceMotorAgent);
			FindForUpdateInsuranceMotorAct3.setInsuranceMotor_Act_Expire_date(TruckTruckEdits.getACT_EXPIRE_DATE());
			FindForUpdateInsuranceMotorAct3.setInsuranceMotor_Act_Status(1);
			imservice.updateInsuranceMotor(FindForUpdateInsuranceMotorAct3);
			
		}		
	
		
		
		InsuranceCoverProduct FindByInsuranceCover = icpservice.findByInsuranceCoverProductId(FindTruckForUpdate.getPOLICY_NO());
		if(FindByInsuranceCover == null)
		{
			InsuranceCoverProduct NewInsuranceCoverProduct = new InsuranceCoverProduct();
						
			NewInsuranceCoverProduct.setInsuranceCover_Cover_Product(ListStakeHolderInsuranceCargoCompany);
			NewInsuranceCoverProduct.setInsuranceCover_No(TruckTruckEdits.getPOLICY_NO());
			NewInsuranceCoverProduct.setInsuranceCover_Limit(TruckTruckEdits.getINSURANCE_LIMIT());
			NewInsuranceCoverProduct.setInsuranceCover_Response_By(ListStakeHolderInsuranceCargoAgent);
			NewInsuranceCoverProduct.setInsuranceCover_date(TruckTruckEdits.getPOLICY_EXPIRY_DATE());
			NewInsuranceCoverProduct.setInsuranceCover_Status(1);
			icpservice.saveInsuranceCoverProduct(NewInsuranceCoverProduct);
	
			FindTruckForUpdate.setPOLICY_NO(NewInsuranceCoverProduct.getInsuranceCover_Id());
			
		}else {
			InsuranceCoverProduct FindByInsuranceCover3 = icpservice.findByInsuranceCoverProductId(FindTruckForUpdate.getPOLICY_NO());	
			FindByInsuranceCover3.setInsuranceCover_Cover_Product(ListStakeHolderInsuranceCargoCompany);
			FindByInsuranceCover3.setInsuranceCover_No(TruckTruckEdits.getPOLICY_NO());
			FindByInsuranceCover3.setInsuranceCover_Limit(TruckTruckEdits.getINSURANCE_LIMIT());
			FindByInsuranceCover3.setInsuranceCover_Response_By(ListStakeHolderInsuranceCargoAgent);
			FindByInsuranceCover3.setInsuranceCover_date(TruckTruckEdits.getPOLICY_EXPIRY_DATE());
			FindByInsuranceCover3.setInsuranceCover_Status(1);
			icpservice.updateInsuranceCoverProduct(FindByInsuranceCover3);
			
		}		
		
		
		FleetCard FindByFleetCard = fcervice.findByFleetCardId(FindTruckForUpdate.getFLEETCARD_NO());
		if(FindByFleetCard == null ||FindByFleetCard.getFLEETCARD_ID() == 1)
		{
			FleetCard NewFleetCard = new FleetCard();
			
			NewFleetCard.setFLEETCARD_NO(TruckTruckEdits.getFLEETCARD_NO());
			NewFleetCard.setFLEETCARD_BRAND(TruckTruckEdits.getFLEETCARD_BRAND());
			NewFleetCard.setFLEETCARD_CREDIT_LIMIT(TruckTruckEdits.getFLEETCARD_CREDIT_LIMIT());
			NewFleetCard.setFLEET_EXPIRY_DATE(TruckTruckEdits.getFLEET_EXPIRY_DATE());
			NewFleetCard.setFLEETCARD_RECIEVE_DATE(TruckTruckEdits.getFLEETCARD_RECIEVE_DATE());
			NewFleetCard.setFLEET_CARD_STATUS("1");
			fcervice.saveFleetCard(NewFleetCard);
			
			FindTruckForUpdate.setFLEETCARD_NO(NewFleetCard.getFLEETCARD_ID());
				
		}else {
		
			FleetCard FindByFleetCardUpdate = fcervice.findByFleetCardId(FindTruckForUpdate.getFLEETCARD_NO());	
			FindByFleetCardUpdate.setFLEETCARD_BRAND(TruckTruckEdits.getFLEETCARD_BRAND());
			FindByFleetCardUpdate.setFLEETCARD_NO(TruckTruckEdits.getFLEETCARD_NO());
			FindByFleetCardUpdate.setFLEETCARD_CREDIT_LIMIT(TruckTruckEdits.getFLEETCARD_CREDIT_LIMIT());
			FindByFleetCardUpdate.setFLEET_EXPIRY_DATE(TruckTruckEdits.getFLEET_EXPIRY_DATE());
			FindByFleetCardUpdate.setFLEETCARD_RECIEVE_DATE(TruckTruckEdits.getFLEETCARD_RECIEVE_DATE());
			FindByFleetCardUpdate.setFLEET_CARD_STATUS("1");
			
			System.out.println("Test Update FindByFleetCardUpdate"+ FindByFleetCardUpdate );
			fcervice.updateFleetCard(FindByFleetCardUpdate);
			
		}	
		

		FindTruckForUpdate.setSUPPLIER_CODE(TruckTruckEdits.getSUPPLIER_SUM());	
		/* รอสร้างservice*/
		

		FindTruckForUpdate.setTRUCK_AGE_YEAR(TruckTruckEdits.getTRUCK_AGE_YEAR());
		FindTruckForUpdate.setTRUCK_AGE_MONTH(TruckTruckEdits.getTRUCK_AGE_MONTH());
	
		FindTruckForUpdate.setINSTALLATION_DATE(TruckTruckEdits.getINSTALLATION_DATE());
		FindTruckForUpdate.setRENEW_DYATE(TruckTruckEdits.getRENEW_DATE());

		FindTruckForUpdate.setUPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
		FindTruckForUpdate.setUPDATE_DATE(LocalDateTime.now());		  
	  if(FindTruckForUpdate.getCREATE_BY() == null || FindTruckForUpdate.getCREATE_DATE() == null) {
		  FindTruckForUpdate.setCREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
		  FindTruckForUpdate.setCREATE_DATE(LocalDateTime.now());
		  
	  }
	  
	  tkervice.updateTrucknumber(FindTruckForUpdate);
	
		return "redirect:/trucklist";
	}
	
	
	
	@SuppressWarnings({ "null" })
	@RequestMapping(value = { "/UpdateTruckExcel" }, method = RequestMethod.POST)
	public String UpdateTruckExcel(HttpSession session, HttpServletRequest requestServer,@RequestBody String getSelectItemData, ModelMap model)
			throws InterruptedException, IOException, JSONException, ParseException,JsonProcessingException {
		
		String json = getSelectItemData;	
		System.out.println(json);
		String replaceString1 = json.replace("[[","[");
		String replaceString2 = replaceString1.replace("]]","]");
		System.out.println("+++++++++++++++++++++++++++++"+replaceString2+"+++++++++++++++++++++++++++++");
		
		JSONObject obj = new JSONObject(replaceString2);
		JSONArray arr = obj.getJSONArray("theGroupData");		

		for (int i = 0; i < arr.length(); i++) {
						
			Truck NewTruck = new Truck();
			
			NewTruck.setTRUCK_NUMBER(arr.getJSONObject(i).getString("CARID"));
			NewTruck.setPROVINCE(arr.getJSONObject(i).getString("CARPROVINCE"));
			
//			Yard FindYard = ydservice.findByNameYard(arr.getJSONObject(i).getString("SUBSIDAIRY"));		
//			NewTruck.setYARD(FindYard.getYard_id());
			NewTruck.setYARD(0);
			NewTruck.setCOMPANY("0");
//			NewTruck.setCOMPANY(arr.getJSONObject(i).getString("CAROWNERNAME"));
			NewTruck.setPLATE_TYPE(arr.getJSONObject(i).getString("HEAD_OR_TAIL"));
			
			
			TruckType FindTruckType = ttervice.findByTruckType(arr.getJSONObject(i).getString("CARTYPEDESC"));
			if(FindTruckType == null) {
				TruckType NewTruckType = new TruckType();
				NewTruckType.setTRUCKTYPE_TYPE(arr.getJSONObject(i).getString("CARTYPEDESC"));
				NewTruckType.setTRUCKTYPE_CREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
				NewTruckType.setTRUCKTYPE_CREATE_DATE(LocalDateTime.now());
				NewTruckType.setTRUCKTYPE_UPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
				NewTruckType.setTRUCKTYPE_UPDATE_DATE(LocalDateTime.now());
				NewTruckType.setTRUCKTYPE_STATUS("1");				
				ttervice.saveTruckType(NewTruckType);
				TruckType FindTruckType2 = ttervice.findByTruckType(arr.getJSONObject(i).getString("CARTYPEDESC"));
				NewTruck.setTRUCK_TYPE(FindTruckType2.getTRUCKTYPE_id());
			}else{
				
				NewTruck.setTRUCK_TYPE(FindTruckType.getTRUCKTYPE_id());
			}
			
			NewTruck.setBRAND(arr.getJSONObject(i).getString("CARBRANDDESC"));	
			NewTruck.setFUEL_TYPE(arr.getJSONObject(i).getString("CARENGINEDESC"));
			NewTruck.setCONTAINER_BORDER_SIZE(arr.getJSONObject(i).getString("CARBOXNET"));
			NewTruck.setCONTAINER_SIZE(arr.getJSONObject(i).getString("CARBOXGROSS"));
			
			if (arr.getJSONObject(i).getString("CARSTATUS").equals("อนุญาตให้ใช้งานปกติ")) {
				NewTruck.setSTATUS(1);
			}else if(arr.getJSONObject(i).getString("CARSTATUS").equals("ไม่อนุญาตให้ใช้งานปกติ")) {
				NewTruck.setSTATUS(0);
			}	
			if(arr.getJSONObject(i).getString("CARLIMITENGINE") != "0" || arr.getJSONObject(i).getString("CARLIMITENGINE") != null)
			{			NewTruck.setAVAERAGE_FUEL(arr.getJSONObject(i).getString("CARLIMITENGINE"));
			
			}else if(arr.getJSONObject(i).getString("CARLIMITENGINE") == "0"|| arr.getJSONObject(i).getString("CARLIMITENGINE") == null) {
				NewTruck.setAVAERAGE_FUEL("0");
			}
			NewTruck.setPROJECT(Integer.valueOf(arr.getJSONObject(i).getString("CARLINEDESC")));
			NewTruck.setLATEST_MILAGE(arr.getJSONObject(i).getString("CAR_MILE"));	
			
			NewTruck.setTRUCK_AGE("0");
			
//			String[] SplitTruckAge = arr.getJSONObject(i).getString(TruckProfileTruckAge).split(".");
//			String SplitTruckAgeYear = SplitTruckAge[0];
//			String SplitTruckAgeMonth = SplitTruckAge[1];			
//			NewTruck.setTRUCK_AGE_MONTH(Integer.parseInt(SplitTruckAgeYear));
//			NewTruck.setTRUCK_AGE_YEAR(Integer.parseInt(SplitTruckAgeMonth));
			
			NewTruck.setTRUCK_AGE_MONTH("0");
			NewTruck.setTRUCK_AGE_YEAR("0");
			
			NewTruck.setACT_CAR_NUM(arr.getJSONObject(i).getString("ACT_NO"));
			NewTruck.setACT_CAR_EXPIRE(arr.getJSONObject(i).getString("ACT_EXPIRE"));	
			NewTruck.setACT_CAR_COMPANY(Integer.valueOf(arr.getJSONObject(i).getString("ACT_COMPANY")));	
//			NewTruck.setACT_CAR_COMPANY("0");
			NewTruck.setMODEL(arr.getJSONObject(i).getString("CARMODEL"));
			NewTruck.setCHASSIS_NO(arr.getJSONObject(i).getString("CARNUMBER"));
			NewTruck.setENGINE_NO(arr.getJSONObject(i).getString("MACHINENUMBER"));
			NewTruck.setPOSITION1(arr.getJSONObject(i).getString("POSITION1"));			
			NewTruck.setPOSITION2(arr.getJSONObject(i).getString("POSITION2"));	
			NewTruck.setPUMP_NO(arr.getJSONObject(i).getString("CC"));
			NewTruck.setHPS(Integer.parseInt(arr.getJSONObject(i).getString("CARPOWER")));
			NewTruck.setWEIGHT(arr.getJSONObject(i).getString("CARWEIGHT"));
			NewTruck.setWEIGHT_CARRY(arr.getJSONObject(i).getString("CARRY_WEIGHT"));
			NewTruck.setWEIGHT_TOTAL(arr.getJSONObject(i).getString("TOTAL_WEIGHT"));	
			
			StakeHolderMaster FindSP1 = spervice.findByStakeHolder(arr.getJSONObject(i).getString("CAROWNERNAME"));
			
			if(FindSP1 == null) {
				model.addAttribute("Error", "Supplier Not Found  :");
				
			}else if(FindSP1 != null){			
				
				  NewTruck.setSUPPLIER_CODE(FindSP1.getSTAKE_HOLDER_UN_ID());
				  
			}		
			
//			InsuranceMotor FindInsuranceMotor = imservice.findByInsuranceMotorName(arr.getJSONObject(i).getString("INS_NO"));
//			if(FindInsuranceMotor != null &&FindInsuranceMotor.getInsuranceMotor_Motor().equals(arr.getJSONObject(i).getString("INS_COMPANY")) 
//					&& FindInsuranceMotor.getInsuranceMotor_Act_No().equals(arr.getJSONObject(i).getString("INS_NO"))
//						&& FindInsuranceMotor.getInsuranceMotor_Act_Limit().equals(arr.getJSONObject(i).getString("INS_PRICE"))) {
//				
//				InsuranceMotor FindByInsuranceMotorAct2 = imservice.findByInsuranceMotorName(arr.getJSONObject(i).getString("INS_NO"));
//				NewTruck.setACT_NO(FindByInsuranceMotorAct2.getInsuranceMotor_id());
//				
//			}else{
//				StakeHolderMaster FindForUpdateInsuranceMotorAct = spervice.findByStakeHolder(arr.getJSONObject(i).getString("INS_COMPANY"));		
//				if(FindForUpdateInsuranceMotorAct != null) {
					
				InsuranceMotor NewInsuranceMotor = new InsuranceMotor();
				NewInsuranceMotor.setInsuranceMotor_Motor(arr.getJSONObject(i).getString("INS_COMPANY"));
				NewInsuranceMotor.setInsuranceMotor_Act_Expire_date(arr.getJSONObject(i).getString("INS_EXPIRE"));
				NewInsuranceMotor.setInsuranceMotor_Act_Status(1);
				NewInsuranceMotor.setInsuranceMotor_Act_No(arr.getJSONObject(i).getString("INS_NO"));
				NewInsuranceMotor.setInsuranceMotor_Act_Limit(arr.getJSONObject(i).getString("INS_PRICE"));
				
//				NewInsuranceMotor.setInsuranceMotor_Act_Response_By(FindForUpdateInsuranceMotorAct.getSTAKE_HOLDER_UN_ID());
				NewInsuranceMotor.setInsuranceMotor_Act_Response_By(0);	
				imservice.saveInsuranceMotor(NewInsuranceMotor);
//					InsuranceMotor FindByInsuranceMotorAct= imservice.findByInsuranceMotorNo(arr.getJSONObject(i).getString("INS_NO"));
					NewTruck.setACT_NO(NewInsuranceMotor.getInsuranceMotor_id());
					
//				}else{
//					
//					model.addAttribute("Error", "Supplier Not Found  :"+arr.getJSONObject(i).getString("INS_COMPANY"));					
//
//				}	
//			}
			
//			InsuranceCoverProduct FindInsuranceCoverProduct = icpservice.findByInsuranceCoverProduct(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
//			if(FindInsuranceCoverProduct != null && FindInsuranceCoverProduct.getInsuranceCover_Cover_Product().equals(arr.getJSONObject(i).getString("INS_CARGO_COMPANY")) 
//					&& FindInsuranceCoverProduct.getInsuranceCover_No().equals(arr.getJSONObject(i).getString("INS_CARGO_NO"))
//						&& FindInsuranceCoverProduct.getInsuranceCover_Limit().equals(arr.getJSONObject(i).getString("INS_CARGO_PRICE"))) {
//											
//				InsuranceCoverProduct FindByInsuranceCover = icpservice.findByInsuranceCoverProduct(arr.getJSONObject(i).getString("INS_CARGO_NO"));
//				NewTruck.setPOLICY_NO(FindByInsuranceCover.getInsuranceCover_Id());
//			}else{
//				StakeHolderMaster FindForUpdateInsuranceCover = spervice.findByStakeHolder(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));	
//				if(FindForUpdateInsuranceCover != null) {
				
				InsuranceCoverProduct NewInsuranceCoverProduct = new InsuranceCoverProduct();
				NewInsuranceCoverProduct.setInsuranceCover_Cover_Product(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
				NewInsuranceCoverProduct.setInsuranceCover_date(arr.getJSONObject(i).getString("INS_CARGO_EXPIRE"));
				NewInsuranceCoverProduct.setInsuranceCover_Status(1);
				NewInsuranceCoverProduct.setInsuranceCover_No(arr.getJSONObject(i).getString("INS_CARGO_NO"));
				NewInsuranceCoverProduct.setInsuranceCover_Limit(arr.getJSONObject(i).getString("INS_CARGO_PRICE"));
				
//				NewInsuranceCoverProduct.setInsuranceCover_Response_By(FindForUpdateInsuranceCover.getSTAKE_HOLDER_UN_ID());
				NewInsuranceCoverProduct.setInsuranceCover_Response_By(0);
				icpservice.saveInsuranceCoverProduct(NewInsuranceCoverProduct);	
				
//				InsuranceCoverProduct FindByInsuranceCover2 = icpservice.findByInsuranceCoverProduct(arr.getJSONObject(i).getString("INS_CARGO_NO"));
				NewTruck.setPOLICY_NO(NewInsuranceCoverProduct.getInsuranceCover_Id());
				
//				}else {
//					model.addAttribute("Error", "Supplier Not Found  :"+arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
//				}
//				
//			}
			
///* รอ  Data Master Data */ NewTruck.setPOLICY_NO(1);
/* รอ Data Master Data */ NewTruck.setFLEETCARD_NO(1);
			
			GpsType FindGps = tgservice.findByGpsType(arr.getJSONObject(i).getString("GPS"));
			if(FindGps != null && FindGps.getGPSTYPE().equals(arr.getJSONObject(i).getString("GPS"))) {
		
				GpsType FindGps2 = tgservice.findByGpsType(arr.getJSONObject(i).getString("GPS"));
				NewTruck.setGPS_TRUCK(FindGps2.getGPSId());
				
			}else {
				
				GpsType NewGpsType = new GpsType();
				NewGpsType.setGPSTYPE(arr.getJSONObject(i).getString("GPS"));
				NewGpsType.setGPS_STATUS("1");
				NewGpsType.setGPS_CREATE_DATE(LocalDateTime.now());
				NewGpsType.setGPS_CREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
				NewGpsType.setGPS_UPDATE_DATE(LocalDateTime.now());
				NewGpsType.setGPS_UPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
				tgservice.saveGpsType(NewGpsType);
				
				GpsType FindGps2 = tgservice.findByGpsType(arr.getJSONObject(i).getString("GPS"));
				NewTruck.setGPS_TRUCK(FindGps2.getGPSId());
			}
			
			
			
			  NewTruck.setFIRST_REGIST_DATE(arr.getJSONObject(i).getString("REGISTERDATE"));
			  NewTruck.setEXPIRE_DATE(arr.getJSONObject(i).getString("CARTAXDATE_END"));
//			  NewTruck.setINSTALLATION_DATE(arr.getJSONObject(i).getString("Installation_Date"));
//			  NewTruck.setRENEW_DATE(arr.getJSONObject(i).getString("Renew_Date"));
			  NewTruck.setUPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
			  NewTruck.setUPDATE_DATE(LocalDateTime.now());		  
		  if(NewTruck.getCREATE_BY() == null || NewTruck.getCREATE_DATE() == null) {
			  NewTruck.setCREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
			  NewTruck.setCREATE_DATE(LocalDateTime.now());
			  
			  
			  /* wait Data Update Use For Test Only*/
			  
				
				/* wait Data Update Use For Test Only*/
				NewTruck.setSUPPLIER_CODE(0);
//				NewTruck.setACT_NO(1);
//				NewTruck.setPOLICY_NO(1);

			  
			  tkervice.saveTrucknumber(NewTruck);
			  
	        }
			
		}
	   
			return "truck-list";
		
		
	}
	
	
	

}
