package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.InsuranceMotor;
import co.th.ford.tms.model.Truck;

public interface InsuranceMotorService {
	List<InsuranceMotor> findAllInsuranceMotor();

	InsuranceMotor findByInsuranceMotorName(String InsuranceMotor);
	
	InsuranceMotor findByInsuranceMotorNo(String InsuranceMotor);
	
	InsuranceMotor findByInsuranceMotorId(int InsuranceMotor);

	void updateInsuranceMotor(InsuranceMotor InsuranceMotor);
	
	void saveInsuranceMotor(InsuranceMotor InsuranceMotor);
}
