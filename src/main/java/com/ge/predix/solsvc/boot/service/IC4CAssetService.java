package com.ge.predix.solsvc.boot.service;

import java.util.List;

import com.ge.predix.solsvc.boot.dto.IC4CAssetDTO;

public interface IC4CAssetService {
	
	public List<IC4CAssetDTO> getAssetDetails(String authorizationToken);

}
