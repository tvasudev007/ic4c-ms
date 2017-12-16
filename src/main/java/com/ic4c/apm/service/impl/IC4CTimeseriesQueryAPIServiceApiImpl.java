/**
 * 
 */
package com.ic4c.apm.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ic4c.apm.dto.TagNamesDTO;
import com.ic4c.apm.service.IC4CTimeseriesQueryAPIService;



@Service(value = "iC4CTimeseriesQueryAPIService")
public class IC4CTimeseriesQueryAPIServiceApiImpl implements IC4CTimeseriesQueryAPIService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public String getLatestValues(List<String> tagNames, String authorizationToken)
			throws ClientProtocolException, IOException {

		TagNamesDTO tagNamesDTO = new TagNamesDTO();
		tagNamesDTO.setName(tagNames);
		List<TagNamesDTO> tagNameList = new ArrayList<TagNamesDTO>();
		tagNameList.add(tagNamesDTO);

		Map<String, List<TagNamesDTO>> requestObj = new HashMap<>();

		requestObj.put("tags", tagNameList);

		String requestBody = objectMapper.writeValueAsString(requestObj);

		logger.info("Timeseries Request object :  {}", requestBody);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(
				"https://time-series-store-predix.run.aws-usw02-pr.ice.predix.io/v1/datapoints/latest");

		StringEntity entity = null;
		String responseFromTMS = null;

		entity = new StringEntity(requestBody);
		httpPost.setEntity(entity);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-type", "application/json");
		httpPost.setHeader("authorization", "Bearer " + authorizationToken);
		httpPost.setHeader("predix-zone-id", "efff9b19-1bad-416f-ab3a-1682a3b4cb6b");

		CloseableHttpResponse response = client.execute(httpPost);
		responseFromTMS = EntityUtils.toString(response.getEntity());
		
		
 
		return responseFromTMS;
	}

}
