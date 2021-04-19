package co.th.ford.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.th.ford.tms.dao.FleetCardDao;
import co.th.ford.tms.model.FleetCard;

@Service("FleetCardService")
@Transactional
public class FleetCardServiceImpl implements FleetCardService {
	@Autowired
	private FleetCardDao dao;
	
	
	public List<FleetCard> findAllFleetCard() {
		return dao.findAllFleetCard();
	}
	
	public FleetCard findByFleetCard(String FleetCard) {
		return dao.findByFleetCard(FleetCard);
	}
	
	public FleetCard findByFleetCardId(int FleetCard) {
		return dao.findByFleetCardId(FleetCard);
	}
	
	public FleetCard findByFleetCardNo(String FleetCard) {
		return dao.findByFleetCardNo(FleetCard);
	}
	
	public void saveFleetCard(FleetCard FleetCard) {
		dao.saveFleetCard(FleetCard);
	}
	
	public void updateFleetCard(FleetCard FleetCard) {
		FleetCard entity = dao.findByFleetCardId(FleetCard.getFLEETCARD_ID());
		if(entity!=null){
			entity.setFLEETCARD_BRAND(FleetCard.getFLEETCARD_BRAND());
			entity.setFLEETCARD_CREDIT_LIMIT(FleetCard.getFLEETCARD_CREDIT_LIMIT());
			entity.setFLEET_EXPIRY_DATE(FleetCard.getFLEET_EXPIRY_DATE());
			entity.setFLEETCARD_NO(FleetCard.getFLEETCARD_NO());
			entity.setFLEETCARD_RECIEVE_DATE(FleetCard.getFLEETCARD_RECIEVE_DATE());
			entity.setFLEET_CARD_STATUS(FleetCard.getFLEET_CARD_STATUS());

			}
		}
	
	
}


