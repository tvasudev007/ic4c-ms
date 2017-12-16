/**
 * 
 */
package com.ge.predix.solsvc.boot.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import com.ge.predix.solsvc.boot.dto.IC4CAssetDTO;
import com.ge.predix.solsvc.boot.dto.TimeSeriesResponse;
import com.ge.predix.solsvc.boot.service.IC4CAssetService;
import com.ge.predix.solsvc.boot.service.IC4CTimeseriesQueryAPIService;

@Service(value = "iC4CAssetService")
public class IC4CAssetServiceImpl implements IC4CAssetService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected IC4CTimeseriesQueryAPIService IC4CTmsService;

	private final ObjectMapper objectMapper = new ObjectMapper();// jackson's
																	// objectmapper

	@Override
	public List<IC4CAssetDTO> getAssetDetails(String authorizationToken) {
		logger.info("Fetching All Asset List in a Project");
		String allAssets = "";
	
	
		String classificationWithFilterURL = "https://predix-asset.run.aws-usw02-pr.ice.predix.io/asset";

		// logger.info("",classificationClonableResponse.getEntity());
		List<IC4CAssetDTO> assetList;
		
		 HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization",authorizationToken);
		headers.add("predix-zone-id","a15bbc17-f8b0-4ccb-9558-d0fd22d791d6");

		 HttpEntity<String> request = new HttpEntity<String>(headers);
		 RestTemplate restTemplate = new RestTemplate();
		 ResponseEntity<String> classificationClonableResponse = restTemplate.exchange(classificationWithFilterURL, HttpMethod.GET, request, String.class);

		try {
			allAssets = classificationClonableResponse.getBody();
			assetList = objectMapper.readValue(allAssets, new TypeReference<List<IC4CAssetDTO>>() {
			});
			List<String> tags = new ArrayList<String>(assetList.get(0).getParameters().keySet());

			logger.info("TAG NAME: {}", tags.get(0));

			TimeSeriesResponse responseObj = objectMapper
					.readValue(IC4CTmsService.getLatestValues(tags, authorizationToken), TimeSeriesResponse.class);

			logger.info("TAG NAME: {}", responseObj.getTags().get(0).getName());

			for (int i = 0; i < tags.size(); i++) {

				float mul = Float.parseFloat(assetList.get(0).getParameters().get(tags.get(i)).getMultiplier());
				String tagName = responseObj.getTags().get(i).getName();
				double value = responseObj.getTags().get(i).getResults()[0].getValues()[0][1];

				logger.info("TAG NAME SEQUENCE CHECK : {}, {},  MUL: {}, VALUE: {}", tags.get(i), tagName, mul, value);
				assetList.get(0).getParameters().get(tags.get(i)).setValue(value * mul);
			}

			return assetList;

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
