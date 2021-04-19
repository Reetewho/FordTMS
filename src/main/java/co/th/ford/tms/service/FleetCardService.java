package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.FleetCard;


public interface FleetCardService {
	List<FleetCard> findAllFleetCard();

	FleetCard findByFleetCard(String FleetCard);
	
	FleetCard findByFleetCardId(int FleetCard);
	
	FleetCard findByFleetCardNo(String string);

	void updateFleetCard(FleetCard FleetCard);
	
	void saveFleetCard(FleetCard FleetCard);
}
