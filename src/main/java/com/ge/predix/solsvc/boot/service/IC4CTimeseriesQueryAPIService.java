package com.ge.predix.solsvc.boot.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

public interface IC4CTimeseriesQueryAPIService {
	
	public String getLatestValues(List<String> tagNames, String authorizationToken)
			throws ClientProtocolException, IOException;

}
