package com.ic4c.apm.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

public interface IC4CTimeseriesQueryAPIService {
	
	public String getLatestValues(List<String> tagNames, String authorizationToken)
			throws ClientProtocolException, IOException;

	public String getLimitedValues(List<String> tagNames, String authorizationToken)
			throws ClientProtocolException, IOException;
}
