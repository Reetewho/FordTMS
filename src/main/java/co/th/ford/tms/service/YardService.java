package co.th.ford.tms.service;

import java.util.List;

import co.th.ford.tms.model.Truck;
import co.th.ford.tms.model.Yard;

public interface YardService {
	List<Yard> findAllYard(); 
	
	Yard findByNameYard(String NameYard);
	
	Yard findByNameYardId(int NameYard);

	void saveYard(Yard Yard);
}
