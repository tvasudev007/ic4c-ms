/**
 * 
 */
package com.ic4c.apm.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ic4c.apm.dto.IC4CAlertDTO;
import com.ic4c.apm.dto.IC4CAssetDTO;
import com.ic4c.apm.dto.IC4CAssetParametersDTO;
import com.ic4c.apm.dto.TagsForAlerts;
import com.ic4c.apm.dto.TimeSeriesResponseForAlerts;
import com.ic4c.apm.service.IC4CAlertService;
import com.ic4c.apm.service.IC4CTimeseriesQueryAPIService;

@Service(value = "iC4CAlertService")
public class IC4CAlertServiceImpl implements IC4CAlertService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String assetZoneID = "a15bbc17-f8b0-4ccb-9558-d0fd22d791d6";

	private final String assetURL = "https://predix-asset.run.aws-usw02-pr.ice.predix.io/asset";

	@Autowired
	protected IC4CTimeseriesQueryAPIService IC4CTmsService;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<IC4CAlertDTO> getAllAlerts(String authorizationToken) {
		logger.info("Fetching All Asset List in a Project");
		String allAssets = "";

		List<IC4CAssetDTO> assetList;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorizationToken);
		headers.add("predix-zone-id", assetZoneID);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> classificationClonableResponse = restTemplate.exchange(assetURL, HttpMethod.GET, request,
				String.class);
		try {
			allAssets = classificationClonableResponse.getBody();
			assetList = objectMapper.readValue(allAssets, new TypeReference<List<IC4CAssetDTO>>() {
			});
			List<String> tags = new ArrayList<String>();
			for (IC4CAssetDTO asset : assetList) {
				for (Map.Entry<String, IC4CAssetParametersDTO> param : asset.getParameters().entrySet()) {
					if (param.getValue().getPinType().equalsIgnoreCase("analog")) {
						tags.add("ALERT_" + param.getKey());
					}
				}

			}

			logger.debug("TAG NAME: {}", tags.get(0));

			TimeSeriesResponseForAlerts responseObj = objectMapper
					.readValue(IC4CTmsService.getLatestValues(tags, authorizationToken), TimeSeriesResponseForAlerts.class);

			logger.debug("TAG NAME: {}", responseObj.getTags().get(0).getName());

			List<IC4CAlertDTO> alerts = new ArrayList<>();
			int id=0;
			
			long currentTime = System.currentTimeMillis();
			for (TagsForAlerts tag : responseObj.getTags()) {
				id++;
				if(tag.getResults().length>0 && tag.getResults()[0].getValues().length>0 ){
					String assetId;
					String tagType;
					if(tag.getResults()[0].getAttributes()==null){
						
						 assetId = tag.getName();
						 tagType = tag.getName();
						
					}else{
						 assetId = tag.getResults()[0].getAttributes().getHost().get(0);
						 tagType = tag.getResults()[0].getAttributes().getCustomer().get(0);
					}
					
					long millis = (long) tag.getResults()[0].getValues()[0][0];
					int statusCode = (int) tag.getResults()[0].getValues()[0][1];
					logger.debug("TIME IN MILLIS: {} ########### CURRENT TIME: {}",millis,currentTime);
					Date date = new Date(millis);
					String timeStr = null;
					if(currentTime-millis<=1000){
						timeStr="1 sec ago";
					}
					else if(currentTime-millis<=1000*60){
						timeStr="1 min ago";
					}
					else if(currentTime-millis<=1000*60*60){
						
						int min = (int) ((currentTime-millis)/(1000*60));
						
						timeStr= min +" mins ago";
					}
					else if(currentTime-millis<=1000*60*60*24){
						int hours = (int) ((currentTime-millis)/(1000*60*60));
						
						timeStr= hours +" hours ago";
					}
					else if(currentTime-millis<=1000*60*60*24*10){
						
						int days = (int) ((currentTime-millis)/(1000*60*60*24));
						timeStr= days+" days ago";
					}
					else {
						timeStr="10+ days ago";
					}
					alerts.add(new IC4CAlertDTO(id,date, String.valueOf(statusCode), timeStr, assetId, tagType));
				}
				
			}
			return alerts;

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
