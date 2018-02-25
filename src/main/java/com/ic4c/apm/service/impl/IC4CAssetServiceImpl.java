package com.ic4c.apm.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.ic4c.apm.dto.IC4CAssetDTO;
import com.ic4c.apm.dto.IC4CAssetParametersDTO;
import com.ic4c.apm.dto.TimeSeriesResponse;
import com.ic4c.apm.repository.IC4CRepository;
import com.ic4c.apm.service.IC4CAssetService;
import com.ic4c.apm.service.IC4CTimeseriesQueryAPIService;

@Service(value = "iC4CAssetService")
public class IC4CAssetServiceImpl implements IC4CAssetService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String assetZoneID = "a15bbc17-f8b0-4ccb-9558-d0fd22d791d6";

	private final String assetURL = "https://predix-asset.run.aws-usw02-pr.ice.predix.io/AMD";

	@Autowired
	private IC4CTimeseriesQueryAPIService IC4CTmsService;

	@Autowired
	private IC4CRepository iC4CAssetRepo;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public List<IC4CAssetDTO> getAssetDetails(String authorizationToken) {

		return null;
	}

	@Override
	public List<IC4CAssetDTO> getAssetDetails(String authorizationToken, String assetId) {

		logger.info("Fetching asset details for ID: {}", assetId);
		String allAssets = "";

		String classificationWithFilterURL = assetURL + "?filter=id=" + assetId;

		List<IC4CAssetDTO> assetList;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorizationToken);
		headers.add("predix-zone-id", assetZoneID);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> classificationClonableResponse = restTemplate.exchange(classificationWithFilterURL,
				HttpMethod.GET, request, String.class);
		int digitalCounter = 0;

		try {
			allAssets = classificationClonableResponse.getBody();
			assetList = objectMapper.readValue(allAssets, new TypeReference<List<IC4CAssetDTO>>() {
			});
			List<String> tags = new ArrayList<String>(assetList.get(0).getParameters().keySet());

			logger.info("TAG NAME: {}", tags.get(0));

			TimeSeriesResponse responseObj = objectMapper
					.readValue(IC4CTmsService.getLatestValues(tags, authorizationToken), TimeSeriesResponse.class);

			logger.info("TAG NAME: {}", responseObj.getTags().get(0).getName());
			Map<String, IC4CAssetParametersDTO> parametersDIGIN = new HashMap<String, IC4CAssetParametersDTO>();

			for (int i = 0; i < tags.size(); i++) {

				float mul = Float.parseFloat(assetList.get(0).getParameters().get(tags.get(i)).getMultiplier());
				String tagName = responseObj.getTags().get(i).getName();
				long timestamp = (long) responseObj.getTags().get(i).getResults()[0].getValues()[0][0];

				if (assetList.get(0).getParameters().get(tags.get(i)).getBinary().equalsIgnoreCase("true")) {

					int value = (int) responseObj.getTags().get(i).getResults()[0].getValues()[0][1];
					String binStr = Integer.toBinaryString(value);
					int binaryDigits = assetList.get(0).getParameters().get(tags.get(i)).getBinaryDigits();
					int index = assetList.get(0).getParameters().get(tags.get(i)).getIndex();
					List<String> pinNames = assetList.get(0).getParameters().get(tags.get(i)).getPinNames();
					while (binStr.length() < binaryDigits) {
						binStr = "0" + (binStr);
					}
					logger.info(responseObj.getTags().get(0).getName() + " : {}", binStr);
					for (int j = 1; j <= binaryDigits; j++) {
						char DIGIN = binStr.charAt(j - 1);
						digitalCounter++;
						parametersDIGIN.put(pinNames.get(j - 1),
								new IC4CAssetParametersDTO("digital", String.valueOf(digitalCounter),
										pinNames.get(j - 1), "NA", "false", "1", 0, 1, Character.getNumericValue(DIGIN),
										timestamp, index));
					}

				} else {
					double value = responseObj.getTags().get(i).getResults()[0].getValues()[0][1];

					logger.info("TAG NAME SEQUENCE CHECK : {}, {},  MUL: {}, VALUE: {}", tags.get(i), tagName, mul,
							value);
					assetList.get(0).getParameters().get(tags.get(i)).setValue(value * mul);
					assetList.get(0).getParameters().get(tags.get(i)).setTimestamp(timestamp);
				}

			}

			assetList.get(0).getParameters().putAll(parametersDIGIN);

			Map<String, IC4CAssetParametersDTO> parametersDTO = new HashMap<>();

			for (String key : assetList.get(0).getParameters().keySet()) {

				String newKey = key.replace('-', '_');
				if (!assetList.get(0).getParameters().get(key).getBinary().equalsIgnoreCase("true")) {
					parametersDTO.put(newKey, assetList.get(0).getParameters().get(key));
				}
			}

			assetList.get(0).setParameters(parametersDTO);

			return assetList;

		} catch (

		ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<IC4CAssetDTO> getAssetMetaData(String authorizationToken) {

		return iC4CAssetRepo.getAllAssetFromCache(authorizationToken);
	}

}
