package com.ic4c.apm.scheduler;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ic4c.apm.dto.AttributesDTO;
import com.ic4c.apm.dto.BearerTokenResponseDTO;
import com.ic4c.apm.dto.IC4CAssetDTO;
import com.ic4c.apm.dto.TagIngestDTO;
import com.ic4c.apm.dto.Tags;
import com.ic4c.apm.dto.TimeSeriesIngestionDTO;
import com.ic4c.apm.dto.TimeSeriesResponse;
import com.ic4c.apm.service.IC4CTimeseriesQueryAPIService;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

@Component
public class ScheduledTasks {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final ObjectMapper objectMapper = new ObjectMapper();

	private WebSocketAdapter onConnectedListener = new WebSocketAdapter() {
		private Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

		@SuppressWarnings("nls")
		@Override
		public void onConnected(WebSocket websocket, Map<String, List<String>> wsHeaders) {
			this.logger.info("CONNECTED...." + wsHeaders.toString());
		}
	};

	@Autowired
	protected IC4CTimeseriesQueryAPIService IC4CTmsService;

	@Scheduled(fixedDelay = 20000)
	public void getLatestData() throws WebSocketException {

		logger.info("Running Scheduler");
		
		String UAAURL = "https://57b93619-4f14-4e56-b215-b985c4b8b245.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token?client_id=ic4c-client&grant_type=client_credentials";

		String bearerToken="";

		HttpHeaders tokenHeaders = new HttpHeaders();
		tokenHeaders.add("Authorization", "Basic aWM0Yy1jbGllbnQ6aWM0Yy1jbGllbnRzZWNyZXQ=");
		tokenHeaders.add("content-type", "application/x-www-form-urlencoded");

		HttpEntity<String> tokenRequest = new HttpEntity<String>(tokenHeaders);
		RestTemplate restTemplateForToken = new RestTemplate();
		ResponseEntity<BearerTokenResponseDTO> tokenResponse = restTemplateForToken.exchange(UAAURL,
				HttpMethod.GET, tokenRequest, BearerTokenResponseDTO.class);

		bearerToken = tokenResponse.getBody().getAccess_token();
			

		String allAssets = "";

		String classificationWithFilterURL = "https://predix-asset.run.aws-usw02-pr.ice.predix.io/asset";

		// logger.info("",classificationClonableResponse.getEntity());
		List<IC4CAssetDTO> assetList;

		String authorizationToken ="Bearer "+bearerToken;
		 //"Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkhlNFd3IiwidHlwIjoiSldUIn0.eyJqdGkiOiI4YzZhMzQ5Y2QzNDg0MTY4OTQ5ZjU4YzBjMTY3MTM2YyIsInN1YiI6ImljNGMtY2xpZW50Iiwic2NvcGUiOlsicHJlZGl4LWFzc2V0LnpvbmVzLmVmYWI5MjZhLTRjNmUtNGY2Yi04YTk0LWY1YTk1NDkzYTZmMS51c2VyIiwiaWRwcy53cml0ZSIsInRpbWVzZXJpZXMuem9uZXMuZWZmZjliMTktMWJhZC00MTZmLWFiM2EtMTY4MmEzYjRjYjZiLnVzZXIiLCJwcmVkaXgtYWNzLnpvbmVzLjcyNThmMGFiLTM5MjYtNDYyYS1iNzhjLTNiYWQ1NTYwZmNmNi51c2VyIiwib2F1dGguYXBwcm92YWwiLCJ1YWEubm9uZSIsImlkcHMucmVhZCIsInpvbmVzLnJlYWQiLCJjbGllbnRzLnJlYWQiLCJzY2ltLnVzZXJpZHMiLCJjbGllbnRzLnNlY3JldCIsInVhYS5yZXNvdXJjZSIsIm9wZW5pZCIsInNjaW0uaW52aXRlIiwiZ3JvdXBzLnVwZGF0ZSIsInByZWRpeC1hY3Muem9uZXMuYzQ2Y2RhNjQtOWZhOS00YzU2LWEzMDktNWMwNjRlNDk2YzgyLnVzZXIiLCJvYXV0aC5sb2dpbiIsInRpbWVzZXJpZXMuem9uZXMuY2RjZmI4MTUtYjhhMS00ZWU3LWFlNGYtZjZkYTI3MDY0YmFjLmluZ2VzdCIsInVhYS5hZG1pbiIsImNsaWVudHMuYWRtaW4iLCJ0aW1lc2VyaWVzLnpvbmVzLmVmZmY5YjE5LTFiYWQtNDE2Zi1hYjNhLTE2ODJhM2I0Y2I2Yi5xdWVyeSIsInRpbWVzZXJpZXMuem9uZXMuZWZmZjliMTktMWJhZC00MTZmLWFiM2EtMTY4MmEzYjRjYjZiLmluZ2VzdCIsInNjaW0uY3JlYXRlIiwic2NpbS5yZWFkIiwidWFhLnVzZXIiLCJwYXNzd29yZC53cml0ZSIsInByZWRpeC1hc3NldC56b25lcy5hMTViYmMxNy1mOGIwLTRjY2ItOTU1OC1kMGZkMjJkNzkxZDYudXNlciIsInpvbmVzLndyaXRlIiwiY2xpZW50cy53cml0ZSIsInRpbWVzZXJpZXMuem9uZXMuY2RjZmI4MTUtYjhhMS00ZWU3LWFlNGYtZjZkYTI3MDY0YmFjLnVzZXIiLCJzY2ltLndyaXRlIiwidGltZXNlcmllcy56b25lcy5jZGNmYjgxNS1iOGExLTRlZTctYWU0Zi1mNmRhMjcwNjRiYWMucXVlcnkiXSwiY2xpZW50X2lkIjoiaWM0Yy1jbGllbnQiLCJjaWQiOiJpYzRjLWNsaWVudCIsImF6cCI6ImljNGMtY2xpZW50IiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyIsInJldl9zaWciOiJkZDU3YTQyYiIsImlhdCI6MTUxNTI2Nzc3MiwiZXhwIjoxNTE1NzY3NzcyLCJpc3MiOiJodHRwczovLzU3YjkzNjE5LTRmMTQtNGU1Ni1iMjE1LWI5ODVjNGI4YjI0NS5wcmVkaXgtdWFhLnJ1bi5hd3MtdXN3MDItcHIuaWNlLnByZWRpeC5pby9vYXV0aC90b2tlbiIsInppZCI6IjU3YjkzNjE5LTRmMTQtNGU1Ni1iMjE1LWI5ODVjNGI4YjI0NSIsImF1ZCI6WyJwcmVkaXgtYWNzLnpvbmVzLjcyNThmMGFiLTM5MjYtNDYyYS1iNzhjLTNiYWQ1NTYwZmNmNiIsInNjaW0iLCJjbGllbnRzIiwib3BlbmlkIiwiZ3JvdXBzIiwiem9uZXMiLCJwcmVkaXgtYWNzLnpvbmVzLmM0NmNkYTY0LTlmYTktNGM1Ni1hMzA5LTVjMDY0ZTQ5NmM4MiIsImlkcHMiLCJwcmVkaXgtYXNzZXQuem9uZXMuYTE1YmJjMTctZjhiMC00Y2NiLTk1NTgtZDBmZDIyZDc5MWQ2IiwidGltZXNlcmllcy56b25lcy5lZmZmOWIxOS0xYmFkLTQxNmYtYWIzYS0xNjgyYTNiNGNiNmIiLCJwYXNzd29yZCIsInByZWRpeC1hc3NldC56b25lcy5lZmFiOTI2YS00YzZlLTRmNmItOGE5NC1mNWE5NTQ5M2E2ZjEiLCJ1YWEiLCJpYzRjLWNsaWVudCIsInRpbWVzZXJpZXMuem9uZXMuY2RjZmI4MTUtYjhhMS00ZWU3LWFlNGYtZjZkYTI3MDY0YmFjIiwib2F1dGgiXX0.F1RSxwddfHO1BFC7cqYjFrzR_tc68TMAx64MveoQbAuL1s60ZBgJOvFmEiGjcoFb7UiTxfmNxhACjskyy1SGDnRboCWkC9cl-mB7McH9FmVnapKPif8hutCrbHgOWiioKGzcTxxAMwhF3XKo483YrMRIJGtCnDjy1zm3JM0zJxSEY6B4HhPQZqm1pCC_iswwAv9OMOBPgDc7YanpAswkGdTeO_Q4yASrce51jz73LXSBmLFDrfcF-eLVPFyWf7yQr3qS1oSbn2ok0Wzm2J0SQF1hJf1lG_T1bybPhBF2DXmftobpXM4DCmLx8GYaAnhUe1-SSK6dRKCZFlOjHh40cw";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", authorizationToken);
		headers.add("predix-zone-id", "a15bbc17-f8b0-4ccb-9558-d0fd22d791d6");

		HttpEntity<String> request = new HttpEntity<String>(headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> classificationClonableResponse = restTemplate.exchange(classificationWithFilterURL,
				HttpMethod.GET, request, String.class);

		try {
			allAssets = classificationClonableResponse.getBody();
			assetList = objectMapper.readValue(allAssets, new TypeReference<List<IC4CAssetDTO>>() {
			});
			List<String> tags = new ArrayList<String>();
			for (IC4CAssetDTO asset : assetList) {
				tags.addAll(asset.getParameters().keySet());
			}

			logger.debug("TAG NAME: {}", tags.get(0));

			TimeSeriesResponse responseObj = objectMapper
					.readValue(IC4CTmsService.getLimitedValues(tags, authorizationToken), TimeSeriesResponse.class);

			logger.debug("TAG VALUE: {}", responseObj.getTags().get(0).getResults()[0].getValues()[1][0]);

			List<TagIngestDTO> body = new ArrayList<>();

			for (IC4CAssetDTO asset : assetList) {
				logger.debug("###############################" + asset.getId());
				if(asset!=null){
					for (Tags tag : responseObj.getTags()) {
						 
						if (asset.getParameters().get(tag.getName())!=null && asset.getParameters().get(tag.getName()).getPinType().equals("analog")) {

							double lowLimit = asset.getParameters().get(tag.getName()).getLow();
							double highLimit = asset.getParameters().get(tag.getName()).getHigh();
							float mul = Float.parseFloat(asset.getParameters().get(tag.getName()).getMultiplier());

							double prevVal = tag.getResults()[0].getValues()[0][1] * mul;
							double curVal = tag.getResults()[0].getValues()[1][1] * mul;

							logger.info("TAG NAME: {} -- LOW : {} -- HIGH :{} -- PREV VALUE: {} -- CUR VALUE: {}",
									tag.getName(), lowLimit, highLimit, prevVal,
									curVal);

							// 

							if ((prevVal >= lowLimit && prevVal <= highLimit) && (curVal < lowLimit || curVal > highLimit)) {
								logger.error("THRESHOLD BREACHED and Value is: {}", curVal);
								List<List<Long>> datapoints = new ArrayList<>();
								List<Long> datapoint = new ArrayList<>();
								datapoint.add((long) tag.getResults()[0].getValues()[0][0]);
								datapoint.add((long) 1);
								datapoint.add((long) 3);
								datapoints.add(datapoint);
								body.add(new TagIngestDTO("ALERT_TEST_123__" + tag.getName(), datapoints, new AttributesDTO(
										asset.getId(), asset.getParameters().get(tag.getName()).getTagType())));
							}
						}
					}
				}
				
			}

			if (body.size() > 0) {
				TimeSeriesIngestionDTO tmsIngestObj = new TimeSeriesIngestionDTO();
				tmsIngestObj.setMessgaeId(String.valueOf(System.currentTimeMillis()));
				tmsIngestObj.setBody(body);

				WebSocketFactory factory = new WebSocketFactory();

				logger.info("***********************Connecting to wss");
				WebSocket ws = factory.createSocket(
						"wss://gateway-predix-data-services.run.aws-usw02-pr.ice.predix.io/v1/stream/messages");

				ws.addHeader("predix-zone-id", "efff9b19-1bad-416f-ab3a-1682a3b4cb6b");
				ws.addHeader("authorization", authorizationToken);
				ws.addHeader("origin", "https://localhost");
				ws.addListener(this.onConnectedListener);
				ws.connect();
				logger.info("COnnected #######################");
				ObjectMapper mapper = new ObjectMapper();
				String tmsIngestStr = mapper.writeValueAsString(tmsIngestObj);
				logger.info("INGESTION BODY: {}", tmsIngestStr);
				ws.sendText(tmsIngestStr);
			    ws.sendClose();
				ws.disconnect();
			}

		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
