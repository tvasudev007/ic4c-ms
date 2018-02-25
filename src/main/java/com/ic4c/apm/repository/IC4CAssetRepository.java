/**
 * 
 */
package com.ic4c.apm.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ic4c.apm.dto.IC4CAssetDTO;

/**
 * @author vasudev007
 *
 */
@Component
public class IC4CAssetRepository implements IC4CRepository {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final String assetZoneID = "a15bbc17-f8b0-4ccb-9558-d0fd22d791d6";

	private final String assetURL = "https://predix-asset.run.aws-usw02-pr.ice.predix.io/AMD";

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	@Cacheable("ic4cAssets")
	public List<IC4CAssetDTO> getAllAssetFromCache(String authorizationToken) {

		logger.info("Fetching Meta Data of all Assets");
		String allAssets = "";

		String classificationWithFilterURL = assetURL
				+ "?fields=id,parent,classification,complexType,description,attributes";

		List<IC4CAssetDTO> assetList = null;

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorizationToken);
		headers.add("predix-zone-id", assetZoneID);

		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> classificationClonableResponse = restTemplate.exchange(classificationWithFilterURL,
				HttpMethod.GET, request, String.class);

		try {
			allAssets = classificationClonableResponse.getBody();
			assetList = objectMapper.readValue(allAssets, new TypeReference<List<IC4CAssetDTO>>() {
			});
		} catch (Exception e) {
			logger.error("Error fectching assets from predix asset service: {}", e.getMessage());
		}

		return assetList;
	}

}
