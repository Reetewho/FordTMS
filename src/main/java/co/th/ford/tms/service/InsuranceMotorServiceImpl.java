	package co.th.ford.tms.service;

	import java.util.List;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	import org.springframework.transaction.annotation.Transactional;

	import co.th.ford.tms.dao.InsuranceMotorDao;
	import co.th.ford.tms.model.InsuranceMotor;
import co.th.ford.tms.model.Truck;

	@Service("InsuranceMotorService")
	@Transactional
	public class InsuranceMotorServiceImpl implements InsuranceMotorService {
		@Autowired
		private InsuranceMotorDao dao;
		
		
		public List<InsuranceMotor> findAllInsuranceMotor() {
			return dao.findAllInsuranceMotor();
		}
		
		public InsuranceMotor findByInsuranceMotorName(String InsuranceMotor) {
			return dao.findByInsuranceMotorName(InsuranceMotor);
		}
		
		public InsuranceMotor findByInsuranceMotorNo(String InsuranceMotor) {
			return dao.findByInsuranceMotorNo(InsuranceMotor);
		}
		
		public InsuranceMotor findByInsuranceMotorId(int InsuranceMotor) {
			return dao.findByInsuranceMotorId(InsuranceMotor);
		}
		
		public void saveInsuranceMotor(InsuranceMotor InsuranceMotor) {
			dao.saveInsuranceMotor(InsuranceMotor);
		}
		
		public void updateInsuranceMotor(InsuranceMotor InsuranceMotor) {
			InsuranceMotor entity = dao.findByInsuranceMotorId(InsuranceMotor.getInsuranceMotor_id());
			if(entity!=null){
				entity.setInsuranceMotor_Motor(InsuranceMotor.getInsuranceMotor_Motor());
				entity.setInsuranceMotor_Act_No(InsuranceMotor.getInsuranceMotor_Act_No());
				entity.setInsuranceMotor_Act_Limit(InsuranceMotor.getInsuranceMotor_Act_Limit());
				entity.setInsuranceMotor_Act_Expire_date(InsuranceMotor.getInsuranceMotor_Act_Expire_date());
				entity.setInsuranceMotor_Act_Response_By(InsuranceMotor.getInsuranceMotor_Act_Response_By());
				entity.setInsuranceMotor_Act_Status(InsuranceMotor.getInsuranceMotor_Act_Status());
	
				}
			}
		
		
	}


