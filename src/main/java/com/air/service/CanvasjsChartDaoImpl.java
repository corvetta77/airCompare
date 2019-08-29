package com.air.service;


import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class CanvasjsChartDaoImpl implements CanvasjsChartDao {

   @Override
	public Map<String, List<Map<Object, Object>>> getData() {
		return CanvasjsChartData.getData();
	}
 
}  