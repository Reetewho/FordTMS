package co.th.ford.tms.model;


import lombok.Data;

@Data
public class TruckEdit {
	
	/* Truck*/
	private String TRUCK_NUMBER;
	private String PROVINCE;
	private int YARDS;
	private String COMPANY;	
	private String PLATE_TYPE;	
	private int TRUCK_TYPE; 
	private String BRAND;	
	private String FUEL_TYPE;	
	private String CONTAINER_BORDER_SIZE;		
	private String CONTAINER_SIZE;	
	private String AVAERAGE_FUEL;	
	private int PROJECT;
	private String LATEST_MILAGE;	
	private String TRUCK_STATUS;
	private String FIRST_REGIST_DATE;	
	private String TRUCK_AGE;
	private String EXPIRE_DATE;
	private String MODEL;
	private String CHASSIS_NO;
	private String ENGINE_NO;
	private String POSITION1;
	private String POSITION2;
	private String PUMP_NO;
	private int HPS;
	private String WEIGHT;
	private String WEIGHT_CARRY;
	private String WEIGHT_TOTAL;
	private int ACT_NUM;
	private int POLICY_NUM;
	private int FLEETCARD_NUM;
	private int GPS_TRUCK;
	private int SUPPLIER_SUM;
	private String TRUCK_AGE_YEAR;
	private String TRUCK_AGE_MONTH;
	private String INSTALLATION_DATE;
	private String RENEW_DATE;
	private String etaDate;
	
	/* InsuranceMotor */
	private String ACT_ID;	
	private String ACT_INSURANCE_LIMIT;
	private String ACT_EXPIRE_DATE;
	private String ACT_INSURANCE_MOTOR;
	private String ACT_RESPONSE_BY;
	private String ACT_STATUS;
	private String ACT_NO;
	
	
	/* InsuranceCoverProduct */
	private String POLICY_ID;	
	private String INSURANCE_LIMIT;
	private String POLICY_EXPIRY_DATE;
	private String INSURANCE_COVER_PRODUCT;
	private String INSURANCE_RESPONSE_BY;
	private String POLICY_NO;
	private String INSURANCE_STATUS;
	
	/* StakeHolder */
	private String STAKE_HOLDER_UN_ID;	
	private String STAKE_HOLDER_ID;
	private String STAKE_HOLDER_NAME;
	private String STAKE_HOLDER_ADDRESS;
	private String STAKE_HOLDER_SUBDISTRICT;
	private String STAKE_HOLDER_DISTRICT;
	private String STAKE_HOLDER_PROVINCE;
	private String STAKE_HOLDER_POST_CODE;
	private String STAKE_HOLDER_TEL_NO;
	private String STAKE_HOLDER_FAX_NO;
	private String STAKE_HOLDER_MOBILE_NO;
	private String STAKE_HOLDER_EMAIL;
	private String STAKE_HOLDER_CONTACT_NAME;
	private String STAKE_HOLDER_TAX_NO;
	private String STAKE_HOLDER_SALE_TYPE;
	private String STAKE_HOLDER_PAYMENT_CONDITION;
	private String STAKE_HOLDER_PAYMENT_METHOD;
	private String STAKE_HOLDER_REF_BANK;
	private String STAKE_HOLDER_REF_BANK_BRANCH;
	private String STAKE_HOLDER_REF_ACCOUNT_NO;
	private String STAKE_HOLDER_REF_ACCOUNT_NAME;
	private String STAKE_HOLDER_PROJECT;
	private String STAKE_HOLDER_CREDIT_TERM;
	private String STAKE_HOLDER_TYPE;
	private String STAKE_HOLDER_STATUS;

	
	
	/* TruckType */
	private String TYPE_ID;	
	private String TYPE_TYPE;
	private String TYPE_STATUS;
	
	
	/* JOIN TABLE */
	private String STAKE_HOLDER_NAME_IM;
	private String STAKE_HOLDER_NAME_IMC;
	
	/* Fleet Card */
	private int FLEETCARD_ID;	
	private String FLEETCARD_BRAND;
	private String FLEETCARD_NO;
	private String FLEETCARD_CREDIT_LIMIT;	
	private String FLEETCARD_RECIEVE_DATE;
	private String FLEET_EXPIRY_DATE;
	
	/* New Field Truck */
	private String ACT_CAR_NUM;	
	private String ACT_CAR_EXPIRE;
	private int ACT_CAR_COMPANY;
	private String SUBSIDAIRY;
	
}
