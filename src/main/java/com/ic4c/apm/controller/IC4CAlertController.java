
package com.ic4c.apm.controller;

import java.io.IOException;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ic4c.apm.service.IC4CAlertService;

@RestController
@RequestMapping(value = "/api")
public class IC4CAlertController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IC4CAlertService alertService;

	@RequestMapping(value = "/alerts", method = RequestMethod.GET)
	private Object getAllALerts(@RequestHeader(value = "authorization") String authorizationToken)
			throws ParseException, IOException {

		logger.debug("IC4CAssetController: getAllALerts");

		return alertService.getAllAlerts(authorizationToken);
	}

}
