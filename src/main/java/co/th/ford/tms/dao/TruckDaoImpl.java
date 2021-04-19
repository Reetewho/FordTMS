package co.th.ford.tms.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.Report1;
import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckEdit;

@Repository("truckDao")
public class TruckDaoImpl extends AbstractDao<Integer, Truck> implements TruckDao {

	/*public User findByUsername(String user) {
		return getByKey(user);
	}*/
	
	@Override
	public void saveTrucknumber(Truck truck) {
		persist(truck);
	}

	public void deleteByTrucknumber(String trucknumber) {
		Query query = getSession().createSQLQuery("delete from tb_trucknumber where trucknumber = :TRUCK_NUMBER");
		query.setString("TRUCK_NUMBER", trucknumber);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Truck> findAllTruckNumber() {
		Criteria criteria = createEntityCriteria();
		return (List<Truck>) criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Truck> findBytype(int trucktype) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("TRUCK_TYPE", trucktype));
		return (List<Truck>) criteria.list();
	}

	public Truck findByTrucknumber(String trucknumber) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("TRUCK_NUMBER", trucknumber));
		return (Truck) criteria.uniqueResult();
	}
	
	 @SuppressWarnings("unchecked")
		public List<TruckEdit> getTruckEdit(String TruckEdit){
			Query query = getSession().createSQLQuery(
					" select t.TRUCK_NUMBER, t.PROVINCE, t.YARDS, t.COMPANY, t.PLATE_TYPE, t.TRUCK_TYPE, t.BRAND, t.FUEL_TYPE, t.CONTAINER_BORDER_SIZE, t.CONTAINER_SIZE, t.AVAERAGE_FUEL, "
					+ " t.PROJECT, t.LATEST_MILAGE, t.TRUCK_STATUS, t.FIRST_REGIST_DATE, t.TRUCK_AGE, t.EXPIRE_DATE, t.MODEL, t.CHASSIS_NO, t.ENGINE_NO, t.POSITION1, "
					+" t.POSITION2, t.PUMP_NO, t.HPS, t.WEIGHT, t.WEIGHT_CARRY, t.WEIGHT_TOTAL, t.ACT_NUM, t.POLICY_NUM, t.FLEETCARD_NUM, t.GPS_TRUCK, "
					+" t.SUPPLIER_SUM, t.TRUCK_AGE_YEAR, t.TRUCK_AGE_MONTH, t.INSTALLATION_DATE, t.RENEW_DATE, tt.TYPE_ID, tt.TYPE_TYPE, tt.TYPE_STATUS, im.ACT_ID, im.ACT_NO, "
					+" im.ACT_EXPIRE_DATE, im.ACT_INSURANCE_MOTOR, im.ACT_INSURANCE_LIMIT, im.ACT_RESPONSE_BY, im.ACT_STATUS, imc.POLICY_ID, imc.INSURANCE_LIMIT, imc.POLICY_EXPIRY_DATE, imc.INSURANCE_COVER_PRODUCT, imc.INSURANCE_RESPONSE_BY, "
					+" imc.POLICY_NO, imc.INSURANCE_STATUS, sh.STAKE_HOLDER_UN_ID, sh.STAKE_HOLDER_ID, sh.STAKE_HOLDER_NAME, sh.STAKE_HOLDER_ADDRESS, sh.STAKE_HOLDER_SUBDISTRICT, sh.STAKE_HOLDER_DISTRICT, sh.STAKE_HOLDER_PROVINCE, sh.STAKE_HOLDER_POST_CODE, "
					+" sh.STAKE_HOLDER_TEL_NO, sh.STAKE_HOLDER_FAX_NO, sh.STAKE_HOLDER_MOBILE_NO, sh.STAKE_HOLDER_EMAIL, sh.STAKE_HOLDER_CONTACT_NAME, sh.STAKE_HOLDER_TAX_NO, "
					+" sh.STAKE_HOLDER_SALE_TYPE, sh.STAKE_HOLDER_PAYMENT_CONDITION, sh.STAKE_HOLDER_PAYMENT_METHOD, sh.STAKE_HOLDER_REF_BANK, "
					+" sh.STAKE_HOLDER_REF_BANK_BRANCH, sh.STAKE_HOLDER_REF_ACCOUNT_NO, sh.STAKE_HOLDER_REF_ACCOUNT_NAME, sh.STAKE_HOLDER_PROJECT, sh.STAKE_HOLDER_CREDIT_TERM, sh.STAKE_HOLDER_STATUS, "
					+" shm.STAKE_HOLDER_NAME as STAKE_HOLDER_NAME_IM, shm.STAKE_HOLDER_NAME as STAKE_HOLDER_NAME_IMC, "
					+" fc.FLEETCARD_ID,fc.FLEETCARD_BRAND,FLEETCARD_NO,FLEETCARD_CREDIT_LIMIT,FLEETCARD_RECIEVE_DATE,FLEET_EXPIRY_DATE,t.ACT_CAR_NUM,ACT_CAR_EXPIRE,ACT_CAR_COMPANY,SUBSIDAIRY"
					+" from tb_truck_profile as t join tb_truck_type as tt on t.TRUCK_TYPE = tt.TYPE_ID "
					+" left join tb_insurance_motor as im on t.ACT_NUM = im.ACT_ID "
					+" left join tb_fleet_card as fc on t.FLEETCARD_NUM = fc.FLEETCARD_ID "
					+" left join tb_stake_holder_master as shm on im.ACT_RESPONSE_BY = shm.STAKE_HOLDER_UN_ID "
					+" left join tb_insurance_cover_product as imc on t.POLICY_NUM = imc.POLICY_ID "
					+" left join tb_stake_holder_master as shmc on imc.INSURANCE_RESPONSE_BY = shmc.STAKE_HOLDER_UN_ID "
					+" left join tb_stake_holder_master as sh on t.SUPPLIER_SUM = sh.STAKE_HOLDER_UN_ID "
					+" where t.TRUCK_NUMBER = :TruckEdit order by  t.TRUCK_NUMBER "	 );		
						
			query.setString("TruckEdit", TruckEdit);
			List<TruckEdit> allList = new ArrayList<TruckEdit>();
			List<Object[]> list=query.list();
			if(list!=null && list.size() >0)
				for (Object[] obj : list) {
					
//					for(int i =0 ;i < obj.length ; i++) {
//						System.out.println(i + ", " + obj[i]);
//					}
					
					TruckEdit TruckEdit1 = new TruckEdit();
					TruckEdit1.setTRUCK_NUMBER(obj[0]==null?"":obj[0].toString());
					TruckEdit1.setPROVINCE(obj[1]==null?"":obj[1].toString());
					TruckEdit1.setYARDS(obj[2]==null?2:((Integer)obj[2]).intValue());
					TruckEdit1.setCOMPANY(obj[3]==null?"":obj[3].toString());
					TruckEdit1.setPLATE_TYPE(obj[4]==null?"":obj[4].toString());
					TruckEdit1.setTRUCK_TYPE(obj[5]==null?5:((Integer)obj[5]).intValue());
					TruckEdit1.setBRAND(obj[6]==null?"":obj[6].toString());
					TruckEdit1.setFUEL_TYPE(obj[7]==null?"":obj[7].toString());
					TruckEdit1.setCONTAINER_BORDER_SIZE(obj[8]==null?"":obj[8].toString());
					TruckEdit1.setCONTAINER_SIZE(obj[9]==null?"":obj[9].toString());
					TruckEdit1.setAVAERAGE_FUEL(obj[10]==null?"":obj[10].toString());
					TruckEdit1.setPROJECT(obj[11]==null?0:Integer.valueOf(obj[11].toString()));
					TruckEdit1.setLATEST_MILAGE(obj[12]==null?"":obj[12].toString());
//					TruckEdit1.setTRUCK_STATUS(obj[13]==null?13:((Integer)obj[13]).intValue());
					TruckEdit1.setTRUCK_STATUS(obj[13]==null?"":obj[13].toString());
					TruckEdit1.setFIRST_REGIST_DATE(obj[14]==null?"":obj[14].toString());
					TruckEdit1.setTRUCK_AGE(obj[15]==null?"":obj[15].toString());
					TruckEdit1.setEXPIRE_DATE(obj[16]==null?"":obj[16].toString());
					TruckEdit1.setMODEL(obj[17]==null?"":obj[17].toString());
					TruckEdit1.setCHASSIS_NO(obj[18]==null?"":obj[18].toString());
					TruckEdit1.setENGINE_NO(obj[19]==null?"":obj[19].toString());
					TruckEdit1.setPOSITION1(obj[20]==null?"":obj[20].toString());
					TruckEdit1.setPOSITION2(obj[21]==null?"":obj[21].toString());
					TruckEdit1.setPUMP_NO(obj[22]==null?"":obj[22].toString());
					TruckEdit1.setHPS(obj[23]==null?23:((Integer)obj[23]).intValue());
					TruckEdit1.setWEIGHT(obj[24]==null?"":obj[24].toString());
					TruckEdit1.setWEIGHT_CARRY(obj[25]==null?"":obj[25].toString());
					TruckEdit1.setWEIGHT_TOTAL(obj[26]==null?"":obj[26].toString());
					TruckEdit1.setACT_NUM(obj[27]==null?27:((Integer)obj[27]).intValue());
					TruckEdit1.setPOLICY_NUM(obj[28]==null?28:((Integer)obj[28]).intValue());
					TruckEdit1.setFLEETCARD_NUM(obj[29]==null?30:((Integer)obj[29]).intValue());					
					TruckEdit1.setGPS_TRUCK(obj[30]==null?32:((Integer)obj[30]).intValue());
					TruckEdit1.setSUPPLIER_SUM(obj[31]==null?32:((Integer)obj[31]).intValue());					
					TruckEdit1.setTRUCK_AGE_YEAR(obj[32]==null?"":obj[32].toString());					
					TruckEdit1.setTRUCK_AGE_MONTH(obj[33]==null?"":obj[33].toString());
					TruckEdit1.setINSTALLATION_DATE(obj[34]==null?"":obj[34].toString());
					TruckEdit1.setRENEW_DATE(obj[35]==null?"":obj[35].toString());
					
					TruckEdit1.setTYPE_ID(obj[36]==null?"":obj[36].toString());
					TruckEdit1.setTYPE_TYPE(obj[37]==null?"":obj[37].toString());
					TruckEdit1.setTYPE_STATUS(obj[38]==null?"":obj[38].toString());
					
					TruckEdit1.setACT_ID(obj[39]==null?"":obj[39].toString());
					TruckEdit1.setACT_NO(obj[40]==null?"":obj[40].toString());
					TruckEdit1.setACT_EXPIRE_DATE(obj[41]==null?"":obj[41].toString());					
					TruckEdit1.setACT_INSURANCE_MOTOR(obj[42]==null?"":obj[42].toString());
					TruckEdit1.setACT_INSURANCE_LIMIT(obj[43]==null?"":obj[43].toString());
					TruckEdit1.setACT_RESPONSE_BY(obj[44]==null?"":obj[44].toString());
//					TruckEdit1.setACT_STATUS(obj[45]==null?45:((Integer)obj[45]).intValue());
					
//					TruckEdit1.setPOLICY_ID(obj[47]==null?"":obj[47].toString());
					TruckEdit1.setINSURANCE_LIMIT(obj[47]==null?"":obj[47].toString());
					TruckEdit1.setPOLICY_EXPIRY_DATE(obj[48]==null?"":obj[48].toString());
					TruckEdit1.setINSURANCE_COVER_PRODUCT(obj[49]==null?"":obj[49].toString());
					TruckEdit1.setINSURANCE_RESPONSE_BY(obj[50]==null?"":obj[50].toString());
					TruckEdit1.setPOLICY_NO(obj[51]==null?"":obj[51].toString());
//					TruckEdit1.setINSURANCE_STATUS(obj[52]==null?52:((Integer)obj[52]).intValue());
//					TruckEdit1.setINSURANCE_STATUS(obj[53]==null?"":obj[53].toString());
					
					TruckEdit1.setSTAKE_HOLDER_UN_ID(obj[54]==null?"":obj[54].toString());
					TruckEdit1.setSTAKE_HOLDER_ID(obj[55]==null?"":obj[55].toString());
					TruckEdit1.setSTAKE_HOLDER_NAME(obj[56]==null?"":obj[56].toString());
					TruckEdit1.setSTAKE_HOLDER_ADDRESS(obj[57]==null?"":obj[57].toString());
					TruckEdit1.setSTAKE_HOLDER_SUBDISTRICT(obj[58]==null?"":obj[58].toString());
					TruckEdit1.setSTAKE_HOLDER_DISTRICT(obj[59]==null?"":obj[59].toString());
					TruckEdit1.setSTAKE_HOLDER_PROVINCE(obj[60]==null?"":obj[60].toString());
					TruckEdit1.setSTAKE_HOLDER_POST_CODE(obj[61]==null?"":obj[61].toString());
					TruckEdit1.setSTAKE_HOLDER_TEL_NO(obj[62]==null?"":obj[62].toString());
					TruckEdit1.setSTAKE_HOLDER_FAX_NO(obj[63]==null?"":obj[63].toString());
					TruckEdit1.setSTAKE_HOLDER_MOBILE_NO(obj[64]==null?"":obj[64].toString());
					TruckEdit1.setSTAKE_HOLDER_EMAIL(obj[65]==null?"":obj[65].toString());
					TruckEdit1.setSTAKE_HOLDER_CONTACT_NAME(obj[66]==null?"":obj[66].toString());
					TruckEdit1.setSTAKE_HOLDER_TAX_NO(obj[67]==null?"":obj[67].toString());
					TruckEdit1.setSTAKE_HOLDER_PAYMENT_CONDITION(obj[68]==null?"":obj[68].toString());
					TruckEdit1.setSTAKE_HOLDER_PAYMENT_METHOD(obj[69]==null?"":obj[69].toString());
					TruckEdit1.setSTAKE_HOLDER_REF_BANK(obj[70]==null?"":obj[70].toString());
					TruckEdit1.setSTAKE_HOLDER_REF_BANK_BRANCH(obj[71]==null?"":obj[71].toString());
					TruckEdit1.setSTAKE_HOLDER_REF_ACCOUNT_NO(obj[72]==null?"":obj[72].toString());
					TruckEdit1.setSTAKE_HOLDER_REF_ACCOUNT_NAME(obj[73]==null?"":obj[73].toString());
					TruckEdit1.setSTAKE_HOLDER_PROJECT(obj[74]==null?"":obj[74].toString());
					TruckEdit1.setSTAKE_HOLDER_CREDIT_TERM(obj[75]==null?"":obj[75].toString());
					TruckEdit1.setSTAKE_HOLDER_STATUS(obj[76]==null?"":obj[76].toString());
					TruckEdit1.setSTAKE_HOLDER_NAME_IM(obj[77]==null?"":obj[77].toString());
					TruckEdit1.setSTAKE_HOLDER_NAME_IMC(obj[78]==null?"":obj[78].toString());
					
					TruckEdit1.setFLEETCARD_ID(obj[79]==null?79:((Integer)obj[79]).intValue());
					TruckEdit1.setFLEETCARD_BRAND(obj[80]==null?"":obj[80].toString());
					TruckEdit1.setFLEETCARD_NO(obj[81]==null?"":obj[81].toString());
					TruckEdit1.setFLEETCARD_CREDIT_LIMIT(obj[82]==null?"":obj[82].toString());
					TruckEdit1.setFLEETCARD_RECIEVE_DATE(obj[83]==null?"":obj[83].toString());
					TruckEdit1.setFLEET_EXPIRY_DATE(obj[84]==null?"":obj[84].toString());
					
					TruckEdit1.setACT_CAR_NUM(obj[85]==null?"":obj[85].toString());
					TruckEdit1.setACT_CAR_EXPIRE(obj[86]==null?"":obj[86].toString());
					TruckEdit1.setACT_CAR_COMPANY(obj[87]==null?0:Integer.valueOf(obj[87].toString()));
					TruckEdit1.setSUBSIDAIRY(obj[88]==null?"":obj[88].toString());
					
					
					allList.add(TruckEdit1);
				}
			
			return allList;
		}

}
