package com.ic4c.apm.service;

import java.util.List;

import com.ic4c.apm.dto.IC4CAlertDTO;

public interface IC4CAlertService {
	
	public List<IC4CAlertDTO> getAllAlerts(String authorizationToken);
	

}
