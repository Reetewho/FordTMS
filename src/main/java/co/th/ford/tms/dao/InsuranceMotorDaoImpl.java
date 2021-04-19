package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.InsuranceMotor;

@Repository("InsuranceMotorDao")
public class InsuranceMotorDaoImpl extends AbstractDao<Integer, InsuranceMotor> implements InsuranceMotorDao {
	
	@SuppressWarnings("unchecked")
	public List<InsuranceMotor> findAllInsuranceMotor() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("InsuranceMotor_Act_Status", "1"));
		return (List<InsuranceMotor>) criteria.list();
	}
	
	public InsuranceMotor findByInsuranceMotorName(String InsuranceMotor) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("InsuranceMotor_Motor", InsuranceMotor));
		return (InsuranceMotor) criteria.uniqueResult();
	}
	
	public InsuranceMotor findByInsuranceMotorNo(String InsuranceMotor) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("InsuranceMotor_Act_No", InsuranceMotor));
		return (InsuranceMotor) criteria.uniqueResult();
	}
	
	
	@Override
	public void saveInsuranceMotor(InsuranceMotor InsuranceMotor) {
		persist(InsuranceMotor);
	}

	
	public InsuranceMotor findByInsuranceMotorId(int InsuranceMotor) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("id", InsuranceMotor));
		return (InsuranceMotor) criteria.uniqueResult();
	}
}
