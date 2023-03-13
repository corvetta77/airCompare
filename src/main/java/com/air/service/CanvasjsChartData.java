//CanvasjsChartData.java
package com.air.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class CanvasjsChartData {


   private static final String SIERCZA_INSTALLATION_ID = "2849";
   private static final String KURASIA_INSTALLATION_ID = "500";

   private static Map<String, String> namePerInstallationId = new HashMap<String, String>() {
      {
         put(SIERCZA_INSTALLATION_ID, "Addr1");
         put(KURASIA_INSTALLATION_ID, "Addr2");
      }
   };

   private static Map<String, List<Map<Object, Object>>> data = new LinkedHashMap<String, List<Map<Object, Object>>>() {
      {
         put(SIERCZA_INSTALLATION_ID, new ArrayList<>());
         put(KURASIA_INSTALLATION_ID, new ArrayList<>());
      }
   };


   @Scheduled(fixedRate = 1800000)
   public void callAirlyAndUpdateDatapoints() {
      for (Map.Entry<String, List<Map<Object, Object>>> entry : data.entrySet()) {
         addPoint(entry.getValue(), entry.getKey());
      }
   }

   private void addPoint(List<Map<Object, Object>> dataPoints, final String installationId) {
      Map<Object, Object> map = new HashMap<>();
      map.put("x", System.currentTimeMillis() + "L");

      HttpHeaders headers = new HttpHeaders();
      headers.set("apikey", API_KEY);

      HttpEntity entity = new HttpEntity(headers);
      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<String> response = restTemplate.exchange(
         "http://airapi.airly.eu/v2/measurements/installation?installationId=" + installationId, HttpMethod.GET, entity, String.class);


      try {
         HashMap<String, Object> result = new ObjectMapper().readValue(response.getBody(), new TypeReference<Map<String, Object>>() {});
         HashMap<String, Object> current = (HashMap<String, Object>) result.get("current");

         map.put("y", ((Double) ((HashMap<String, Object>) ((ArrayList) current.get("indexes")).get(0)).get("value")).floatValue());
      } catch (IOException e) {
      }
      dataPoints.add(map);
   }

   public static Map<String, List<Map<Object, Object>>> getData() {
      Map<String, List<Map<Object, Object>>> dataWithNames = new LinkedHashMap<>();
      for (Map.Entry<String, List<Map<Object, Object>>> entry : data.entrySet()) {
         dataWithNames.put(namePerInstallationId.get(entry.getKey()), entry.getValue());
      }

      return dataWithNames;
   }
}                        
