package com.insside.budget.area;

import org.springframework.stereotype.Service;

@Service
public class AreaService {

	AreaRepository areaRespository;
	

	public AreaService(AreaRepository areaRespository) {
		this.areaRespository = areaRespository;
	}
	
	public Area save(Area area) {
		return areaRespository.save(area);
	}

}
