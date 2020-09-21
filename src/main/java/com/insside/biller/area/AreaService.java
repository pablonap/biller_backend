package com.insside.biller.area;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService {

	@Autowired
	private AreaRepository areaRepository;
	
	public List<Area> getAreas() {
		return areaRepository.findAll();
	}
	
	public Area getAreaById(long id) {
		Optional<Area> areaTemp = areaRepository.findById(id);
		Area area = null;
		
		if (areaTemp.isPresent()) {
			area = areaTemp.get();
		}

		return area;
	}

}
