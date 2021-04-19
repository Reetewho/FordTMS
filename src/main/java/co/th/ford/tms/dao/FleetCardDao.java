package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.FleetCard;

public interface FleetCardDao {
	List<FleetCard> findAllFleetCard();
	
	FleetCard findByFleetCard(String FleetCard);
	
	FleetCard findByFleetCardId(int FleetCard);
	
	FleetCard findByFleetCardNo(String FleetCard);
	
	void saveFleetCard(FleetCard FleetCard);
}
