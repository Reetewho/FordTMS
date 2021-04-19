package co.th.ford.tms.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.th.ford.tms.model.Roles;
import co.th.ford.tms.model.SaleType;
import co.th.ford.tms.model.StakeHolderMaster;
import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckType;
import co.th.ford.tms.model.CreditTerm;
import co.th.ford.tms.model.Department;
import co.th.ford.tms.model.GpsType;
import co.th.ford.tms.model.Gsdb;
import co.th.ford.tms.model.InsuranceCoverProduct;
import co.th.ford.tms.model.InsuranceMotor;
import co.th.ford.tms.model.Load;
import co.th.ford.tms.model.PaymentMethod;
import co.th.ford.tms.model.ProjectName;
import co.th.ford.tms.model.ReportStakeHolder;
import co.th.ford.tms.model.User;
import co.th.ford.tms.model.Yard;
import co.th.ford.tms.service.UserService;
import co.th.ford.tms.service.YardService;
import co.th.ford.tms.service.RolesService;
import co.th.ford.tms.service.SaleTypeService;
import co.th.ford.tms.service.StakeHolderMasterService;
import co.th.ford.tms.service.TruckService;
import co.th.ford.tms.service.TruckTypeService;
import co.th.ford.tms.service.CreditTermService;
import co.th.ford.tms.service.DepartmentService;
import co.th.ford.tms.service.GpsTypeService;
import co.th.ford.tms.service.InsuranceCoverProductService;
import co.th.ford.tms.service.InsuranceMotorService;
import co.th.ford.tms.service.PaymentMethodService;
import co.th.ford.tms.service.ProjectNameService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import co.th.ford.tms.aesencrypt.AESCrypt;
/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Controller
@RequestMapping("/")
public class StakeHolderController {
	
	private static final Logger log = Logger.getLogger(StakeHolderController.class);
	
	org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	UserService uservice;
	
	@Autowired
	StakeHolderMasterService spervice;
	
	@Autowired
	InsuranceMotorService imservice;
	
	@Autowired
	CreditTermService crservice;
	
	@Autowired
	PaymentMethodService pmservice;
	
	@Autowired
	ProjectNameService pnservice;
	
	@Autowired
	InsuranceCoverProductService icpservice;
	
	@Autowired
	GpsTypeService tgservice;
	
	@Autowired
	YardService ydservice;
	
	@Autowired
	SaleTypeService stservice;
	
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
	@RequestMapping(value = { "/stakeholderlist" }, method = RequestMethod.GET)
	public String StakeHolderList(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		//List<StakeHolderMaster> FindAllStakeHolder = spervice.findAllStakeHolder();
		List<ReportStakeHolder> allStakeHolder = spervice.displayAllReportStakeHolder();
		
		model.addAttribute("allStakeHolder", allStakeHolder);
		return "StakeHolder";
	}
	
	
	@RequestMapping(value = { "/addstakeholder" }, method = RequestMethod.GET)
	public String addstakeholder(HttpSession session, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		/*
		 * 
		 * 
		 * List<StakeHolderMaster> FindAllStakeHolder = spervice.findAllStakeHolder();
		 * model.addAttribute("FindAllStakeHolder", FindAllStakeHolder);
		 * List<Truck> trucks = tkervice.findAllTruckNumber();
		 * model.addAttribute("trucks", trucks);
		 * 
		 * List<TruckType> ListTypes = ttervice.findAllTruckType();
		 * model.addAttribute("ListType", ListTypes);
		 * 
		 * List<GpsType> TruckGps = tgservice.findAllGps();
		 * model.addAttribute("TruckGps", TruckGps);
		 * 
		 * 
		 */
		
		return "addstakeholder";
	}

	@RequestMapping(value = { "/addstakeholder" }, method = RequestMethod.POST)
	public String addstakeholder(HttpSession session, @RequestParam String StakeId,@RequestParam String StakeName,@RequestParam String StakeAddress,@RequestParam String StakeDistrict,
			@RequestParam String StakeSubdistrict,@RequestParam String StakeProvince,@RequestParam String StakePostCode,@RequestParam String StakeTel,@RequestParam String StakeFax,
			@RequestParam String StakeMobile,@RequestParam String Stakemail,@RequestParam String StakeContactName,@RequestParam String StakeTaxNo,@RequestParam String StakeSaleType,
			@RequestParam String StakePaymentCondition,@RequestParam String StakePaymentMethod,@RequestParam String StakeRefBank,@RequestParam String StakeRefBankBranch,@RequestParam String StakeAccountNo,
			@RequestParam String StakeAccountName,@RequestParam String StakeProject,@RequestParam String StakeCreditTerm,@RequestParam int StakeType,
			ModelMap model) {
		
		if (!checkAuthorization(session))
			return "redirect:/login";

		List<StakeHolderMaster> FindAllStakeHolder = spervice.findAllStakeHolder();

		for (StakeHolderMaster findStakeHolder : FindAllStakeHolder) {

			if (findStakeHolder.getSTAKE_HOLDER_ID().equalsIgnoreCase(StakeId)) {
				model.addAttribute("Error", "Duplicate name : " + StakeId + " Please Try Again.");

				model.addAttribute("FindAllStakeHolder", FindAllStakeHolder);

				return "redirect:/addstakeholder";

			}

		}

		DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		String strDateNow = DateTime.now().toString(dtf);

		StakeHolderMaster NewStakeHolderMaster = new StakeHolderMaster();

		NewStakeHolderMaster.setSTAKE_HOLDER_ID(StakeId);
		NewStakeHolderMaster.setSTAKE_HOLDER_NAME(StakeName);
		NewStakeHolderMaster.setSTAKE_HOLDER_ADDRESS(StakeAddress);
		NewStakeHolderMaster.setSTAKE_HOLDER_DISTRICT(StakeDistrict);
		NewStakeHolderMaster.setSTAKE_HOLDER_SUBDISTRICT(StakeSubdistrict);
		NewStakeHolderMaster.setSTAKE_HOLDER_PROVINCE(StakeProvince);
		NewStakeHolderMaster.setSTAKE_HOLDER_POST_CODE(StakePostCode);
		NewStakeHolderMaster.setSTAKE_HOLDER_TEL_NO(StakeTel);
		NewStakeHolderMaster.setSTAKE_HOLDER_FAX_NO(StakeFax);
		NewStakeHolderMaster.setSTAKE_HOLDER_MOBILE_NO(StakeMobile);
		NewStakeHolderMaster.setSTAKE_HOLDER_EMAIL(Stakemail);
		NewStakeHolderMaster.setSTAKE_HOLDER_CONTACT_NAME(StakeContactName);
		NewStakeHolderMaster.setSTAKE_HOLDER_TAX_NO(StakeTaxNo);
		NewStakeHolderMaster.setSTAKE_HOLDER_SALE_TYPE(Integer.valueOf(StakeSaleType.trim()));
		NewStakeHolderMaster.setSTAKE_HOLDER_PAYMENT_CONDITION(StakePaymentCondition);
		NewStakeHolderMaster.setSTAKE_HOLDER_PAYMENT_METHOD(Integer.valueOf(StakePaymentMethod.trim()));
		NewStakeHolderMaster.setSTAKE_HOLDER_REF_BANK(StakeRefBank);
		NewStakeHolderMaster.setSTAKE_HOLDER_REF_BANK_BRANCH(StakeRefBankBranch);
		NewStakeHolderMaster.setSTAKE_HOLDER_REF_ACCOUNT_NO(StakeAccountNo);
		NewStakeHolderMaster.setSTAKE_HOLDER_REF_ACCOUNT_NAME(StakeAccountName);
		NewStakeHolderMaster.setSTAKE_HOLDER_PROJECT(Integer.valueOf(StakeProject.trim()));
		NewStakeHolderMaster.setSTAKE_HOLDER_CREDIT_TERM(Integer.valueOf(StakeCreditTerm.trim()));
		NewStakeHolderMaster.setSTAKE_HOLDER_TYPE(StakeType);
		NewStakeHolderMaster.setSTAKE_HOLDER_STATUS(1);
		NewStakeHolderMaster.setSTAKE_HOLDER_CREATE_DATE(LocalDateTime.now());
		NewStakeHolderMaster.setSTAKE_HOLDER_CREATE_BY(((User) session.getAttribute("S_FordUser")).getUsername());
		NewStakeHolderMaster.setSTAKE_HOLDER_UPDATE_DATE(LocalDateTime.now());
		NewStakeHolderMaster.setSTAKE_HOLDER_UPDATE_BY(((User) session.getAttribute("S_FordUser")).getUsername());

		spervice.saveStakeHolder(NewStakeHolderMaster);

		model.addAttribute("Success", "Successfully, add Stake Holder : " + StakeId + ".");

		return "addstakeholder";
	}

	@RequestMapping(value = { "/StakeHolderName/{STAKE_HOLDER_UN_ID}" }, method = RequestMethod.GET)
	public String StakeHolderStatus(@PathVariable("STAKE_HOLDER_UN_ID") String StakeHolder, ModelMap model) {

		// StakeHolderMaster FindByStakeHolder =
		// spervice.findByStakeHolder(StakeHolder);
		StakeHolderMaster FindByStakeHolder = spervice.findByStakeHolderId(Integer.valueOf(StakeHolder.trim()));

		try {
			if (FindByStakeHolder.getSTAKE_HOLDER_CREATE_DATE() == null)
				FindByStakeHolder.setSTAKE_HOLDER_CREATE_DATE(LocalDateTime.now());
			if (FindByStakeHolder.getSTAKE_HOLDER_UPDATE_DATE() == null)
				FindByStakeHolder.setSTAKE_HOLDER_UPDATE_DATE(LocalDateTime.now());

			if (FindByStakeHolder.getSTAKE_HOLDER_STATUS() == 1) {
				FindByStakeHolder.setSTAKE_HOLDER_STATUS(0);
				spervice.updateStakeHolder(FindByStakeHolder);
			} else {
				FindByStakeHolder.setSTAKE_HOLDER_STATUS(1);
				spervice.updateStakeHolder(FindByStakeHolder);
			}
		} catch (Exception e) {
			log.error("Error from STAKE_HOLDER_ID : " + FindByStakeHolder.getSTAKE_HOLDER_ID() + " | Error message : "
					+ e.getMessage());
		}

		log.info("Success, Update Activate Status by STAKE_HOLDER_ID : " + FindByStakeHolder.getSTAKE_HOLDER_ID()
				+ " | current status : " + FindByStakeHolder.getSTAKE_HOLDER_STATUS());

		return "redirect:/stakeholderlist";
	}
	
	
	
	@RequestMapping(value = { "/edit-stake-holder/{stakeHolderUnID}" }, method = RequestMethod.GET)
	public String editStakeHolderActionDisplay(HttpSession session, @PathVariable("stakeHolderUnID") String stakeHolderUnID, ModelMap model) {
		if (!checkAuthorization(session))
			return "redirect:/login";
		
		//StakeHolderMaster FindByStakeHolder = spervice.findByStakeHolder(StakeHolder);
		
		log.info("Action : display editStakeHolder  >>>>>>>>>> STAKE_HOLDER_UN_ID : " + stakeHolderUnID);
		
		StakeHolderMaster FindByStakeHolder = spervice.findByStakeHolderId(Integer.valueOf(stakeHolderUnID.trim()));
		

		model.addAttribute("FindByStakeHolder", FindByStakeHolder);
		model.addAttribute("edit", true);
		
		List<SaleType> FindSaleType = stservice.findAllSaleType();
		model.addAttribute("FindSaleType", FindSaleType);
		
		List<PaymentMethod> FindPaymentMethod = pmservice.findAllPaymentMethod();
		model.addAttribute("FindPaymentMethod", FindPaymentMethod);
		
		List<ProjectName> FindProject = pnservice.findAllProject();
		model.addAttribute("FindProject", FindProject);
		
		List<CreditTerm> FindCreditTerm = crservice.findAllCreditTerm();
		model.addAttribute("FindCreditTerm", FindCreditTerm);
		
		
		Map<String, String> StakeHoldertype = new HashMap<>();
		StakeHoldertype.put("1", "SUPPLIER");
		StakeHoldertype.put("2", "CUSTOMER");
		 
	     model.addAttribute("StakeHoldertype", StakeHoldertype);

	
//		List<Roles> ListRoles = rolesService.findAllRoles();
//		model.addAttribute("ListRolest", ListRoles);
//
//		List<TruckType> ListTypes = ttervice.findAllTruckType();
//		model.addAttribute("ListType", ListTypes);
//		
//		List<GpsType> TruckGps = tgservice.findAllGps();
//		model.addAttribute("TruckGps", TruckGps);
		
		return "edit-stake-holder";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * updating employee in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-stake-holder/{stakeHolderUnID}" }, method = RequestMethod.POST)
	public String editStakeHolderActionSave(HttpSession session,@PathVariable("stakeHolderUnID") String stakeHolderUnID, 
								 @RequestParam String STAKEHOLDERSALETYPE,@RequestParam String STAKEHOLDERPAYMENTMETHOD, 
								 @RequestParam String STAKEHOLDERPROJECT,@RequestParam String STAKEHOLDERCREDITTERM, 
								 @Valid StakeHolderMaster FindByStakeHolder, BindingResult result, ModelMap model) {
		if (!checkAuthorization(session))return "redirect:/login";
		
		log.info("Action : save editStakeHolder >>>>>>>>>> STAKE_HOLDER_UN_ID : " + stakeHolderUnID);
		log.info("Action : save editStakeHolder >>>>>>>>>> FindByStakeHolder.getSTAKE_HOLDER_UN_ID : " + FindByStakeHolder.getSTAKE_HOLDER_UN_ID());
		log.info("Action : save editStakeHolder >>>>>>>>>> FindByStakeHolder.getSTAKE_HOLDER_ID : " + FindByStakeHolder.getSTAKE_HOLDER_ID());
		
		if(FindByStakeHolder.getSTAKE_HOLDER_CREATE_DATE()==null || FindByStakeHolder.getSTAKE_HOLDER_CREATE_BY()==null){
			FindByStakeHolder.setSTAKE_HOLDER_PAYMENT_METHOD(Integer.valueOf(STAKEHOLDERPAYMENTMETHOD.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_PROJECT(Integer.valueOf(STAKEHOLDERPROJECT.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_CREDIT_TERM(Integer.valueOf(STAKEHOLDERCREDITTERM.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_SALE_TYPE(Integer.valueOf(STAKEHOLDERSALETYPE.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_CREATE_DATE(LocalDateTime.now());
			FindByStakeHolder.setSTAKE_HOLDER_CREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
			FindByStakeHolder.setSTAKE_HOLDER_UPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());		
			FindByStakeHolder.setSTAKE_HOLDER_UPDATE_DATE(LocalDateTime.now());
//			FindByStakeHolder.setSTAKE_HOLDER_TYPE(StakeType);
			spervice.updateStakeHolder(FindByStakeHolder);
		}else {
			FindByStakeHolder.setSTAKE_HOLDER_PAYMENT_METHOD(Integer.valueOf(STAKEHOLDERPAYMENTMETHOD.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_PROJECT(Integer.valueOf(STAKEHOLDERPROJECT.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_CREDIT_TERM(Integer.valueOf(STAKEHOLDERCREDITTERM.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_SALE_TYPE(Integer.valueOf(STAKEHOLDERSALETYPE.trim()));
			FindByStakeHolder.setSTAKE_HOLDER_UPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());		
			FindByStakeHolder.setSTAKE_HOLDER_UPDATE_DATE(LocalDateTime.now());
//			FindByStakeHolder.setSTAKE_HOLDER_TYPE(StakeType);
			spervice.updateStakeHolder(FindByStakeHolder);
		}
		return "redirect:/stakeholderlist";
	}
	
	
	
//	@SuppressWarnings({ "unchecked", "null" })
//	@RequestMapping(value = { "/UpdateTruckExcel" }, method = RequestMethod.POST)
//	public String UpdateTruckExcel(HttpSession session, HttpServletRequest requestServer,@RequestBody String getSelectItemData, ModelMap model)
//			throws InterruptedException, IOException, JSONException, ParseException,JsonProcessingException {
//		
//		String json = getSelectItemData;	
//		System.out.println(json);
//		String replaceString1 = json.replace("[[","[");
//		String replaceString2 = replaceString1.replace("]]","]");
//		System.out.println("+++++++++++++++++++++++++++++"+replaceString2+"+++++++++++++++++++++++++++++");
//		
//		JSONObject obj = new JSONObject(replaceString2);
//		JSONArray arr = obj.getJSONArray("theGroupData");		
//
//		for (int i = 0; i < arr.length(); i++) {
//						
//			Truck NewTruck = new Truck();
//			
//			NewTruck.setTRUCK_NUMBER(arr.getJSONObject(i).getString("CARID"));
//			NewTruck.setPROVINCE(arr.getJSONObject(i).getString("CARPROVINCE"));
//			
//			Yard FindYard = ydservice.findByNameYard(arr.getJSONObject(i).getString("SUBSIDAIRY"));		
//			NewTruck.setYARD(FindYard.getYardId());
//			NewTruck.setCOMPANY(arr.getJSONObject(i).getString("CAROWNERNAME"));
//			NewTruck.setPLATE_TYPE(arr.getJSONObject(i).getString("HEAD_OR_TAIL"));
//			
//			
//			TruckType FindTruckType = ttervice.findByTruckType(arr.getJSONObject(i).getString("CARTYPEDESC"));
//			if(FindTruckType == null) {
//				TruckType NewTruckType = new TruckType();
//				NewTruckType.setTRUCKTYPE_TYPE(arr.getJSONObject(i).getString("CARTYPEDESC"));
//				NewTruckType.setTRUCKTYPE_CREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
//				NewTruckType.setTRUCKTYPE_CREATE_DATE(LocalDateTime.now());
//				NewTruckType.setTRUCKTYPE_UPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
//				NewTruckType.setTRUCKTYPE_UPDATE_DATE(LocalDateTime.now());
//				NewTruckType.setTRUCKTYPE_STATUS("1");				
//				ttervice.saveTruckType(NewTruckType);
//				TruckType FindTruckType2 = ttervice.findByTruckType(arr.getJSONObject(i).getString("CARTYPEDESC"));
//				NewTruck.setTRUCK_TYPE(FindTruckType2.getTRUCKTYPE_id());
//			}else{
//				
//				NewTruck.setTRUCK_TYPE(FindTruckType.getTRUCKTYPE_id());
//			}
//			
//			NewTruck.setBRAND(arr.getJSONObject(i).getString("CARBRANDDESC"));	
//			NewTruck.setFUEL_TYPE(arr.getJSONObject(i).getString("CARENGINEDESC"));
//			NewTruck.setCONTAINER_BORDER_SIZE(arr.getJSONObject(i).getString("CARBOXNET"));
//			NewTruck.setCONTAINER_SIZE(arr.getJSONObject(i).getString("CARBOXGROSS"));
//			
//			if (arr.getJSONObject(i).getString("CARSTATUS").equals("อนุญาตให้ใช้งานปกติ")) {
//				NewTruck.setSTATUS(1);
//			}else if(arr.getJSONObject(i).getString("CARSTATUS").equals("ไม่อนุญาตให้ใช้งานปกติ")) {
//				NewTruck.setSTATUS(0);
//			}	
//			if(arr.getJSONObject(i).getString("CARLIMITENGINE") != "0" || arr.getJSONObject(i).getString("CARLIMITENGINE") != null)
//			{			NewTruck.setAVAERAGE_FUEL(arr.getJSONObject(i).getString("CARLIMITENGINE"));
//			
//			}else if(arr.getJSONObject(i).getString("CARLIMITENGINE") == "0"|| arr.getJSONObject(i).getString("CARLIMITENGINE") == null) {
//				NewTruck.setAVAERAGE_FUEL("0");
//			}
//			NewTruck.setPROJECT(arr.getJSONObject(i).getString("CARLINEDESC"));
//			NewTruck.setLATEST_MILAGE(arr.getJSONObject(i).getString("CAR_MILE"));	
//			
//			NewTruck.setTRUCK_AGE(arr.getJSONObject(i).getString(TruckProfileTruckAge));
//			
////			String[] SplitTruckAge = arr.getJSONObject(i).getString(TruckProfileTruckAge).split(".");
////			String SplitTruckAgeYear = SplitTruckAge[0];
////			String SplitTruckAgeMonth = SplitTruckAge[1];			
////			NewTruck.setTRUCK_AGE_MONTH(Integer.parseInt(SplitTruckAgeYear));
////			NewTruck.setTRUCK_AGE_YEAR(Integer.parseInt(SplitTruckAgeMonth));
//			
//			NewTruck.setTRUCK_AGE_MONTH(0);
//			NewTruck.setTRUCK_AGE_YEAR(0);
//			
//			NewTruck.setMODEL(arr.getJSONObject(i).getString("CARMODEL"));
//			NewTruck.setCHASSIS_NO(arr.getJSONObject(i).getString("CARNUMBER"));
//			NewTruck.setENGINE_NO(arr.getJSONObject(i).getString("MACHINENUMBER"));
//			NewTruck.setPOSITION1(arr.getJSONObject(i).getString("POSITION1"));			
//			NewTruck.setPOSITION2(arr.getJSONObject(i).getString("POSITION2"));	
//			NewTruck.setPUMP_NO(arr.getJSONObject(i).getString("CC"));
//			NewTruck.setHPS(Integer.parseInt(arr.getJSONObject(i).getString("CARPOWER")));
//			NewTruck.setWEIGHT(arr.getJSONObject(i).getString("CARWEIGHT"));
//			NewTruck.setWEIGHT_CARRY(arr.getJSONObject(i).getString("CARRY_WEIGHT"));
//			NewTruck.setWEIGHT_TOTAL(arr.getJSONObject(i).getString("TOTAL_WEIGHT"));	
//			
//			StakeHolderMaster FindSP1 = spervice.findByStakeHolder(arr.getJSONObject(i).getString("CAROWNERNAME"));
//			
//			if(FindSP1 == null) {
//				model.addAttribute("Error", "Supplier Not Found  :");
//				
//			}else if(FindSP1 != null){			
//				
//				  NewTruck.setSUPPLIER_CODE(FindSP1.getStakeHolder_un_Id());
//				  
//			}		
//			
//			InsuranceMotor FindInsuranceMotor = imservice.findByInsuranceMotorName(arr.getJSONObject(i).getString("INS_COMPANY"));
//			if(FindInsuranceMotor != null &&FindInsuranceMotor.getInsuranceMotor_Motor().equals(arr.getJSONObject(i).getString("INS_COMPANY")) 
//					&& FindInsuranceMotor.getInsuranceMotor_Act_No().equals(arr.getJSONObject(i).getString("INS_NO"))
//						&& FindInsuranceMotor.getInsuranceMotor_Act_Limit().equals(arr.getJSONObject(i).getString("INS_PRICE"))) {
//				
//				InsuranceMotor FindByInsuranceMotorAct2 = imservice.findByInsuranceMotorName(arr.getJSONObject(i).getString("INS_COMPANY"));
//				NewTruck.setACT_NO(FindByInsuranceMotorAct2.getInsuranceMotor_id());
//				
//			}else{
//				StakeHolderMaster FindForUpdateInsuranceMotorAct = spervice.findByStakeHolder(arr.getJSONObject(i).getString("INS_COMPANY"));		
//				if(FindForUpdateInsuranceMotorAct != null) {
//					
//				InsuranceMotor NewInsuranceMotor = new InsuranceMotor();
//				NewInsuranceMotor.setInsuranceMotor_Motor(arr.getJSONObject(i).getString("INS_COMPANY"));
//				NewInsuranceMotor.setInsuranceMotor_Act_Expire_date(arr.getJSONObject(i).getString("INS_EXPIRE"));
//				NewInsuranceMotor.setInsuranceMotor_Act_Status(1);
//				NewInsuranceMotor.setInsuranceMotor_Act_No(arr.getJSONObject(i).getString("INS_NO"));
//				NewInsuranceMotor.setInsuranceMotor_Act_Limit(arr.getJSONObject(i).getString("INS_PRICE"));
//
//				NewInsuranceMotor.setInsuranceMotor_Act_Response_By(FindForUpdateInsuranceMotorAct.getStakeHolder_un_Id());	
//				imservice.saveInsuranceMotor(NewInsuranceMotor);
//					InsuranceMotor FindByInsuranceMotorAct= imservice.findByInsuranceMotorName(arr.getJSONObject(i).getString("INS_COMPANY"));
//					NewTruck.setACT_NO(FindByInsuranceMotorAct.getInsuranceMotor_id());
//					
//				}else{
//					
//					model.addAttribute("Error", "Supplier Not Found  :"+arr.getJSONObject(i).getString("INS_COMPANY"));					
//
//				}	
//			}
//			
//			InsuranceCoverProduct FindInsuranceCoverProduct = icpservice.findByInsuranceCoverProduct(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
//			if(FindInsuranceCoverProduct != null && FindInsuranceCoverProduct.getInsuranceCover_Cover_Product().equals(arr.getJSONObject(i).getString("INS_CARGO_COMPANY")) 
//					&& FindInsuranceCoverProduct.getInsuranceCover_No().equals(arr.getJSONObject(i).getString("INS_CARGO_NO"))
//						&& FindInsuranceCoverProduct.getInsuranceCover_Limit().equals(arr.getJSONObject(i).getString("INS_CARGO_PRICE"))) {
//											
//				InsuranceCoverProduct FindByInsuranceCover = icpservice.findByInsuranceCoverProduct(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
//				NewTruck.setPOLICY_NO(FindByInsuranceCover.getInsuranceCover_Id());
//			}else{
//				StakeHolderMaster FindForUpdateInsuranceCover = spervice.findByStakeHolder(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));	
//				if(FindForUpdateInsuranceCover != null) {
//				
//				InsuranceCoverProduct NewInsuranceCoverProduct = new InsuranceCoverProduct();
//				NewInsuranceCoverProduct.setInsuranceCover_Cover_Product(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
//				NewInsuranceCoverProduct.setInsuranceCover_date(arr.getJSONObject(i).getString("INS_CARGO_EXPIRE"));
//				NewInsuranceCoverProduct.setInsuranceCover_Status(1);
//				NewInsuranceCoverProduct.setInsuranceCover_No(arr.getJSONObject(i).getString("INS_CARGO_NO"));
//				NewInsuranceCoverProduct.setInsuranceCover_Limit(arr.getJSONObject(i).getString("INS_CARGO_PRICE"));
//
//				NewInsuranceCoverProduct.setInsuranceCover_Response_By(FindForUpdateInsuranceCover.getStakeHolder_un_Id());	
//				icpservice.saveInsuranceCoverProduct(NewInsuranceCoverProduct);	
//				
//				InsuranceCoverProduct FindByInsuranceCover2 = icpservice.findByInsuranceCoverProduct(arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
//				NewTruck.setPOLICY_NO(FindByInsuranceCover2.getInsuranceCover_Id());
//				
//				}else {
//					model.addAttribute("Error", "Supplier Not Found  :"+arr.getJSONObject(i).getString("INS_CARGO_COMPANY"));
//				}
//				
//			}
//			
/////* รอ  Data Master Data */ NewTruck.setPOLICY_NO(1);
///* รอ Data Master Data */ NewTruck.setFLEETCARD_NO(1);
//			
//			GpsType FindGps = tgservice.findByGpsType(arr.getJSONObject(i).getString("GPS"));
//			if(FindGps != null && FindGps.getGPSTYPE().equals(arr.getJSONObject(i).getString("GPS"))) {
//		
//				GpsType FindGps2 = tgservice.findByGpsType(arr.getJSONObject(i).getString("GPS"));
//				NewTruck.setGPS_TRUCK(FindGps2.getGPSId());
//				
//			}else {
//				
//				GpsType NewGpsType = new GpsType();
//				NewGpsType.setGPSTYPE(arr.getJSONObject(i).getString("GPS"));
//				NewGpsType.setGPS_STATUS("1");
//				NewGpsType.setGPS_CREATE_DATE(LocalDateTime.now());
//				NewGpsType.setGPS_CREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
//				NewGpsType.setGPS_UPDATE_DATE(LocalDateTime.now());
//				NewGpsType.setGPS_UPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
//				tgservice.saveGpsType(NewGpsType);
//				
//				GpsType FindGps2 = tgservice.findByGpsType(arr.getJSONObject(i).getString("GPS"));
//				NewTruck.setGPS_TRUCK(FindGps2.getGPSId());
//			}
//			
//			
//			
//			  NewTruck.setFIRST_REGIST_DATE(arr.getJSONObject(i).getString("REGISTERDATE"));
//			  NewTruck.setEXPIRE_DATE(arr.getJSONObject(i).getString(TruckProfileExpireDate));
////			  NewTruck.setINSTALLATION_DATE(arr.getJSONObject(i).getString("Installation_Date"));
////			  NewTruck.setRENEW_DATE(arr.getJSONObject(i).getString("Renew_Date"));
//			  NewTruck.setUPDATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
//			  NewTruck.setUPDATE_DATE(LocalDateTime.now());		  
//		  if(NewTruck.getCREATE_BY() == null || NewTruck.getCREATE_DATE() == null) {
//			  NewTruck.setCREATE_BY(((User)session.getAttribute("S_FordUser")).getUsername());
//			  NewTruck.setCREATE_DATE(LocalDateTime.now());
//			  
//			  
//			  /* wait Data Update Use For Test Only*/
//			  
//				
//				/* wait Data Update Use For Test Only*/
//				NewTruck.setSUPPLIER_CODE(1);
//				NewTruck.setACT_NO(1);
//				NewTruck.setPOLICY_NO(1);
//
//			  
//			  tkervice.saveTrucknumber(NewTruck);
//			  
//	        }
//			
//		}
//	   
//			return "truck-list";
//		
//		
//	}
	
	
	

}
