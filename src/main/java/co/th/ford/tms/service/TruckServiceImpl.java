package co.th.ford.tms.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.controller.TruckController;
import co.th.ford.tms.dao.TruckDao;
import co.th.ford.tms.model.Truck;

import co.th.ford.tms.aesencrypt.AESCrypt;

/*import co.th.ford.tms.aesencrypt.EncryptDecryptPass;*/

@Service("TruckService")
@Transactional
public class TruckServiceImpl implements TruckService {

	@Autowired
	private TruckDao dao;
	
	

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
        System.out.println("----------> ! Test Data In Truck Service  ! <----------" + entity); 

		if(entity!=null){

			entity.setTRUCK_NUMBER(t.getTRUCK_NUMBER());
			entity.setTRUCK_TYPE(t.getTRUCK_TYPE());
			entity.setGPS_TRUCK(t.getGPS_TRUCK());
			entity.setCREATE_DATE(t.getCREATE_DATE());
			entity.setCREATE_BY(t.getCREATE_BY());
			entity.setUPDATE_DATE(t.getUPDATE_DATE());
			entity.setUPDATE_BY(t.getUPDATE_BY());
			entity.setSTATUS(t.getSTATUS());
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
