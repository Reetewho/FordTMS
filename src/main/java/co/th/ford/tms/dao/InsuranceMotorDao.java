package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.InsuranceMotor;

public interface InsuranceMotorDao {
	List<InsuranceMotor> findAllInsuranceMotor();
	
	InsuranceMotor findByInsuranceMotorName(String InsuranceMotor);
	
	InsuranceMotor findByInsuranceMotorId(int InsuranceMotor);

	InsuranceMotor findByInsuranceMotorNo(String InsuranceMotor);
	
	void saveInsuranceMotor(InsuranceMotor InsuranceMotor);
}
