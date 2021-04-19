package co.th.ford.tms.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.controller.TruckController;
import co.th.ford.tms.dao.TruckDao;
import co.th.ford.tms.model.Report1;
import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.TruckEdit;
import co.th.ford.tms.aesencrypt.AESCrypt;

/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Service("TruckService")
@Transactional
public class TruckServiceImpl implements TruckService {

	@Autowired
	private TruckDao dao;
	
	public List<TruckEdit> getTruckEdit(String TruckEdit) {
		return dao.getTruckEdit(TruckEdit);
	}

	public void saveTrucknumber(Truck truck) {
		dao.saveTrucknumber(truck);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateTrucknumber(Truck t) {
		Truck entity = dao.findByTrucknumber(t.getTRUCK_NUMBER());
		if(entity!=null){

			entity.setTRUCK_NUMBER(t.getTRUCK_NUMBER());
			entity.setPROVINCE(t.getPROVINCE());
			entity.setYARD(t.getYARD());
			entity.setCOMPANY(t.getCOMPANY());
			entity.setPLATE_TYPE(t.getPLATE_TYPE());
			entity.setTRUCK_TYPE(t.getTRUCK_TYPE());
			entity.setBRAND(t.getBRAND());
			entity.setFUEL_TYPE(t.getFUEL_TYPE());
			entity.setCONTAINER_BORDER_SIZE(t.getCONTAINER_BORDER_SIZE());
			entity.setCONTAINER_SIZE(t.getCONTAINER_SIZE());
			entity.setAVAERAGE_FUEL(t.getAVAERAGE_FUEL());
			entity.setPROJECT(t.getPROJECT());
			entity.setLATEST_MILAGE(t.getLATEST_MILAGE());
			entity.setSTATUS(t.getSTATUS());
			entity.setFIRST_REGIST_DATE(t.getFIRST_REGIST_DATE());
			entity.setTRUCK_AGE(t.getTRUCK_AGE());
			entity.setEXPIRE_DATE(t.getEXPIRE_DATE());
			entity.setMODEL(t.getMODEL());
			entity.setCHASSIS_NO(t.getCHASSIS_NO());
			entity.setENGINE_NO(t.getENGINE_NO());
			entity.setPOSITION1(t.getPOSITION1());
			entity.setPOSITION2(t.getPOSITION2());
			entity.setPUMP_NO(t.getPUMP_NO());
			entity.setHPS(t.getHPS());
			entity.setWEIGHT(t.getWEIGHT());
			entity.setWEIGHT_CARRY(t.getWEIGHT_CARRY());
			entity.setWEIGHT_TOTAL(t.getWEIGHT_TOTAL());
			entity.setACT_NO(t.getACT_NO());
			entity.setPOLICY_NO(t.getPOLICY_NO());
			entity.setFLEETCARD_NO(t.getFLEETCARD_NO());
			entity.setGPS_TRUCK(t.getGPS_TRUCK());
			entity.setTRUCK_AGE_YEAR(t.getTRUCK_AGE_YEAR());
			entity.setTRUCK_AGE_MONTH(t.getTRUCK_AGE_MONTH());
			entity.setSUPPLIER_CODE(t.getSUPPLIER_CODE());	
			entity.setINSTALLATION_DATE(t.getINSTALLATION_DATE());
			entity.setRENEW_DYATE(t.getRENEW_DYATE());
			entity.setACT_CAR_NUM(t.getACT_CAR_NUM());
			entity.setACT_CAR_EXPIRE(t.getACT_CAR_EXPIRE());
			entity.setCREATE_DATE(t.getCREATE_DATE());
			entity.setCREATE_BY(t.getCREATE_BY());
			entity.setUPDATE_DATE(t.getUPDATE_DATE());
			entity.setUPDATE_BY(t.getUPDATE_BY());	
			
			entity.setACT_CAR_COMPANY(t.getACT_CAR_COMPANY());
			entity.setSUBSIDAIRY(t.getSUBSIDAIRY());
		}
	}

	public List<Truck> findAllTruckNumber() {
		return dao.findAllTruckNumber();
	}
	
	public List<Truck> findBytype(int trucktype) {
		return dao.findBytype(trucktype);
	}

	public Truck findByTrucknumber(String trucknumber) {
		return dao.findByTrucknumber(trucknumber);
	}

	
	@Override
	public void deleteByTrucknumber(String trucknumber) {
		dao.deleteByTrucknumber(trucknumber);
		
	}
	
}
