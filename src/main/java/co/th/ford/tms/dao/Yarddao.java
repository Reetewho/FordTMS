
package co.th.ford.tms.dao;

import java.util.List;

import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.Yard;

public interface Yarddao {

	List<Yard> findAllYard();
	
	Yard findByNameYard(String NameYard);
	
	Yard findByNameYardId(int NameYard);
	
	void saveYard(Yard NameYard);

	
}
