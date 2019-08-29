package com.air.controller;

import com.air.service.CanvasjsChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class CanvasChartController {

   @Autowired
   private CanvasjsChartService canvasjsChartService;

   @RequestMapping(value = "/chart", method = RequestMethod.GET)
   public String chart(Map<String, Object> modelMap) {
      modelMap.put("dataPointsList", canvasjsChartService.getData().values());
      return "chart";
   }

   @RequestMapping(value = "/data", method = RequestMethod.GET)
   @ResponseBody
   public Map<String, List<Map<Object, Object>>> data() {
      return canvasjsChartService.getData();
   }
}


