package com.ic4c.apm.service;

import java.util.List;

import com.ic4c.apm.dto.IC4CAssetDTO;

public interface IC4CAssetService {
	
	public List<IC4CAssetDTO> getAssetDetails(String authorizationToken);

}