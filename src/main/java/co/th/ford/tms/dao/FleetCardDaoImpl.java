package co.th.ford.tms.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import co.th.ford.tms.model.FleetCard;

@Repository("FleetCardDao")
public class FleetCardDaoImpl extends AbstractDao<Integer, FleetCard> implements FleetCardDao {
	
	@SuppressWarnings("unchecked")
	public List<FleetCard> findAllFleetCard() {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("FLEET_CARD_STATUS", "1"));
		return (List<FleetCard>) criteria.list();
	}
	
	public FleetCard findByFleetCard(String FleetCard) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("FLEETCARD_BRAND", FleetCard));
		return (FleetCard) criteria.uniqueResult();
	}
	
	public FleetCard findByFleetCardId(int FleetCard) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("FLEETCARD_ID", FleetCard));
		return (FleetCard) criteria.uniqueResult();
	}
	
	
	@Override
	public void saveFleetCard(FleetCard FleetCard) {
		persist(FleetCard);
	}

	
	public FleetCard findByFleetCardNo(String FleetCard) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("FLEETCARD_NO", FleetCard));
		return (FleetCard) criteria.uniqueResult();
	}
}
