package com.air.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CanvasjsChartServiceImpl implements CanvasjsChartService {

   @Autowired
   private CanvasjsChartDao canvasjsChartDao;

   public void setCanvasjsChartDao(CanvasjsChartDao canvasjsChartDao) {
      this.canvasjsChartDao = canvasjsChartDao;
   }

   @Override
   public Map<String, List<Map<Object, Object>>> getData() {
      return canvasjsChartDao.getData();
   }

}
